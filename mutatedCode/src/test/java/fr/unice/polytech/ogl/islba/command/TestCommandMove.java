package fr.unice.polytech.ogl.islba.command;

import static org.junit.Assert.*;
import fr.unice.polytech.ogl.islba.model.*;

import org.json.JSONObject;
import org.junit.Test;

import fr.unice.polytech.ogl.islba.model.Direction;

import java.util.HashMap;

public class TestCommandMove {
    private EtatDeJeu etat = new EtatDeJeu(600, "creek_id",50,new HashMap<String, Integer>());

    @Test
    public void TestEffetDeBord() {
        //Test dans TestCommandScout

        //Apres avoir fait un scout dans le vide, on obtient quelque chose comme ca :
        //String reponse = "{ \"status\": \"OK\", \"cost\": 8, \"extras\": { \"resources\": [], \"altitude\": 0, \"unreachable\": true } }";
        // Le "unreachable":true previens que si on y va, on tombe dans le vide
        // Appeler la commande stop !
    }

    @Test
    public void TestMauvaiseDirection() {
        // move prend en parametre une direction écrite dans un enum
        // --> On ne peut pas ecrire de direction inexistante, non?
    }

    //@Ignore

    /**
     * Teste si l'état de jeu se met bien à jour
     *
     * Simulation :
     * Land (avec un retour bateau :"{\"status\":\"OK\",\"cost\":12}")
     * Verifie que la case et les coordonnées ne sont pas nulles
     */
    @Test
    public void TestMajEdJ() {
        Command c;
        c = new Land(etat.getTeam().getMAXMEN()/2, etat.getCreekID());
        c.doResult(new JSONObject("{\"status\":\"OK\",\"cost\":12}"), etat);

        Case case1 = etat.getMapMonde().getCurrentCase();
        Coordonnees coo1 = etat.getMapMonde().getCurrentCoo();
        assertTrue(case1 != null);
        assertTrue(coo1 != null);
    }

    /**
     * Test si on retourne bien au meme endroit si on fait un move North + move South
     *
     * Simulation :
     * land, move north, move south
     * avec des retour bateau : "{\"status\":\"OK\",\"cost\":12}"
     */
    @Test
    public void TestDeuxMoves() {
        Command c;
        Case debut, intermed, fin;
        Coordonnees debutCoo, intermedCoo, finCoo;
        c = new Land(etat.getTeam().getMAXMEN()/2, etat.getCreekID());
        c.doResult(new JSONObject("{\"status\":\"OK\",\"cost\":12}"), etat);
        debut = etat.getMapMonde().getCurrentCase();
        debutCoo = etat.getMapMonde().getCurrentCoo();

        c = new Move(Direction.NORTH);
        c.doResult(new JSONObject("{\"status\":\"OK\",\"cost\":21}"), etat);
        intermed = etat.getMapMonde().getCurrentCase();
        intermedCoo = etat.getMapMonde().getCurrentCoo();

        c = new Move(Direction.SOUTH);
        c.doResult(new JSONObject("{\"status\":\"OK\",\"cost\":21}"), etat);
        fin = etat.getMapMonde().getCurrentCase();
        finCoo = etat.getMapMonde().getCurrentCoo();

        assertNotEquals(debut, intermed);
        assertNotEquals(fin, intermed);
        assertEquals(debut, fin);

        assertNotEquals(debutCoo, intermedCoo);
        assertNotEquals(finCoo, intermedCoo);
        assertEquals(debutCoo, finCoo);
    }

	@Test
	public void TestMoveNorth(){
		Move moveN = new Move(Direction.NORTH);
		String dir[] = {"E","W","S"};
		
		JSONObject obj= new JSONObject();
		JSONObject param= new JSONObject();
		
		obj.put("action", "move_to");
		
		param.put("direction", "N");
        obj.put("parameters", param); //param.toString(), il n'aime pas!!
        assertTrue(moveN.toJSON().toString().equals(obj.toString()));

		for(int i = 0; i < dir.length; i++){

            param.put("direction", dir[i]);
	        obj.put("parameters", param); //param : {"action":"move_to","parameters":{"direction":"E"}}
                                        //param.toString : {"action":"move_to","parameters":"{\"direction\":\"E\"}"} <-- PAS BIEN !!
            assertFalse(moveN.toJSON().toString().equals(obj.toString()));
		}
	}

		@Test 
		public void TestMoveSouth(){
            Move moveS = new Move(Direction.SOUTH);
			String dir[] = {"E","W","N"};
			
			JSONObject obj= new JSONObject();
			JSONObject param= new JSONObject();
			
			obj.put("action", "move_to");
			
			param.put("direction", "S");
	        obj.put("parameters", param); //param.toString() il n'aime pas !!
	        assertTrue(moveS.toJSON().toString().equals(obj.toString()));

			for(int i = 0; i < dir.length; i++){
				param.put("direction", dir[i]);
		        obj.put("parameters", param);
		        assertFalse(moveS.toJSON().toString().equals(obj.toString()));

			}
	}
		
		@Test 
		public void TestMoveEast(){
			Move moveE = new Move(Direction.EAST);
			String dir[] = {"S","W","N"};
			
			JSONObject obj= new JSONObject();
			JSONObject param= new JSONObject();
			
			obj.put("action", "move_to");
			
			param.put("direction", "E");
	        obj.put("parameters", param); //param.toString() il n'aime pas !!
	        assertTrue(moveE.toJSON().toString().equals(obj.toString()));

			for(int i = 0; i < dir.length; i++){
				param.put("direction", dir[i]);
		        obj.put("parameters", param);
		        assertFalse(moveE.toJSON().toString().equals(obj.toString()));

			}
	}
		
		@Test 
		public void TestMoveWest(){
			Move moveW = new Move(Direction.WEST);
			String dir[] = {"S","E","N"};
			
			JSONObject obj= new JSONObject();
			JSONObject param= new JSONObject();
			
			obj.put("action", "move_to");
			
			param.put("direction", "W");
	        obj.put("parameters", param); //param.toString() il n'aime pas !!
	        assertTrue(moveW.toJSON().toString().equals(obj.toString()));

			for(int i = 0; i < dir.length; i++){
				param.put("direction", dir[i]);
		        obj.put("parameters", param);
		        assertFalse(moveW.toJSON().toString().equals(obj.toString()));

			}
	}
}
