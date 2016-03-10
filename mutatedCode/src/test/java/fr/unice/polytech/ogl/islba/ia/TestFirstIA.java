package fr.unice.polytech.ogl.islba.ia;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import fr.unice.polytech.ogl.islba.command.Command;
import fr.unice.polytech.ogl.islba.command.Exploit;
import fr.unice.polytech.ogl.islba.command.Explore;
import fr.unice.polytech.ogl.islba.command.Glimpse;
import fr.unice.polytech.ogl.islba.command.Land;
import fr.unice.polytech.ogl.islba.command.Move;
import fr.unice.polytech.ogl.islba.command.Scout;
import fr.unice.polytech.ogl.islba.model.Direction;
import fr.unice.polytech.ogl.islba.model.EtatDeJeu;
/*
 *     
        //first move
        cmd.doResult("{\"status\": \"ok\", \"cost\":60}",etat);
        cmd=ia.takeDecision(etat, etat.getTeam());
        assertEquals(cmd.toJSON(),new Scout(Direction.NORTH).toJSON());
        cmd.doResult("{\"status\": \"ok\", \"cost\":60, \"extras\": {\"resources\":[\"ROCK\",\"PAPER\"], \"altitude\":-23}}",etat);
        cmd=ia.takeDecision(etat, etat.getTeam());
        assertEquals(cmd.toJSON(),new Scout(Direction.EAST).toJSON());
        cmd.doResult("{\"status\": \"ok\", \"cost\":60, \"extras\": {\"resources\":[\"ROCK\",\"PAPER\"], \"altitude\":-23}}",etat);
        cmd=ia.takeDecision(etat, etat.getTeam());
        assertEquals(cmd.toJSON(),new Scout(Direction.SOUTH).toJSON());
        cmd.doResult("{\"status\": \"ok\", \"cost\":60, \"extras\": {\"resources\":[\"ROCK\",\"PAPER\"], \"altitude\":-23}}",etat);
        cmd=ia.takeDecision(etat, etat.getTeam());
        assertEquals(cmd.toJSON(),new Scout(Direction.WEST).toJSON());
        cmd.doResult("{\"status\": \"ok\", \"cost\":60, \"extras\": {\"resources\":[\"ROCK\",\"PAPER\"], \"altitude\":-23}}",etat);
        cmd=ia.takeDecision(etat, etat.getTeam());
        assertEquals(cmd.toJSON(),new Glimpse(Direction.EAST,4).toJSON());
        cmd.doResult("{ \"status\": \"OK\", \"cost\": 12,\"extras\": {\"asked_range\": 4,\"report\": [[[\"MANGROVE\", 80.0], [\"BEACH\", 20.0]],[[\"MANGROVE\", 40.0], [\"TROPICAL_RAIN_FOREST\", 40.0], [\"TROPICAL_SEASONAL_FOREST\", 20.0]],[\"TROPICAL_RAIN_FOREST\", \"TROPICAL_SEASONAL_FOREST\"],[\"OCEAN\"]]}}",etat);
        cmd=ia.takeDecision(etat, etat.getTeam());
        assertEquals(cmd.toJSON(),new Glimpse(Direction.WEST,4).toJSON());
        cmd.doResult("{ \"status\": \"OK\", \"cost\": 12,\"extras\": {\"asked_range\": 4,\"report\": [[[\"MANGROVE\", 80.0], [\"BEACH\", 20.0]],[[\"MANGROVE\", 40.0], [\"TROPICAL_RAIN_FOREST\", 40.0], [\"TROPICAL_SEASONAL_FOREST\", 20.0]],[\"TROPICAL_RAIN_FOREST\", \"TROPICAL_SEASONAL_FOREST\"],[\"OCEAN\"]]}}",etat);
        
        //second move
        cmd=ia.takeDecision(etat, etat.getTeam());
        assertEquals(cmd.toJSON(),new Move(Direction.SOUTH).toJSON());
        cmd.doResult("{\"status\": \"ok\", \"cost\":60}",etat);
        cmd=ia.takeDecision(etat, etat.getTeam());
        assertEquals(cmd.toJSON(),new Scout(Direction.EAST).toJSON());
        cmd.doResult("{\"status\": \"ok\", \"cost\":60, \"extras\": {\"resources\":[\"ROCK\",\"PAPER\"], \"altitude\":-23}}",etat);
        cmd=ia.takeDecision(etat, etat.getTeam());
        assertEquals(cmd.toJSON(),new Scout(Direction.SOUTH).toJSON());
        cmd.doResult("{\"status\": \"ok\", \"cost\":60, \"extras\": {\"resources\":[\"ROCK\",\"PAPER\"], \"altitude\":-23}}",etat);
        cmd=ia.takeDecision(etat, etat.getTeam());
        assertEquals(cmd.toJSON(),new Scout(Direction.WEST).toJSON());
        cmd.doResult("{\"status\": \"ok\", \"cost\":60, \"extras\": {\"resources\":[\"ROCK\",\"PAPER\"], \"altitude\":-23}}",etat);
        cmd=ia.takeDecision(etat, etat.getTeam());
        assertEquals(cmd.toJSON(),new Glimpse(Direction.EAST,4).toJSON());
        cmd.doResult("{ \"status\": \"OK\", \"cost\": 12,\"extras\": {\"asked_range\": 4,\"report\": [[[\"MANGROVE\", 80.0], [\"BEACH\", 20.0]],[[\"MANGROVE\", 40.0], [\"TROPICAL_RAIN_FOREST\", 40.0], [\"TROPICAL_SEASONAL_FOREST\", 20.0]],[\"TROPICAL_RAIN_FOREST\", \"TROPICAL_SEASONAL_FOREST\"],[\"TROPICAL_SEASONAL_FOREST\"]]}}",etat);
        cmd=ia.takeDecision(etat, etat.getTeam());
        assertEquals(cmd.toJSON(),new Glimpse(Direction.WEST,4).toJSON());
        cmd.doResult("{ \"status\": \"OK\", \"cost\": 12,\"extras\": {\"asked_range\": 4,\"report\": [[[\"MANGROVE\", 80.0], [\"BEACH\", 20.0]],[[\"MANGROVE\", 40.0], [\"TROPICAL_RAIN_FOREST\", 40.0], [\"TROPICAL_SEASONAL_FOREST\", 20.0]],[\"TROPICAL_RAIN_FOREST\", \"TROPICAL_SEASONAL_FOREST\"],[\"OCEAN\"]]}}",etat);
        
        //third move
        cmd=ia.takeDecision(etat, etat.getTeam());
        assertEquals(cmd.toJSON(),new Move(Direction.SOUTH).toJSON());
        cmd.doResult("{\"status\": \"ok\", \"cost\":60}",etat);
        cmd=ia.takeDecision(etat, etat.getTeam());
        assertEquals(cmd.toJSON(),new Scout(Direction.EAST).toJSON());
        cmd.doResult("{\"status\": \"ok\", \"cost\":60, \"extras\": {\"resources\":[\"ROCK\",\"PAPER\"], \"altitude\":-23}}",etat);
        cmd=ia.takeDecision(etat, etat.getTeam());
        assertEquals(cmd.toJSON(),new Scout(Direction.SOUTH).toJSON());
        cmd.doResult("{\"status\": \"ok\", \"cost\":60, \"extras\": {\"resources\":[\"ROCK\",\"PAPER\"], \"altitude\":-23}}",etat);
        cmd=ia.takeDecision(etat, etat.getTeam());
        assertEquals(cmd.toJSON(),new Scout(Direction.WEST).toJSON());
        cmd.doResult("{\"status\": \"ok\", \"cost\":60, \"extras\": {\"resources\":[\"ROCK\",\"PAPER\"], \"altitude\":-23}}",etat);
        cmd=ia.takeDecision(etat, etat.getTeam());
        assertEquals(cmd.toJSON(),new Glimpse(Direction.EAST,4).toJSON());
        cmd.doResult("{ \"status\": \"OK\", \"cost\": 12,\"extras\": {\"asked_range\": 4,\"report\": [[[\"MANGROVE\", 80.0], [\"BEACH\", 20.0]],[[\"MANGROVE\", 40.0], [\"TROPICAL_RAIN_FOREST\", 40.0], [\"TROPICAL_SEASONAL_FOREST\", 20.0]],[\"TROPICAL_RAIN_FOREST\", \"TROPICAL_SEASONAL_FOREST\"],[\"LAKE\"]]}}",etat);
        cmd=ia.takeDecision(etat, etat.getTeam());
        assertEquals(cmd.toJSON(),new Glimpse(Direction.SOUTH,4).toJSON());
        cmd.doResult("{ \"status\": \"OK\", \"cost\": 12,\"extras\": {\"asked_range\": 4,\"report\": [[[\"MANGROVE\", 80.0], [\"BEACH\", 20.0]],[[\"MANGROVE\", 40.0], [\"TROPICAL_RAIN_FOREST\", 40.0], [\"TROPICAL_SEASONAL_FOREST\", 20.0]],[\"TROPICAL_RAIN_FOREST\", \"TROPICAL_SEASONAL_FOREST\"],[\"OCEAN\"]]}}",etat);
        cmd=ia.takeDecision(etat, etat.getTeam());
        assertEquals(cmd.toJSON(),new Glimpse(Direction.WEST,4).toJSON());
        cmd.doResult("{ \"status\": \"OK\", \"cost\": 12,\"extras\": {\"asked_range\": 4,\"report\": [[[\"MANGROVE\", 80.0], [\"BEACH\", 20.0]],[[\"MANGROVE\", 40.0], [\"TROPICAL_RAIN_FOREST\", 40.0], [\"TROPICAL_SEASONAL_FOREST\", 20.0]],[\"TROPICAL_RAIN_FOREST\", \"TROPICAL_SEASONAL_FOREST\"],[\"TROPICAL_SEASONAL_FOREST\"]]}}",etat);
        
        //fourth move
        cmd=ia.takeDecision(etat, etat.getTeam());
        assertEquals(cmd.toJSON(),new Move(Direction.WEST).toJSON());
        cmd.doResult("{\"status\": \"ok\", \"cost\":60}",etat);
        cmd=ia.takeDecision(etat, etat.getTeam());
        assertEquals(cmd.toJSON(),new Scout(Direction.SOUTH).toJSON());
        cmd.doResult("{\"status\": \"ok\", \"cost\":60, \"extras\": {\"resources\":[\"ROCK\",\"PAPER\"], \"altitude\":-23}}",etat);
        cmd=ia.takeDecision(etat, etat.getTeam());
        assertEquals(cmd.toJSON(),new Scout(Direction.WEST).toJSON());
        cmd.doResult("{\"status\": \"ok\", \"cost\":60, \"extras\": {\"resources\":[\"ROCK\",\"PAPER\"], \"altitude\":-23}}",etat);
        cmd=ia.takeDecision(etat, etat.getTeam());
        assertEquals(cmd.toJSON(),new Glimpse(Direction.SOUTH,4).toJSON());
        cmd.doResult("{ \"status\": \"OK\", \"cost\": 12,\"extras\": {\"asked_range\": 4,\"report\": [[[\"MANGROVE\", 80.0], [\"BEACH\", 20.0]],[[\"MANGROVE\", 40.0], [\"TROPICAL_RAIN_FOREST\", 40.0], [\"TROPICAL_SEASONAL_FOREST\", 20.0]],[\"TROPICAL_RAIN_FOREST\", \"TROPICAL_SEASONAL_FOREST\"],[\"TROPICAL_SEASONAL_FOREST\"]]}}",etat);
        
        cmd=ia.takeDecision(etat, etat.getTeam());
        assertEquals(cmd.toJSON(),new Move(Direction.WEST).toJSON());
        cmd.doResult("{\"status\": \"ok\", \"cost\":60}",etat);
 */
public class TestFirstIA {
    private EtatDeJeu etat;
    private Command cmd;
    private FirstIA ia;
    
    @Before
    public void init(){
        ia = new FirstIA();
        HashMap<String, Integer> obj = new HashMap<String, Integer>();
        obj.put("FUR",200);
        obj.put("WOOD",600);
        obj.put("PS4",2000);
        etat = new EtatDeJeu(6000, "creek_id",50,obj);
    }

    @Test
    public void testBeginning() {
        cmd=ia.takeDecision(etat, etat.getTeam());
        assertEquals(cmd.toJSON().toString(),new Land(1,"creek_id").toJSON().toString());
        cmd.doResult(new JSONObject("{\"status\": \"ok\", \"cost\":60}"),etat);
        assertNotEquals(etat.getMapMonde().getCurrentCase(),null);
    }
    
    @Test
    public void testIfNoResourceNearUs(){
        cmd=ia.takeDecision(etat, etat.getTeam());
        cmd.doResult(new JSONObject("{\"status\": \"ok\", \"cost\":60}"),etat);
        
        cmd=ia.takeDecision(etat, etat.getTeam());
        assertEquals(cmd.toJSON().toString(),new Scout(Direction.NORTH).toJSON().toString());
        cmd.doResult(new JSONObject("{\"status\": \"ok\", \"cost\":60, \"extras\": {\"resources\":[\"ROCK\",\"PAPER\"], \"altitude\":-23}}"),etat);
        cmd=ia.takeDecision(etat, etat.getTeam());
        assertEquals(cmd.toJSON().toString(),new Scout(Direction.EAST).toJSON().toString());
        cmd.doResult(new JSONObject("{\"status\": \"ok\", \"cost\":60, \"extras\": {\"resources\":[\"ROCK\",\"PAPER\"], \"altitude\":-23}}"),etat);
        cmd=ia.takeDecision(etat, etat.getTeam());
        assertEquals(cmd.toJSON().toString(),new Scout(Direction.SOUTH).toJSON().toString());
        cmd.doResult(new JSONObject("{\"status\": \"ok\", \"cost\":60, \"extras\": {\"resources\":[\"ROCK\",\"PAPER\"], \"altitude\":-23}}"),etat);
        cmd=ia.takeDecision(etat, etat.getTeam());
        assertEquals(cmd.toJSON().toString(),new Scout(Direction.WEST).toJSON().toString());
        cmd.doResult(new JSONObject("{\"status\": \"ok\", \"cost\":60, \"extras\": {\"resources\":[\"ROCK\",\"PAPER\"], \"altitude\":-23}}"),etat);
        cmd=ia.takeDecision(etat, etat.getTeam());
        assertEquals(cmd.toJSON().toString(),new Glimpse(Direction.NORTH,4).toJSON().toString());
        cmd.doResult(new JSONObject("{ \"status\": \"OK\", \"cost\": 12,\"extras\": {\"asked_range\": 4,\"report\": [[[\"MANGROVE\", 80.0], [\"BEACH\", 20.0]],[[\"MANGROVE\", 40.0], [\"TROPICAL_RAIN_FOREST\", 40.0], [\"TROPICAL_SEASONAL_FOREST\", 20.0]],[\"TROPICAL_RAIN_FOREST\", \"TROPICAL_SEASONAL_FOREST\"],[\"OCEAN\"]]}}"),etat);
        cmd=ia.takeDecision(etat, etat.getTeam());
        assertEquals(cmd.toJSON().toString(),new Glimpse(Direction.EAST,4).toJSON().toString());
        cmd.doResult(new JSONObject("{ \"status\": \"OK\", \"cost\": 12,\"extras\": {\"asked_range\": 4,\"report\": [[[\"MANGROVE\", 80.0], [\"BEACH\", 20.0]],[[\"MANGROVE\", 40.0], [\"TROPICAL_RAIN_FOREST\", 40.0], [\"TROPICAL_SEASONAL_FOREST\", 20.0]],[\"TROPICAL_RAIN_FOREST\", \"TROPICAL_SEASONAL_FOREST\"],[\"OCEAN\"]]}}"),etat);
        cmd=ia.takeDecision(etat, etat.getTeam());
        assertEquals(cmd.toJSON().toString(),new Glimpse(Direction.SOUTH,4).toJSON().toString());
        cmd.doResult(new JSONObject("{ \"status\": \"OK\", \"cost\": 12,\"extras\": {\"asked_range\": 4,\"report\": [[[\"MANGROVE\", 80.0], [\"BEACH\", 20.0]],[[\"MANGROVE\", 40.0], [\"TROPICAL_RAIN_FOREST\", 40.0], [\"TROPICAL_SEASONAL_FOREST\", 20.0]],[\"TROPICAL_RAIN_FOREST\", \"TROPICAL_SEASONAL_FOREST\"],[\"TROPICAL_RAIN_FOREST\"]]}}"),etat);
        cmd=ia.takeDecision(etat, etat.getTeam());
        assertEquals(cmd.toJSON().toString(),new Glimpse(Direction.WEST,4).toJSON().toString());
        cmd.doResult(new JSONObject("{ \"status\": \"OK\", \"cost\": 12,\"extras\": {\"asked_range\": 4,\"report\": [[[\"MANGROVE\", 80.0], [\"BEACH\", 20.0]],[[\"MANGROVE\", 40.0], [\"TROPICAL_RAIN_FOREST\", 40.0], [\"TROPICAL_SEASONAL_FOREST\", 20.0]],[\"TROPICAL_RAIN_FOREST\", \"TROPICAL_SEASONAL_FOREST\"],[\"OCEAN\"]]}}"),etat);
    }
    
    @Test
    public void testMoveWithBiomeResources(){
        cmd=ia.takeDecision(etat, etat.getTeam());
        cmd.doResult(new JSONObject("{\"status\": \"ok\", \"cost\":60}"),etat);
        
        cmd=ia.takeDecision(etat, etat.getTeam());
        cmd.doResult(new JSONObject("{\"status\": \"ok\", \"cost\":60, \"extras\": {\"resources\":[\"ROCK\",\"PAPER\"], \"altitude\":-23}}"),etat);
        cmd=ia.takeDecision(etat, etat.getTeam());
        cmd.doResult(new JSONObject("{\"status\": \"ok\", \"cost\":60, \"extras\": {\"resources\":[\"ROCK\",\"PAPER\"], \"altitude\":-23}}"),etat);
        cmd=ia.takeDecision(etat, etat.getTeam());
        cmd.doResult(new JSONObject("{\"status\": \"ok\", \"cost\":60, \"extras\": {\"resources\":[\"ROCK\",\"PAPER\"], \"altitude\":-23}}"),etat);
        cmd=ia.takeDecision(etat, etat.getTeam());
        cmd.doResult(new JSONObject("{\"status\": \"ok\", \"cost\":60, \"extras\": {\"resources\":[\"ROCK\",\"PAPER\"], \"altitude\":-23}}"),etat);
        cmd=ia.takeDecision(etat, etat.getTeam());
        cmd.doResult(new JSONObject("{ \"status\": \"OK\", \"cost\": 12,\"extras\": {\"asked_range\": 4,\"report\": [[[\"OCEAN\", 80.0], [\"OCEAN\", 20.0]],[[\"OCEAN\", 40.0], [\"OCEAN\", 40.0], [\"OCEAN\", 20.0]],[\"OCEAN\", \"OCEAN\"],[\"OCEAN\"]]}}"),etat);
        cmd=ia.takeDecision(etat, etat.getTeam());
        cmd.doResult(new JSONObject("{ \"status\": \"OK\", \"cost\": 12,\"extras\": {\"asked_range\": 4,\"report\": [[[\"OCEAN\", 80.0], [\"OCEAN\", 20.0]],[[\"OCEAN\", 40.0], [\"OCEAN\", 40.0], [\"OCEAN\", 20.0]],[\"TROPICAL_RAIN_FOREST\", \"TROPICAL_SEASONAL_FOREST\"],[\"OCEAN\"]]}}"),etat);
        cmd=ia.takeDecision(etat, etat.getTeam());
        cmd.doResult(new JSONObject("{ \"status\": \"OK\", \"cost\": 12,\"extras\": {\"asked_range\": 4,\"report\": [[[\"OCEAN\", 80.0], [\"BEACH\", 20.0]],[[\"OCEAN\", 40.0], [\"OCEAN\", 40.0], [\"OCEAN\", 20.0]],[\"TROPICAL_RAIN_FOREST\", \"TROPICAL_SEASONAL_FOREST\"],[\"OCEAN\"]]}}"),etat);
        cmd=ia.takeDecision(etat, etat.getTeam());
        cmd.doResult(new JSONObject("{ \"status\": \"OK\", \"cost\": 12,\"extras\": {\"asked_range\": 4,\"report\": [[[\"OCEAN\", 80.0], [\"OCEAN\", 20.0]],[[\"SHRUBLAND\", 40.0], [\"OCEAN\", 40.0], [\"OCEAN\", 20.0]],[\"TROPICAL_RAIN_FOREST\", \"TROPICAL_SEASONAL_FOREST\"],[\"TROPICAL_RAIN_FOREST\"]]}}"),etat);

        
        cmd=ia.takeDecision(etat, etat.getTeam());
        assertEquals(cmd.toJSON().toString(),new Move(Direction.WEST).toJSON().toString());
    }
    
    @Test
    public void testGlimpseAfterSeveralMoves(){
        //land
        cmd=ia.takeDecision(etat, etat.getTeam());
        cmd.doResult(new JSONObject("{\"status\": \"ok\", \"cost\":60}"),etat);
        
        //scout puis glimpse
        cmd=ia.takeDecision(etat, etat.getTeam());
        cmd.doResult(new JSONObject("{\"status\": \"ok\", \"cost\":60, \"extras\": {\"resources\":[\"ROCK\",\"PAPER\"], \"altitude\":-23}}"),etat);
        cmd=ia.takeDecision(etat, etat.getTeam());
        cmd.doResult(new JSONObject("{\"status\": \"ok\", \"cost\":60, \"extras\": {\"resources\":[\"ROCK\",\"PAPER\"], \"altitude\":-23}}"),etat);
        cmd=ia.takeDecision(etat, etat.getTeam());
        cmd.doResult(new JSONObject("{\"status\": \"ok\", \"cost\":60, \"extras\": {\"resources\":[\"ROCK\",\"PAPER\"], \"altitude\":-23}}"),etat);
        cmd=ia.takeDecision(etat, etat.getTeam());
        cmd.doResult(new JSONObject("{\"status\": \"ok\", \"cost\":60, \"extras\": {\"resources\":[\"ROCK\",\"PAPER\"], \"altitude\":-23}}"),etat);
        cmd=ia.takeDecision(etat, etat.getTeam());
        cmd.doResult(new JSONObject("{ \"status\": \"OK\", \"cost\": 12,\"extras\": {\"asked_range\": 4,\"report\": [[[\"OCEAN\", 80.0], [\"OCEAN\", 20.0]],[[\"OCEAN\", 40.0], [\"OCEAN\", 40.0], [\"OCEAN\", 20.0]],[\"OCEAN\", \"OCEAN\"],[\"OCEAN\"]]}}"),etat);
        cmd=ia.takeDecision(etat, etat.getTeam());
        cmd.doResult(new JSONObject("{ \"status\": \"OK\", \"cost\": 12,\"extras\": {\"asked_range\": 4,\"report\": [[[\"OCEAN\", 80.0], [\"OCEAN\", 20.0]],[[\"OCEAN\", 40.0], [\"OCEAN\", 40.0], [\"OCEAN\", 20.0]],[\"TROPICAL_RAIN_FOREST\", \"TROPICAL_SEASONAL_FOREST\"],[\"OCEAN\"]]}}"),etat);
        cmd=ia.takeDecision(etat, etat.getTeam());
        cmd.doResult(new JSONObject("{ \"status\": \"OK\", \"cost\": 12,\"extras\": {\"asked_range\": 4,\"report\": [[[\"OCEAN\", 80.0], [\"BEACH\", 20.0]],[[\"OCEAN\", 40.0], [\"OCEAN\", 40.0], [\"OCEAN\", 20.0]],[\"TROPICAL_RAIN_FOREST\", \"TROPICAL_SEASONAL_FOREST\"],[\"OCEAN\"]]}}"),etat);
        cmd=ia.takeDecision(etat, etat.getTeam());
        cmd.doResult(new JSONObject("{ \"status\": \"OK\", \"cost\": 12,\"extras\": {\"asked_range\": 4,\"report\": [[[\"OCEAN\", 80.0], [\"OCEAN\", 20.0]],[[\"SHRUBLAND\", 40.0], [\"OCEAN\", 40.0], [\"OCEAN\", 20.0]],[\"TROPICAL_RAIN_FOREST\", \"TROPICAL_SEASONAL_FOREST\"],[\"TROPICAL_RAIN_FOREST\"]]}}"),etat);
        
        //move
        cmd=ia.takeDecision(etat, etat.getTeam());
        cmd.doResult(new JSONObject("{\"status\": \"ok\", \"cost\":60}"),etat);
        cmd=ia.takeDecision(etat, etat.getTeam());
        //scout
        cmd.doResult(new JSONObject("{\"status\": \"ok\", \"cost\":60, \"extras\": {\"resources\":[\"ROCK\",\"PAPER\"], \"altitude\":-23}}"),etat);
        cmd=ia.takeDecision(etat, etat.getTeam());
        cmd.doResult(new JSONObject("{\"status\": \"ok\", \"cost\":60, \"extras\": {\"resources\":[\"ROCK\",\"PAPER\"], \"altitude\":-23}}"),etat);
        cmd=ia.takeDecision(etat, etat.getTeam());
        cmd.doResult(new JSONObject("{\"status\": \"ok\", \"cost\":60, \"extras\": {\"resources\":[\"ROCK\",\"PAPER\"], \"altitude\":-23}}"),etat);
        cmd=ia.takeDecision(etat, etat.getTeam());
        cmd.doResult(new JSONObject("{\"status\": \"ok\", \"cost\":60, \"extras\": {\"resources\":[\"ROCK\",\"PAPER\"], \"altitude\":-23}}"),etat);
        cmd=ia.takeDecision(etat, etat.getTeam());
        //puis bouge direct sans glimpse
        cmd=ia.takeDecision(etat, etat.getTeam());
        assertEquals(cmd.toJSON().toString(),new Move(Direction.WEST).toJSON().toString());
        cmd.doResult(new JSONObject("{\"status\": \"ok\", \"cost\":60}"),etat);

        //scout
        cmd=ia.takeDecision(etat, etat.getTeam());
        cmd.doResult(new JSONObject("{\"status\": \"ok\", \"cost\":60, \"extras\": {\"resources\":[\"ROCK\",\"PAPER\"], \"altitude\":-23}}"),etat);
        cmd=ia.takeDecision(etat, etat.getTeam());
        cmd.doResult(new JSONObject("{\"status\": \"ok\", \"cost\":60, \"extras\": {\"resources\":[\"ROCK\",\"PAPER\"], \"altitude\":-23}}"),etat);
        cmd=ia.takeDecision(etat, etat.getTeam());
        cmd.doResult(new JSONObject("{\"status\": \"ok\", \"cost\":60, \"extras\": {\"resources\":[\"ROCK\",\"PAPER\"], \"altitude\":-23}}"),etat);
        cmd=ia.takeDecision(etat, etat.getTeam());
        //move sans glimpse
        cmd=ia.takeDecision(etat, etat.getTeam());
        assertEquals(cmd.toJSON().toString(),new Move(Direction.WEST).toJSON().toString());
        cmd.doResult(new JSONObject("{\"status\": \"ok\", \"cost\":60}"),etat);
        
        //scout
        cmd=ia.takeDecision(etat, etat.getTeam());
        cmd.doResult(new JSONObject("{\"status\": \"ok\", \"cost\":60, \"extras\": {\"resources\":[\"ROCK\",\"PAPER\"], \"altitude\":-23}}"),etat);
        cmd=ia.takeDecision(etat, etat.getTeam());
        cmd.doResult(new JSONObject("{\"status\": \"ok\", \"cost\":60, \"extras\": {\"resources\":[\"ROCK\",\"PAPER\"], \"altitude\":-23}}"),etat);
        cmd=ia.takeDecision(etat, etat.getTeam());
        cmd.doResult(new JSONObject("{\"status\": \"ok\", \"cost\":60, \"extras\": {\"resources\":[\"ROCK\",\"PAPER\"], \"altitude\":-23}}"),etat);
        //glimpse
        cmd=ia.takeDecision(etat, etat.getTeam());
        assertEquals(cmd.toJSON().toString(),new Glimpse(Direction.NORTH,4).toJSON().toString());
        cmd.doResult(new JSONObject("{ \"status\": \"OK\", \"cost\": 12,\"extras\": {\"asked_range\": 4,\"report\": [[[\"OCEAN\", 80.0], [\"OCEAN\", 20.0]],[[\"OCEAN\", 40.0], [\"OCEAN\", 40.0], [\"OCEAN\", 20.0]],[\"OCEAN\", \"OCEAN\"],[\"OCEAN\"]]}}"),etat);
        cmd=ia.takeDecision(etat, etat.getTeam());
        cmd.doResult(new JSONObject("{ \"status\": \"OK\", \"cost\": 12,\"extras\": {\"asked_range\": 4,\"report\": [[[\"OCEAN\", 80.0], [\"OCEAN\", 20.0]],[[\"OCEAN\", 40.0], [\"OCEAN\", 40.0], [\"OCEAN\", 20.0]],[\"TROPICAL_RAIN_FOREST\", \"TROPICAL_SEASONAL_FOREST\"],[\"OCEAN\"]]}}"),etat);
        cmd=ia.takeDecision(etat, etat.getTeam());
        cmd.doResult(new JSONObject("{ \"status\": \"OK\", \"cost\": 12,\"extras\": {\"asked_range\": 4,\"report\": [[[\"OCEAN\", 80.0], [\"BEACH\", 20.0]],[[\"OCEAN\", 40.0], [\"OCEAN\", 40.0], [\"OCEAN\", 20.0]],[\"TROPICAL_RAIN_FOREST\", \"TROPICAL_SEASONAL_FOREST\"],[\"OCEAN\"]]}}"),etat);
        assertEquals(cmd.toJSON().toString(),new Glimpse(Direction.WEST,4).toJSON().toString());
        
    }
    
    @Test
    public void testExploit(){
        //land
        cmd=ia.takeDecision(etat, etat.getTeam());
        cmd.doResult(new JSONObject("{\"status\": \"ok\", \"cost\":60}"),etat);
        
        //scout puis move
        cmd=ia.takeDecision(etat, etat.getTeam());
        cmd.doResult(new JSONObject("{\"status\": \"ok\", \"cost\":60, \"extras\": {\"resources\":[\"WOOD\",\"PS4\",\"FUR\"], \"altitude\":-23}}"),etat);
        cmd=ia.takeDecision(etat, etat.getTeam());
        assertEquals(cmd.toJSON().toString(),(new Move(Direction.NORTH)).toJSON().toString());
        cmd.doResult(new JSONObject("{\"status\": \"ok\", \"cost\":60}"),etat);
        
        //explore
        cmd=ia.takeDecision(etat, etat.getTeam());
        assertEquals(cmd.toJSON().toString(),(new Explore()).toJSON().toString());
        cmd.doResult(new JSONObject("{\"status\": \"ok\", \"cost\":60,"
                + "\"extras\": {"
                    + "\"resources\": ["
                        + "{\"resource\": \"WOOD\", \"amount\":\"HIGH\",\"cond\":\"EASY\"},"
                        + "{\"resource\": \"PS4\", \"amount\":\"HIGH\",\"cond\":\"EASY\"},"
                        + "{\"resource\": \"FUR\", \"amount\":\"HIGH\",\"cond\":\"EASY\"}"
                    + "],"
                + "\"pois\":[]}"
                + "}"),etat);
        cmd=ia.takeDecision(etat,etat.getTeam());
        assertEquals(cmd.toJSON().toString(),new Exploit("WOOD").toJSON().toString());
        
        cmd=ia.takeDecision(etat,etat.getTeam());
        assertEquals(cmd.toJSON().toString(),new Exploit("PS4").toJSON().toString());

        cmd=ia.takeDecision(etat,etat.getTeam());
        assertEquals(cmd.toJSON().toString(),new Exploit("FUR").toJSON().toString());
        cmd=ia.takeDecision(etat,etat.getTeam());
        assertNotEquals(cmd.toJSON().toString(),new Exploit("FUR").toJSON().toString());
    }
    
    @Test
    public void exploitButNotInGoodCond(){
        //land
        cmd=ia.takeDecision(etat, etat.getTeam());
        cmd.doResult(new JSONObject("{\"status\": \"ok\", \"cost\":60}"),etat);
        //scout and move
        cmd=ia.takeDecision(etat, etat.getTeam());
        cmd.doResult(new JSONObject("{\"status\": \"ok\", \"cost\":60, \"extras\": {\"resources\":[\"WOOD\",\"PS4\",\"FUR\"], \"altitude\":-23}}"),etat);
        cmd=ia.takeDecision(etat, etat.getTeam());
        assertEquals(cmd.toJSON().toString(),(new Move(Direction.NORTH)).toJSON().toString());
        cmd.doResult(new JSONObject("{\"status\": \"ok\", \"cost\":60}"),etat);   
        //explore
        cmd=ia.takeDecision(etat, etat.getTeam());
        assertEquals(cmd.toJSON().toString(),(new Explore()).toJSON().toString());
        cmd.doResult(new JSONObject("{\"status\": \"ok\", \"cost\":60,"
                + "\"extras\": {"
                    + "\"resources\": ["
                        + "{\"resource\": \"WOOD\", \"amount\":\"LOW\",\"cond\":\"HARSH\"},"
                        + "{\"resource\": \"PS4\", \"amount\":\"LOW\",\"cond\":\"HARSH\"}"
                    + "],"
                + "\"pois\":[]}"
                + "}"),etat);
        //scout and move
        cmd=ia.takeDecision(etat,etat.getTeam());
        assertEquals(cmd.toJSON().toString(),new Scout(Direction.NORTH).toJSON().toString());
        cmd.doResult(new JSONObject("{\"status\": \"ok\", \"cost\":60, \"extras\": {\"resources\":[\"PS4\",\"PAPER\"], \"altitude\":-23}}"),etat);
        cmd=ia.takeDecision(etat,etat.getTeam());
        assertEquals(cmd.toJSON().toString(),new Move(Direction.NORTH).toJSON().toString());
        cmd.doResult(new JSONObject("{\"status\": \"ok\", \"cost\":60, \"extras\": {\"resources\":[\"PS4\",\"PAPER\"], \"altitude\":-23}}"),etat);
        //explore
        cmd=ia.takeDecision(etat, etat.getTeam());
        assertEquals(cmd.toJSON().toString(),(new Explore()).toJSON().toString());
        cmd.doResult(new JSONObject("{\"status\": \"ok\", \"cost\":60,"
                + "\"extras\": {"
                    + "\"resources\": ["
                        + "{\"resource\": \"WOOD\", \"amount\":\"LOW\",\"cond\":\"HARSH\"},"
                        + "{\"resource\": \"PS4\", \"amount\":\"LOW\",\"cond\":\"HARSH\"}"
                    + "],"
                + "\"pois\":[]}"
                + "}"),etat);
      //scout and move
        cmd=ia.takeDecision(etat, etat.getTeam());
        cmd.doResult(new JSONObject("{\"status\": \"ok\", \"cost\":60, \"extras\": {\"resources\":[\"WOOD\",\"PS4\",\"FUR\"], \"altitude\":-23}}"),etat);
        cmd=ia.takeDecision(etat, etat.getTeam());
        assertEquals(cmd.toJSON().toString(),(new Move(Direction.NORTH)).toJSON().toString());
        cmd.doResult(new JSONObject("{\"status\": \"ok\", \"cost\":60}"),etat);   
        //explore
        cmd=ia.takeDecision(etat, etat.getTeam());
        assertEquals(cmd.toJSON().toString(),(new Explore()).toJSON().toString());
        cmd.doResult(new JSONObject("{\"status\": \"ok\", \"cost\":60,"
                + "\"extras\": {"
                    + "\"resources\": ["
                        + "{\"resource\": \"WOOD\", \"amount\":\"LOW\",\"cond\":\"HARSH\"},"
                        + "{\"resource\": \"PS4\", \"amount\":\"LOW\",\"cond\":\"HARSH\"}"
                    + "],"
                + "\"pois\":[]}"
                + "}"),etat);
      //scout and move
        cmd=ia.takeDecision(etat, etat.getTeam());
        cmd.doResult(new JSONObject("{\"status\": \"ok\", \"cost\":60, \"extras\": {\"resources\":[\"WOOD\",\"PS4\",\"FUR\"], \"altitude\":-23}}"),etat);
        cmd=ia.takeDecision(etat, etat.getTeam());
        assertEquals(cmd.toJSON().toString(),(new Move(Direction.NORTH)).toJSON().toString());
        cmd.doResult(new JSONObject("{\"status\": \"ok\", \"cost\":60}"),etat);   
        //explore
        cmd=ia.takeDecision(etat, etat.getTeam());
        assertEquals(cmd.toJSON().toString(),(new Explore()).toJSON().toString());
        cmd.doResult(new JSONObject("{\"status\": \"ok\", \"cost\":60,"
                + "\"extras\": {"
                    + "\"resources\": ["
                        + "{\"resource\": \"WOOD\", \"amount\":\"LOW\",\"cond\":\"HARSH\"},"
                        + "{\"resource\": \"PS4\", \"amount\":\"LOW\",\"cond\":\"HARSH\"}"
                    + "],"
                + "\"pois\":[]}"
                + "}"),etat);
        //scout and move
        cmd=ia.takeDecision(etat, etat.getTeam());
        assertEquals(new Scout(Direction.NORTH).toJSON().toString(),cmd.toJSON().toString());
        cmd.doResult(new JSONObject("{\"status\": \"ok\", \"cost\":60, \"extras\": {\"resources\":[\"WOOD\",\"PS4\",\"FUR\"], \"altitude\":-23}}"),etat);
        cmd=ia.takeDecision(etat, etat.getTeam());
        assertEquals(new Move(Direction.NORTH).toJSON().toString(),cmd.toJSON().toString());
        
    }
    
    /*
    @Ignore
    @Test
    public void testMoveIfResourceNeeded(){
        cmd=ia.takeDecision(etat, etat.getTeam());
        assertEquals(cmd.toJSON(),new Land(1,"creek_id").toJSON().toString());
        cmd.doResult("{\"status\": \"ok\", \"cost\":6}",etat);
        assertNotEquals(etat.getMapMonde().getCurrentCase(),null);
        
        cmd=ia.takeDecision(etat, etat.getTeam());
        assertEquals(cmd.toJSON(),new Scout(Direction.NORTH).toJSON());
        cmd.doResult("{\"status\": \"ok\", \"cost\":6, \"extras\": {\"resources\":[\"ROCK\",\"PAPER\"], \"altitude\":-23}}",etat);
        cmd=ia.takeDecision(etat, etat.getTeam());
        assertEquals(cmd.toJSON(),new Scout(Direction.EAST).toJSON());
        cmd.doResult("{\"status\": \"ok\", \"cost\":6, \"extras\": {\"resources\":[\"ROCK\",\"PAPER\"], \"altitude\":-23}}",etat);
        cmd=ia.takeDecision(etat, etat.getTeam());
        assertEquals(cmd.toJSON(),new Scout(Direction.SOUTH).toJSON());
        cmd.doResult("{\"status\": \"ok\", \"cost\":6, \"extras\": {\"resources\":[\"ROCK\",\"PAPER\"], \"altitude\":-23}}",etat);
        cmd=ia.takeDecision(etat, etat.getTeam());
        assertEquals(cmd.toJSON(),new Scout(Direction.WEST).toJSON());
        cmd.doResult("{\"status\": \"ok\", \"cost\":6, \"extras\": {\"resources\":[\"ROCK\",\"PAPER\"], \"altitude\":-23}}",etat);
        cmd=ia.takeDecision(etat, etat.getTeam());
        assertEquals(cmd.toJSON(),new Glimpse(Direction.NORTH,4).toJSON());
        cmd.doResult("{ \"status\": \"OK\", \"cost\": 12,\"extras\": {\"asked_range\": 4,\"report\": [[[\"MANGROVE\", 80.0], [\"BEACH\", 20.0]],[[\"MANGROVE\", 40.0], [\"TROPICAL_RAIN_FOREST\", 40.0], [\"TROPICAL_SEASONAL_FOREST\", 20.0]],[\"TROPICAL_RAIN_FOREST\", \"TROPICAL_SEASONAL_FOREST\"],[\"OCEAN\"]]}}",etat);
        cmd=ia.takeDecision(etat, etat.getTeam());
        assertEquals(cmd.toJSON(),new Glimpse(Direction.EAST,4).toJSON());
        cmd.doResult("{ \"status\": \"OK\", \"cost\": 12,\"extras\": {\"asked_range\": 4,\"report\": [[[\"MANGROVE\", 80.0], [\"BEACH\", 20.0]],[[\"MANGROVE\", 40.0], [\"TROPICAL_RAIN_FOREST\", 40.0], [\"TROPICAL_SEASONAL_FOREST\", 20.0]],[\"TROPICAL_RAIN_FOREST\", \"TROPICAL_SEASONAL_FOREST\"],[\"OCEAN\"]]}}",etat);
        cmd=ia.takeDecision(etat, etat.getTeam());
        assertEquals(cmd.toJSON(),new Glimpse(Direction.SOUTH,4).toJSON());
        cmd.doResult("{ \"status\": \"OK\", \"cost\": 12,\"extras\": {\"asked_range\": 4,\"report\": [[[\"MANGROVE\", 80.0], [\"BEACH\", 20.0]],[[\"MANGROVE\", 40.0], [\"TROPICAL_RAIN_FOREST\", 40.0], [\"TROPICAL_SEASONAL_FOREST\", 20.0]],[\"TROPICAL_RAIN_FOREST\", \"TROPICAL_SEASONAL_FOREST\"],[\"TROPICAL_RAIN_FOREST\"]]}}",etat);
        cmd=ia.takeDecision(etat, etat.getTeam());
        assertEquals(cmd.toJSON(),new Glimpse(Direction.WEST,4).toJSON());
        cmd.doResult("{ \"status\": \"OK\", \"cost\": 12,\"extras\": {\"asked_range\": 4,\"report\": [[[\"MANGROVE\", 80.0], [\"BEACH\", 20.0]],[[\"MANGROVE\", 40.0], [\"TROPICAL_RAIN_FOREST\", 40.0], [\"TROPICAL_SEASONAL_FOREST\", 20.0]],[\"TROPICAL_RAIN_FOREST\", \"TROPICAL_SEASONAL_FOREST\"],[\"OCEAN\"]]}}",etat);
        
        //first move
        cmd=ia.takeDecision(etat, etat.getTeam());
        assertEquals(cmd.toJSON(),new Move(Direction.SOUTH).toJSON());
        cmd.doResult("{\"status\": \"ok\", \"cost\":6}",etat);
        cmd=ia.takeDecision(etat, etat.getTeam());
        assertEquals(cmd.toJSON(),new Scout(Direction.NORTH).toJSON());
        cmd.doResult("{\"status\": \"ok\", \"cost\":6, \"extras\": {\"resources\":[\"ROCK\",\"PAPER\"], \"altitude\":-23}}",etat);
        cmd=ia.takeDecision(etat, etat.getTeam());
        assertEquals(cmd.toJSON(),new Scout(Direction.EAST).toJSON());
        cmd.doResult("{\"status\": \"ok\", \"cost\":6, \"extras\": {\"resources\":[\"ROCK\",\"PAPER\"], \"altitude\":-23}}",etat);
        cmd=ia.takeDecision(etat, etat.getTeam());
        assertEquals(cmd.toJSON(),new Scout(Direction.SOUTH).toJSON());
        cmd.doResult("{\"status\": \"ok\", \"cost\":6, \"extras\": {\"resources\":[\"ROCK\",\"PAPER\"], \"altitude\":-23}}",etat);
        cmd=ia.takeDecision(etat, etat.getTeam());
        assertEquals(cmd.toJSON(),new Scout(Direction.WEST).toJSON());
        cmd.doResult("{\"status\": \"ok\", \"cost\":6, \"extras\": {\"resources\":[\"ROCK\",\"PAPER\"], \"altitude\":-23}}",etat);
        cmd=ia.takeDecision(etat, etat.getTeam());
        assertEquals(cmd.toJSON(),new Glimpse(Direction.EAST,4).toJSON());
        cmd.doResult("{ \"status\": \"OK\", \"cost\": 12,\"extras\": {\"asked_range\": 4,\"report\": [[[\"MANGROVE\", 80.0], [\"BEACH\", 20.0]],[[\"MANGROVE\", 40.0], [\"TROPICAL_RAIN_FOREST\", 40.0], [\"TROPICAL_SEASONAL_FOREST\", 20.0]],[\"TROPICAL_RAIN_FOREST\", \"TROPICAL_SEASONAL_FOREST\"],[\"OCEAN\"]]}}",etat);
        cmd=ia.takeDecision(etat, etat.getTeam());
        assertEquals(cmd.toJSON(),new Glimpse(Direction.WEST,4).toJSON());
        cmd.doResult("{ \"status\": \"OK\", \"cost\": 12,\"extras\": {\"asked_range\": 4,\"report\": [[[\"MANGROVE\", 80.0], [\"BEACH\", 20.0]],[[\"MANGROVE\", 40.0], [\"TROPICAL_RAIN_FOREST\", 40.0], [\"TROPICAL_SEASONAL_FOREST\", 20.0]],[\"TROPICAL_RAIN_FOREST\", \"TROPICAL_SEASONAL_FOREST\"],[\"OCEAN\"]]}}",etat);
        
        //second move
        cmd=ia.takeDecision(etat, etat.getTeam());
        assertEquals(cmd.toJSON(),new Move(Direction.SOUTH).toJSON());
        cmd.doResult("{\"status\": \"ok\", \"cost\":6}",etat);
        cmd=ia.takeDecision(etat, etat.getTeam());
        assertEquals(cmd.toJSON(),new Scout(Direction.EAST).toJSON());
        cmd.doResult("{\"status\": \"ok\", \"cost\":6, \"extras\": {\"resources\":[\"ROCK\",\"PAPER\"], \"altitude\":-23}}",etat);
        cmd=ia.takeDecision(etat, etat.getTeam());
        assertEquals(cmd.toJSON(),new Scout(Direction.SOUTH).toJSON());
        cmd.doResult("{\"status\": \"ok\", \"cost\":6, \"extras\": {\"resources\":[\"ROCK\",\"PAPER\"], \"altitude\":-23}}",etat);
        cmd=ia.takeDecision(etat, etat.getTeam());
        assertEquals(cmd.toJSON(),new Scout(Direction.WEST).toJSON());
        cmd.doResult("{\"status\": \"ok\", \"cost\":6, \"extras\": {\"resources\":[\"ROCK\",\"PAPER\"], \"altitude\":-23}}",etat);
        cmd=ia.takeDecision(etat, etat.getTeam());
        assertEquals(cmd.toJSON(),new Glimpse(Direction.EAST,4).toJSON());
        cmd.doResult("{ \"status\": \"OK\", \"cost\": 12,\"extras\": {\"asked_range\": 4,\"report\": [[[\"MANGROVE\", 80.0], [\"BEACH\", 20.0]],[[\"MANGROVE\", 40.0], [\"TROPICAL_RAIN_FOREST\", 40.0], [\"TROPICAL_SEASONAL_FOREST\", 20.0]],[\"TROPICAL_RAIN_FOREST\", \"TROPICAL_SEASONAL_FOREST\"],[\"TROPICAL_SEASONAL_FOREST\"]]}}",etat);
        cmd=ia.takeDecision(etat, etat.getTeam());
        assertEquals(cmd.toJSON(),new Glimpse(Direction.WEST,4).toJSON());
        cmd.doResult("{ \"status\": \"OK\", \"cost\": 12,\"extras\": {\"asked_range\": 4,\"report\": [[[\"MANGROVE\", 80.0], [\"BEACH\", 20.0]],[[\"MANGROVE\", 40.0], [\"TROPICAL_RAIN_FOREST\", 40.0], [\"TROPICAL_SEASONAL_FOREST\", 20.0]],[\"TROPICAL_RAIN_FOREST\", \"TROPICAL_SEASONAL_FOREST\"],[\"OCEAN\"]]}}",etat);
        
        //third move
        cmd=ia.takeDecision(etat, etat.getTeam());
        assertEquals(cmd.toJSON(),new Move(Direction.SOUTH).toJSON());
        cmd.doResult("{\"status\": \"ok\", \"cost\":6}",etat);
        cmd=ia.takeDecision(etat, etat.getTeam());
        assertEquals(cmd.toJSON(),new Scout(Direction.EAST).toJSON());
        cmd.doResult("{\"status\": \"ok\", \"cost\":6, \"extras\": {\"resources\":[\"ROCK\",\"PAPER\"], \"altitude\":-23}}",etat);
        cmd=ia.takeDecision(etat, etat.getTeam());
        assertEquals(cmd.toJSON(),new Scout(Direction.SOUTH).toJSON());
        cmd.doResult("{\"status\": \"ok\", \"cost\":6, \"extras\": {\"resources\":[], \"altitude\":0,\"unreachable\":true}}",etat);
        cmd=ia.takeDecision(etat, etat.getTeam());
        assertEquals(cmd.toJSON(),new Scout(Direction.WEST).toJSON());
        cmd.doResult("{\"status\": \"ok\", \"cost\":6, \"extras\": {\"resources\":[\"ROCK\",\"PAPER\"], \"altitude\":-23}}",etat);
        cmd=ia.takeDecision(etat, etat.getTeam());
        assertEquals(cmd.toJSON(),new Glimpse(Direction.EAST,4).toJSON());
        cmd.doResult("{ \"status\": \"OK\", \"cost\": 12,\"extras\": {\"asked_range\": 4,\"report\": [[[\"MANGROVE\", 80.0], [\"BEACH\", 20.0]],[[\"MANGROVE\", 40.0], [\"TROPICAL_RAIN_FOREST\", 40.0], [\"TROPICAL_SEASONAL_FOREST\", 20.0]],[\"TROPICAL_RAIN_FOREST\", \"TROPICAL_SEASONAL_FOREST\"],[\"LAKE\"]]}}",etat);
        cmd=ia.takeDecision(etat, etat.getTeam());
        assertEquals(cmd.toJSON(),new Glimpse(Direction.SOUTH,4).toJSON());
        cmd.doResult("{ \"status\": \"OK\", \"cost\": 12,\"extras\": {\"asked_range\": 4,\"report\": [[[\"MANGROVE\", 80.0], [\"BEACH\", 20.0]],[[\"MANGROVE\", 40.0], [\"TROPICAL_RAIN_FOREST\", 40.0], [\"TROPICAL_SEASONAL_FOREST\", 20.0]]]}}",etat);
        cmd=ia.takeDecision(etat, etat.getTeam());
        assertEquals(cmd.toJSON(),new Glimpse(Direction.WEST,4).toJSON());
        cmd.doResult("{ \"status\": \"OK\", \"cost\": 12,\"extras\": {\"asked_range\": 4,\"report\": [[[\"MANGROVE\", 80.0], [\"BEACH\", 20.0]],[[\"MANGROVE\", 40.0], [\"TROPICAL_RAIN_FOREST\", 40.0], [\"TROPICAL_SEASONAL_FOREST\", 20.0]],[\"TROPICAL_RAIN_FOREST\", \"TROPICAL_SEASONAL_FOREST\"],[\"OCEAN\"]]}}",etat);
        
        cmd=ia.takeDecision(etat, etat.getTeam());
        assertEquals(cmd.toJSON(),new Move(Direction.NORTH).toJSON());
        cmd.doResult("{\"status\": \"ok\", \"cost\":6}",etat);
    }*/
}
