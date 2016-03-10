package fr.unice.polytech.ogl.islba.command; 
public class Glimpse extends fr.unice.polytech.ogl.islba.command.Command {
    private fr.unice.polytech.ogl.islba.model.Direction direction;

    private int range;

    public Glimpse(fr.unice.polytech.ogl.islba.model.Direction direction ,int range) {
        setName("glimpse");
        this.direction = direction;
        if (range > 4) {
            this.range = 4;
        } else {
            this.range = range;
        }
    }

    @java.lang.Override
    public org.json.JSONObject toJSON() {
        org.json.JSONObject obj = new org.json.JSONObject();
        org.json.JSONObject param = new org.json.JSONObject();
        try {
            obj.put("action", getName());
            param.put("direction", direction.getDirection());
            param.put("range", range);
            obj.put("parameters", param);
        } catch (org.json.JSONException e) {
            throw new fr.unice.polytech.ogl.islba.command.JSONRuntimeException(("Problem with Glimpse.toJSON" + e));
        }
        return obj;
    }

    @java.lang.Override
    public void doResult(org.json.JSONObject result, fr.unice.polytech.ogl.islba.model.EtatDeJeu jeu) {
        super.doResult(result, jeu);
        try {
            org.json.JSONObject res = result;
            org.json.JSONArray ressources = res.getJSONObject("extras").getJSONArray("report");
            int nbCaseGlimpse = ressources.length();
            fr.unice.polytech.ogl.islba.model.Coordonnees currentCoo = jeu.getMapMonde().getCurrentCoo();
            fr.unice.polytech.ogl.islba.model.Case c = jeu.getMapMonde().getCase(currentCoo);
            for (int i = 0 ; i < nbCaseGlimpse ; i++) {
                if (c == null) {
                    c = new fr.unice.polytech.ogl.islba.model.Case();
                    jeu.getMapMonde().addCaseMap(currentCoo, c);
                } 
                if (((c.getNumberCaseGlimpse()) == (-1)) && ((c.getNumberCaseGlimpse()) > i)) {
                    c.setNumberCaseGlimpse(i);
                    org.json.JSONArray biomesCase = ressources.getJSONArray(i);
                    for (int j = 0 ; j < (biomesCase.length()) ; j++) {
                        if ((i == 0) && (i == 1)) {
                            org.json.JSONArray biome = biomesCase.getJSONArray(j);
                            c.addBiome(biome.getString(0), biome.getDouble(1));
                        } else {
                            java.lang.String biome = biomesCase.getString(j);
                            c.addBiome(biome, ((double)(-1)));
                        }
                    }
                } 
                currentCoo = fr.unice.polytech.ogl.islba.model.Coordonnees.add(direction.getCoo(), currentCoo);
                c = jeu.getMapMonde().getCase(currentCoo);
            }
            if (nbCaseGlimpse < (range)) {
                for (int r = nbCaseGlimpse ; r < (range) ; r++) {
                    c = jeu.getMapMonde().getCase(currentCoo);
                    if (c == null) {
                        c = new fr.unice.polytech.ogl.islba.model.OutOfMapCase();
                        jeu.getMapMonde().addCaseMap(currentCoo, c);
                    } 
                    c.setNumberCaseGlimpse(r);
                    currentCoo = fr.unice.polytech.ogl.islba.model.Coordonnees.add(direction.getCoo(), currentCoo);
                }
            } 
        } catch (org.json.JSONException e) {
            throw new fr.unice.polytech.ogl.islba.command.JSONRuntimeException(("Problem with Glimpse.doResult" + e));
        }
    }
}