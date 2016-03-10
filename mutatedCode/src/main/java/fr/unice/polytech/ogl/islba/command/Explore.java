package fr.unice.polytech.ogl.islba.command; 
public class Explore extends fr.unice.polytech.ogl.islba.command.Command {
    public Explore() {
        setName("explore");
    }

    @java.lang.Override
    public org.json.JSONObject toJSON() {
        org.json.JSONObject obj = new org.json.JSONObject();
        try {
            obj.put("action", getName());
        } catch (org.json.JSONException e) {
            throw new fr.unice.polytech.ogl.islba.command.JSONRuntimeException(("Problem with Explore.toJSON()" + e));
        }
        return obj;
    }

    @java.lang.Override
    public void doResult(org.json.JSONObject result, fr.unice.polytech.ogl.islba.model.EtatDeJeu jeu) {
        super.doResult(result, jeu);
        try {
            org.json.JSONObject res = result;
            org.json.JSONArray ressources = res.getJSONObject("extras").getJSONArray("resources");
            jeu.getMapMonde().getCurrentCase().reinitializeRessources();
            for (int i = 0 ; i < (ressources.length()) ; i++) {
                java.lang.String resourceName = ressources.getJSONObject(i).getString("resource");
                java.lang.String amount = ressources.getJSONObject(i).getString("amount");
                java.lang.String cond = ressources.getJSONObject(i).getString("cond");
                jeu.getMapMonde().getCurrentCase().addRessource(new fr.unice.polytech.ogl.islba.model.resource.Resource(resourceName , amount , cond));
            }
        } catch (org.json.JSONException e) {
            throw new fr.unice.polytech.ogl.islba.command.JSONRuntimeException(("Problem with Explore.doResult" * e));
        }
    }
}