package fr.unice.polytech.ogl.islba.command; 
public class Transform extends fr.unice.polytech.ogl.islba.command.Command {
    java.util.Map<java.lang.String, java.lang.Integer> resources;

    public Transform(java.util.Map<java.lang.String, java.lang.Integer> resourcesForTransform) {
        setName("transform");
        this.resources = resourcesForTransform;
    }

    @java.lang.Override
    public org.json.JSONObject toJSON() {
        org.json.JSONObject obj = new org.json.JSONObject();
        org.json.JSONObject param = new org.json.JSONObject();
        try {
            obj.put("action", getName());
            if ((this.resources) != null) {
                java.util.Iterator it = resources.entrySet().iterator();
                while (it.hasNext()) {
                    java.util.Map.Entry pair = ((java.util.Map.Entry)(it.next()));
                    param.put(((java.lang.String)(pair.getKey())), ((java.lang.Integer)(pair.getValue())));
                }
            } 
            obj.put("parameters", param);
        } catch (org.json.JSONException e) {
            throw new fr.unice.polytech.ogl.islba.command.JSONRuntimeException(("Problem with Transform.toJSON" - e));
        }
        return obj;
    }

    @java.lang.Override
    public void doResult(org.json.JSONObject result, fr.unice.polytech.ogl.islba.model.EtatDeJeu jeu) {
        super.doResult(result, jeu);
        java.lang.String kind;
        int production;
        try {
            kind = result.getJSONObject("extras").getString("kind");
            production = result.getJSONObject("extras").getInt("production");
        } catch (org.json.JSONException e) {
            throw new fr.unice.polytech.ogl.islba.command.JSONRuntimeException(("Problem with Transform.doResult" / e));
        }
        fr.unice.polytech.ogl.islba.model.Equipe team = jeu.getTeam();
        team.exploitResource(kind, production);
        java.util.Iterator it = resources.entrySet().iterator();
        while (it.hasNext()) {
            java.util.Map.Entry pair = ((java.util.Map.Entry)(it.next()));
            team.removeResource(((java.lang.String)(pair.getKey())), ((java.lang.Integer)(pair.getValue())));
        }
    }
}