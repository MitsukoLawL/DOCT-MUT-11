package fr.unice.polytech.ogl.islba;

import fr.unice.polytech.ogl.islba.ia.FirstIA;
import fr.unice.polytech.ogl.islba.ia.secondIA.NodeIA;
import fr.unice.polytech.ogl.islba.model.EtatDeJeu;
import fr.unice.polytech.ogl.islba.parser.JSONParser;
import fr.unice.polytech.ogl.islba.parser.Parser;

import org.json.*;

import java.util.HashMap;
import java.util.Map;

import fr.unice.polytech.ogl.islba.command.*;
import eu.ace_design.island.bot.*;

public class Explorer implements IExplorerRaid{
    private Command cmd;
    private EtatDeJeu etat;
    //private FirstIA ia;
    private NodeIA ia;
    private Parser parser;
    private boolean problem;
    
    @Override
	public void initialize(String context) {
        parser = new JSONParser();
	    JSONObject cont = parser.StringtoJSON(context);
	    String creek;
	    int budget;
	    int men;
	    Map<String, Integer> resources = new HashMap<String, Integer>();
        try {
            creek=cont.getString("creek");
            budget = cont.getInt("budget");
            men = cont.getInt("men");
            JSONArray objective = cont.getJSONArray("objective");
            JSONObject resource;
            for(int i=0;i<objective.length();i++){
                resource = objective.getJSONObject(i);
                resources.put(resource.getString("resource"),resource.getInt("amount"));
            }
        } catch (JSONException e) {
            throw new JSONRuntimeException("Problem when trying to parse the String context" +
        		"to transform it into an JSONObject and getting all the informations");
        }

        etat = new EtatDeJeu(budget, creek,men,resources);
        etat.setPAMax(budget);
        
        this.ia=new NodeIA(etat);
        problem = false;
	}

    @Override
	public String takeDecision() {
        String trace = null;
        if(!problem){
            try{
                //cmd = ia.takeDecision(etat, etat.getTeam());
                cmd = ia.takeDecision(etat);
                if(cmd==null){
                    cmd=new Stop();
                }
            }catch(Exception e){
                cmd = new Stop();
                trace = e.getMessage();
                problem = true;
            }
        }else{
            cmd = new Stop();
        }
        JSONObject cmdToLaunch = cmd.toJSON();
        if(trace!=null) cmdToLaunch.put("error",trace);
        return parser.JSONtoString(cmdToLaunch);
	}

    @Override
	public void acknowledgeResults(String results) {
        try{
            if(cmd != null){
                JSONObject res = parser.StringtoJSON(results);
                cmd.doResult(res, etat);
            }
        }catch(Exception e){
            problem = true;
        }
		cmd=null;
    }
}
