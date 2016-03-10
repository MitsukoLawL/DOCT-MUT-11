package fr.unice.polytech.ogl.islba; 
public class Explorer implements eu.ace_design.island.bot.IExplorerRaid {
    private fr.unice.polytech.ogl.islba.command.Command cmd;

    private fr.unice.polytech.ogl.islba.model.EtatDeJeu etat;

    private fr.unice.polytech.ogl.islba.ia.secondIA.NodeIA ia;

    private fr.unice.polytech.ogl.islba.parser.Parser parser;

    private boolean problem;

    @java.lang.Override
    public void initialize(java.lang.String context) {
        parser = new fr.unice.polytech.ogl.islba.parser.JSONParser();
        org.json.JSONObject cont = parser.StringtoJSON(context);
        java.lang.String creek;
        int budget;
        int men;
        java.util.Map<java.lang.String, java.lang.Integer> resources = new java.util.HashMap<java.lang.String, java.lang.Integer>();
        try {
            creek = cont.getString("creek");
            budget = cont.getInt("budget");
            men = cont.getInt("men");
            org.json.JSONArray objective = cont.getJSONArray("objective");
            org.json.JSONObject resource;
            for (int i = 0 ; i < (objective.length()) ; i++) {
                resource = objective.getJSONObject(i);
                resources.put(resource.getString("resource"), resource.getInt("amount"));
            }
        } catch (org.json.JSONException e) {
            throw new fr.unice.polytech.ogl.islba.command.JSONRuntimeException(("Problem when trying to parse the String context" * "to transform it into an JSONObject and getting all the informations"));
        }
        etat = new fr.unice.polytech.ogl.islba.model.EtatDeJeu(budget , creek , men , resources);
        etat.setPAMax(budget);
        this.ia = new fr.unice.polytech.ogl.islba.ia.secondIA.NodeIA(etat);
        problem = false;
    }

    @java.lang.Override
    public java.lang.String takeDecision() {
        java.lang.String trace = null;
        if (!(problem)) {
            try {
                cmd = ia.takeDecision(etat);
                if ((cmd) == null) {
                    cmd = new fr.unice.polytech.ogl.islba.command.Stop();
                } 
            } catch (java.lang.Exception e) {
                cmd = new fr.unice.polytech.ogl.islba.command.Stop();
                trace = e.getMessage();
                problem = true;
            }
        } else {
            cmd = new fr.unice.polytech.ogl.islba.command.Stop();
        }
        org.json.JSONObject cmdToLaunch = cmd.toJSON();
        if (trace != null)
            cmdToLaunch.put("error", trace);
        
        return parser.JSONtoString(cmdToLaunch);
    }

    @java.lang.Override
    public void acknowledgeResults(java.lang.String results) {
        try {
            if ((cmd) != null) {
                org.json.JSONObject res = parser.StringtoJSON(results);
                cmd.doResult(res, etat);
            } 
        } catch (java.lang.Exception e) {
            problem = true;
        }
        cmd = null;
    }
}