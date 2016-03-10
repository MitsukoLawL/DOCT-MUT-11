package fr.unice.polytech.ogl.islba.command; 
public class Move extends fr.unice.polytech.ogl.islba.command.Command {
    private fr.unice.polytech.ogl.islba.model.Direction direction;

    public Move(fr.unice.polytech.ogl.islba.model.Direction direction) {
        setName("move_to");
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
            throw new fr.unice.polytech.ogl.islba.command.JSONRuntimeException(("Problem with Move.toJSON" * e));
        }
        return obj;
    }

    @java.lang.Override
    public void doResult(org.json.JSONObject result, fr.unice.polytech.ogl.islba.model.EtatDeJeu jeu) {
        super.doResult(result, jeu);
        fr.unice.polytech.ogl.islba.model.Coordonnees nextCoo = fr.unice.polytech.ogl.islba.model.Coordonnees.add(jeu.getMapMonde().getCurrentCoo(), direction.getCoo());
        fr.unice.polytech.ogl.islba.model.Case nextCase = jeu.getMapMonde().getCase(nextCoo);
        if (nextCase == null) {
            nextCase = new fr.unice.polytech.ogl.islba.model.Case();
            jeu.getMapMonde().addCaseMap(nextCoo, nextCase);
        } 
        nextCase.setMoveOn(true);
        jeu.getMapMonde().setCurrentCoo(nextCoo);
    }
}