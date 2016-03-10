package fr.unice.polytech.ogl.islba.command;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import fr.unice.polytech.ogl.islba.model.Case;
import fr.unice.polytech.ogl.islba.model.Coordonnees;
import fr.unice.polytech.ogl.islba.model.EtatDeJeu;
import static org.junit.Assert.*;

public class TestCommandExplore {
    @Test
    public void testToJSON(){
        Explore explore = new Explore();
        JSONObject obj= new JSONObject();
        try {
            obj.put("action", "explore");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        assertTrue((explore.toJSON().toString()).equals(obj.toString()));
    }
    
    @Test
    public void testDoResult(){
    	try {
    		JSONObject result = new JSONObject();
   
    		JSONObject extras = new JSONObject();
    		
    		JSONArray resources = new JSONArray();
    		JSONObject res1 = new JSONObject();
    		JSONObject res2 = new JSONObject();
    		JSONObject res3 = new JSONObject();
    		
    		JSONArray pois = new JSONArray();
    		JSONObject pois1 = new JSONObject();
    		    		
			result.put("status", "OK");
			result.put("cost",35);
			
			res1.put("resource", "WOOD");
			res1.put("amount", "HIGH");
			res1.put("cond", "EASY");
			
			res2.put("resource", "FUR");
			res2.put("amount", "LOW");
			res2.put("cond", "FAIR");
			
			res3.put("resource", "FLOWER");
			res3.put("amount", "MEDIUM");
			res3.put("cond", "HARSH");
			
			resources.put(res1);
			resources.put(res2);
			resources.put(res3);
			
			extras.put("resource", resources);
			
			pois1.put("kind", "CREEK");
			pois1.put("id", "creek_identifier_2");
			
			pois.put(pois1);
			
			extras.put("resources", resources);
			extras.put("pois", pois);
			
			result.put("extras",extras);
			
			Explore explore = new Explore();
			Map<String, Integer> resourcesMap = new HashMap<String, Integer>();
			EtatDeJeu jeu = new EtatDeJeu(200, "creek", 20, resourcesMap);
			jeu.getMapMonde().addCaseMap(new Coordonnees(0, 0), new Case());
			jeu.getMapMonde().setCurrentCoo(new Coordonnees(0, 0));

			explore.doResult(result, jeu);
			
			assertEquals(165, jeu.getPA());
			assertEquals(3, jeu.getMapMonde().getCurrentCase().getRessources().size());
			
		} catch (JSONException e) {
			throw new JSONRuntimeException("Problem with the  test of Glimpse");
		}
    }
}
