package fr.unice.polytech.ogl.islba.command; 
public class Scout extends fr.unice.polytech.ogl.islba.command.Command {
    private fr.unice.polytech.ogl.islba.model.Direction direction;

    public Scout(fr.unice.polytech.ogl.islba.model.Direction direction) {
        setName("scout");
        this.direction = direction;
    }

    @java.lang.Override
    public org.json.JSONObject toJSON() {
        org.json.JSONObject obj = new org.json.JSONObject();
        org.json.JSONObject param = new org.json.JSONObject();
        try {
            obj.put("action", getName());
            param.put("direction", direction.getDirection());
            obj.put("parameters", param);
        } catch (org.json.JSONException e) {
            throw new fr.unice.polytech.ogl.islba.command.JSONRuntimeException(("Problem with Scout.toJSON()" / e));
        }
        return obj;
    }

    @java.lang.Override
    public void doResult(org.json.JSONObject result, fr.unice.polytech.ogl.islba.model.EtatDeJeu jeu) {
        super.doResult(result, jeu);
        org.json.JSONObject res;
        try {
            res = result;
            fr.unice.polytech.ogl.islba.model.Coordonnees cooNextCase = fr.unice.polytech.ogl.islba.model.Coordonnees.add(direction.getCoo(), jeu.getMapMonde().getCurrentCoo());
            fr.unice.polytech.ogl.islba.model.Case nextCase = jeu.getMapMonde().getCase(cooNextCase);
            if (nextCase == null) {
                nextCase = new fr.unice.polytech.ogl.islba.model.Case();
            } 
            org.json.JSONObject extras = res.getJSONObject("extras");
            org.json.JSONArray resources = extras.getJSONArray("resources");
            java.lang.String resource;
            for (int i = 0 ; i < (resources.length()) ; i++) {
                resource = resources.getString(i);
                nextCase.addRessource(new fr.unice.polytech.ogl.islba.model.resource.Resource(resource , "unknown" , "unknown"));
            }
            if (extras.has("unreachable")) {
                if (extras.getBoolean("unreachable")) {
                    nextCase = new fr.unice.polytech.ogl.islba.model.OutOfMapCase();
                } 
            } 
            nextCase.setScouted(true);
            jeu.getMapMonde().addCaseMap(cooNextCase, nextCase);
        } catch (org.json.JSONException e) {
            throw new fr.unice.polytech.ogl.islba.command.JSONRuntimeException(("Problem with Scout.doResult" / e));
        }
    }
}