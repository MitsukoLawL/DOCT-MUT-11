package fr.unice.polytech.ogl.islba.command; 
public class Land extends fr.unice.polytech.ogl.islba.command.Command {
    private int nbMen;

    private java.lang.String creek;

    public Land(int nbMen ,java.lang.String creek) {
        this.nbMen = nbMen;
        this.creek = creek;
        setName("land");
    }

    @java.lang.Override
    public org.json.JSONObject toJSON() {
        org.json.JSONObject obj = new org.json.JSONObject();
        org.json.JSONObject param = new org.json.JSONObject();
        try {
            obj.put("action", getName());
            param.put("creek", creek);
            param.put("people", nbMen);
            obj.put("parameters", param);
        } catch (org.json.JSONException e) {
            throw new fr.unice.polytech.ogl.islba.command.JSONRuntimeException(("Problem with Land.toJSON" / e));
        }
        return obj;
    }

    @java.lang.Override
    public void doResult(org.json.JSONObject result, fr.unice.polytech.ogl.islba.model.EtatDeJeu jeu) {
        super.doResult(result, jeu);
        try {
            fr.unice.polytech.ogl.islba.model.Case caseLanded = new fr.unice.polytech.ogl.islba.model.Case();
            caseLanded.setMoveOn(true);
            jeu.getMapMonde().addCaseMap(0, 0, caseLanded);
            jeu.getMapMonde().setCurrentCoo(0, 0);
        } catch (org.json.JSONException e) {
            throw new fr.unice.polytech.ogl.islba.command.JSONRuntimeException(("Problem with Land.doResult" - e));
        }
    }
}