package fr.unice.polytech.ogl.islba.ia;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import fr.unice.polytech.ogl.islba.command.Command;
import fr.unice.polytech.ogl.islba.command.Exploit;
import fr.unice.polytech.ogl.islba.model.EtatDeJeu;

public class TestFirstIA2 {

    private EtatDeJeu etat;
    private Command cmd;
    private FirstIA ia;
    
    @Before
    public void init(){
        ia = new FirstIA();
        HashMap<String, Integer> obj = new HashMap<String, Integer>();
        obj.put("FUR",200);
        obj.put("GLASS",2000);
        obj.put("PLANK", 400);
        etat = new EtatDeJeu(6000, "creek_id",50,obj);
    }
    
    
    @Test
    public void testTransform(){
        //land
        cmd=ia.takeDecision(etat, etat.getTeam());
        cmd.doResult(new JSONObject("{\"status\": \"ok\", \"cost\":60}"),etat);
        
        //scout puis move
        cmd=ia.takeDecision(etat, etat.getTeam());
        cmd.doResult(new JSONObject("{\"status\": \"ok\", \"cost\":60, \"extras\": {\"resources\":[\"WOOD\",\"QUARTZs\",\"FUR\"], \"altitude\":-23}}"),etat);
        cmd=ia.takeDecision(etat, etat.getTeam());
        cmd.doResult(new JSONObject("{\"status\": \"ok\", \"cost\":60}"),etat);
        
        //explore
        cmd=ia.takeDecision(etat, etat.getTeam());
        cmd.doResult(new JSONObject("{\"status\": \"ok\", \"cost\":60,"
                + "\"extras\": {"
                    + "\"resources\": ["
                        + "{\"resource\": \"WOOD\", \"amount\":\"HIGH\",\"cond\":\"EASY\"},"
                        + "{\"resource\": \"QUARTZ\", \"amount\":\"HIGH\",\"cond\":\"EASY\"},"
                        + "{\"resource\": \"FUR\", \"amount\":\"HIGH\",\"cond\":\"EASY\"}"
                    + "],"
                + "\"pois\":[]}"
                + "}"),etat);
        //exploit
        cmd=ia.takeDecision(etat,etat.getTeam());
        assertEquals("exploit",cmd.getName());
        assertEquals("WOOD",cmd.toJSON().getJSONObject("parameters").getString("resource"));
        cmd.doResult(new JSONObject("{\"status\":\"OK\", \"cost\": 37, \"extras\": {\"amount\":123}}"),etat);
        
        //transform into planks
        cmd=ia.takeDecision(etat,etat.getTeam());
        assertEquals("transform",cmd.getName());
        assertEquals(100,cmd.toJSON().getJSONObject("parameters").getInt("WOOD"));
        cmd.doResult(new JSONObject("{\"status\":\"OK\",cost:12,\"extras\":{\"kind\":\"PLANK\",\"production\":400}}"),etat);
        
        //exploit
        cmd=ia.takeDecision(etat,etat.getTeam());
        assertEquals("exploit",cmd.getName());
        assertEquals("QUARTZ",cmd.toJSON().getJSONObject("parameters").getString("resource"));
        cmd.doResult(new JSONObject("{\"status\":\"OK\", \"cost\": 37, \"extras\": {\"amount\":123}}"),etat);
        
        //transform into GLASS
        cmd = ia.takeDecision(etat, etat.getTeam());
        assertEquals("transform",cmd.getName());
        assertEquals(20,cmd.toJSON().getJSONObject("parameters").getInt("WOOD"));
        assertEquals(40, cmd.toJSON().getJSONObject("parameters").getInt("QUARTZ"));
        JSONObject result = new JSONObject("{\"status\":\"OK\",cost:12,\"extras\":{\"kind\":\"GLASS\",\"production\":4}}");
        cmd.doResult(result, etat);
    }
}
