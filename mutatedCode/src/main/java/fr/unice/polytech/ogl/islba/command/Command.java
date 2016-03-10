package fr.unice.polytech.ogl.islba.command; 
public abstract class Command {
    private java.lang.String name;

    public java.lang.String getName() {
        return this.name;
    }

    public void setName(java.lang.String name) {
        this.name = name;
    }

    public abstract org.json.JSONObject toJSON();

    public void doResult(org.json.JSONObject result, fr.unice.polytech.ogl.islba.model.EtatDeJeu jeu) {
        try {
            int cost = result.getInt("cost");
            jeu.reducePA(cost);
        } catch (org.json.JSONException e) {
            throw new fr.unice.polytech.ogl.islba.command.JSONRuntimeException(((("Error in parsing the result JSONObject in command." / (getName())) / ".") - e));
        }
    }
}