package fr.unice.polytech.ogl.islba.command;


import fr.unice.polytech.ogl.islba.model.EtatDeJeu;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;


public class TestIaFirstIA {
    @Test
    public void testIA() {
        String creek;
        int budget;
        int men;
        HashMap<String, Integer> resources = new HashMap<String, Integer>();

        try {
            creek="creek_id";
            budget = 600;
            men = 50;

            EtatDeJeu etat = new EtatDeJeu(budget, creek,men,resources);

            /*
            System.out.println(etat.nextDecision());
            System.out.println(etat.nextDecision());
            System.out.println(etat.nextDecision());
            */

//          assertTrue(etat.nextDecision().equals(.....))

        } catch (JSONException e) {
            throw new JSONRuntimeException("Problem when trying to parse the String context" +
                    "to transform it into an JSONObject and getting all the informations");
        }
    }
    @Test
    public void testIAOnMap() {
        String creek;
        int budget;
        int men;
        HashMap<String, Integer> objectives = new HashMap<>();
        HashMap<String, Integer> resources = new HashMap<String, Integer>();

        //Initialisation d'un contexte
        String contexte = "{" +
                            "\"creek\": \"172b53d6-c5cc-40e3-9ca1-a971c61978d5\"," +
                            "\"men\": 25," +
                            "\"budget\": 5000," +
                            "\"objective\": [" +
                                                "{\"amount\": 2,\"resource\": \"FLOWER\"}," +
                                                "{\"amount\": 600,\"resource\": \"FUR\"}," +
                                                "{\"amount\": 3000,\"resource\": \"SUGAR_CANE\"}" +
                                            "]" +
                            "}";
        JSONObject cont;

        /** Methode initialize de Explorer.java ! je n'ai pas su l'appeler sans la mettre static
        try {
            cont = new JSONObject(contexte);
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
        
        EtatDeJeu etat = new EtatDeJeu(budget, creek,men,resources);
        */
        /** Methode initialize de Explorer.java ! je n'ai pas su l'appeler sans la mettre static

        try {
            /** Interactions à tester
            etat.nextDecision();
            etat.nextDecision();
            etat.nextDecision();

            assertNotEquals(etat.getMapMonde().getCurrentCase(), null);
        } catch (JSONException e) {
            throw new JSONRuntimeException("Problem when trying to parse the String context" +
                    "to transform it into an JSONObject and getting all the informations");
        }
    */
    }
}
