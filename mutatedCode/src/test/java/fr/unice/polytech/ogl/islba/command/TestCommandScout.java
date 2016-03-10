package fr.unice.polytech.ogl.islba.command;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import fr.unice.polytech.ogl.islba.model.*;

import org.json.JSONObject;
import org.junit.Test;

import fr.unice.polytech.ogl.islba.model.Direction;

import java.util.HashMap;

public class TestCommandScout {
    private EtatDeJeu etat = new EtatDeJeu(600, "creek_id",50,new HashMap<String, Integer>());

    /**
     * Test la reaction si une commande scout nous indique le vide
     *
     * [INDICATION PIAZZA]
     * Apres avoir fait un scout dans le vide, on obtient quelque chose comme ca :
     * String reponse = "{ \"status\": \"OK\", \"cost\": 8, \"extras\": { \"resources\": [], \"altitude\": 0, \"unreachable\": true } }";
     * Le "unreachable":true previens que si on y va, on tombe dans le vide
     * Appeler la commande [A DEFINIR]
     **/
    @Test
    public void TestEffetDeBord() {
        Command c;
        c = new Land(etat.getTeam().getMAXMEN()/2, etat.getCreekID());
        c.doResult(new JSONObject("{\"status\":\"OK\",\"cost\":12}"), etat);

        c = new Scout(Direction.NORTH);
        c.doResult(new JSONObject("{ \"status\": \"OK\", \"cost\": 8, \"extras\": { \"resources\": [], \"altitude\": 0, \"unreachable\": true } }"), etat);

        Coordonnees nextCoo = Coordonnees.add(Direction.NORTH.getCoo(), etat.getMapMonde().getCurrentCoo());
        assertFalse(etat.getMapMonde().getCase(nextCoo).isOnMap());

    }

    @Test
    public void TestMauvaiseDirection() {
        // scout prend en parametre une direction écrite dans un enum
        // --> On peut pas ecrire de direction inexistante, non?

    }

    /**
     * Test si le caseScouted est mise à jour dans l'etat
     */
    @Test
    public void TestMajEdJ() {
        Command c;
        c = new Land(etat.getTeam().getMAXMEN()/2, etat.getCreekID());
        c.doResult(new JSONObject("{\"status\":\"OK\",\"cost\":12}"), etat);

        c = new Scout(Direction.NORTH);
        //Simulation d'un resultat
        c.doResult(new JSONObject("{\"status\":\"OK\",\"cost\":35,\"extras\": {\"resources\":[\"WOOD\",\"FUR\",\"FLOWER\"],\"alitude\":-23}}"),etat);

        Case caseScouted = etat.getMapMonde().getCase(Coordonnees.add(Direction.NORTH.getCoo(), etat.getMapMonde().getCurrentCoo()));

        assertTrue(caseScouted.getScouted());
    }
    /**
     * Test Deux Scout dans la meme direction et vérifie que la case existe dans l'etat
     * ets sont identiques
     */
    @Test
    public void TestMemeScout() {
        Command c;
        c = new Land(etat.getTeam().getMAXMEN()/2, etat.getCreekID());
        c.doResult(new JSONObject("{\"status\":\"OK\",\"cost\":12}"), etat);

        //Scouter deux fois la mm case pr voir si on obtient la mm chose?
        c = new Scout(Direction.NORTH);
        c.doResult(new JSONObject("{\"status\":\"OK\",\"cost\":35,\"extras\": {\"resources\":[\"WOOD\",\"FUR\",\"FLOWER\"],\"alitude\":-23}}"),etat);
        Case c1 = etat.getMapMonde().getCase(Coordonnees.add(Direction.NORTH.getCoo(), etat.getMapMonde().getCurrentCoo()));

        c = new Scout(Direction.NORTH);
        c.doResult(new JSONObject("{\"status\":\"OK\",\"cost\":35,\"extras\": {\"resources\":[\"WOOD\",\"FUR\",\"FLOWER\"],\"alitude\":-23}}"),etat);
        Case c2 = etat.getMapMonde().getCase(Coordonnees.add(Direction.NORTH.getCoo(), etat.getMapMonde().getCurrentCoo()));

        assertTrue(c1 != null);
        assertEquals(c1,c2);
    }

    /**
     * Scout dans une direction,
     * et verifie qu'on a bien enregistré les resources que la commande nous a renvoyé
     */
    @Test
    public void testResource() {
        Command c;
        c = new Land(etat.getTeam().getMAXMEN()/2, etat.getCreekID());
        c.doResult(new JSONObject("{\"status\":\"OK\",\"cost\":12}"), etat);

        c = new Scout(Direction.NORTH);
        //Simulation d'un resultat
        c.doResult(new JSONObject("{\"status\":\"OK\",\"cost\":35,\"extras\": {\"resources\":[\"WOOD\",\"FUR\",\"FLOWER\"],\"alitude\":-23}}"),etat);

        Case caseScouted = etat.getMapMonde().getCase(Coordonnees.add(Direction.NORTH.getCoo(), etat.getMapMonde().getCurrentCoo()));
        assertTrue(caseScouted.hasRessource("WOOD"));
        assertTrue(caseScouted.hasRessource("FUR"));
        assertTrue(caseScouted.hasRessource("FLOWER"));
        assertFalse(caseScouted.hasRessource("foo"));
    }


    /**
     * Test le JSon d'un scout north
     */
	@Test 
	public void TestScoutNorth(){
		Scout scoutN = new Scout(Direction.NORTH);
		String dir[] = {"E","W","S"};
		
		JSONObject obj= new JSONObject();
		JSONObject param= new JSONObject();
		
		obj.put("action", "scout");
		
		param.put("direction", "N");
        obj.put("parameters", param);
        assertTrue(scoutN.toJSON().toString().equals(obj.toString()));

		for(int i = 0; i < dir.length; i++){
			param.put("direction", dir[i]);
	        obj.put("parameters", param); //pas de .toString()
	        assertFalse(scoutN.toJSON().toString().equals(obj.toString()));

		}
	}
		
    @Test
    public void TestScoutSouth(){
        Scout scoutS = new Scout(Direction.SOUTH);
        String dir[] = {"E","W","N"};

        JSONObject obj= new JSONObject();
        JSONObject param= new JSONObject();

        obj.put("action", "scout");

        param.put("direction", "S");
        obj.put("parameters", param);
        assertTrue(scoutS.toJSON().toString().equals(obj.toString()));

        for(int i = 0; i < dir.length; i++){
            param.put("direction", dir[i]);
            obj.put("parameters", param);
            assertFalse(scoutS.toJSON().toString().equals(obj.toString()));

        }
	}

    /**
     * Test le JSon d'un scout east
     */
    @Test
    public void TestScoutEast(){
        Scout scoutE = new Scout(Direction.EAST);
        String dir[] = {"S","W","N"};

        JSONObject obj= new JSONObject();
        JSONObject param= new JSONObject();

        obj.put("action", "scout");

        param.put("direction", "E");
        obj.put("parameters", param);
        assertTrue(scoutE.toJSON().toString().equals(obj.toString()));

        for(int i = 0; i < dir.length; i++){
            param.put("direction", dir[i]);
            obj.put("parameters", param);
            assertFalse(scoutE.toJSON().toString().equals(obj.toString()));

        }
	}

    /**
     * Test le JSon d'un scout WEST
     */
    @Test
    public void TestScoutWest(){
        Scout scoutW = new Scout(Direction.WEST);
        String dir[] = {"S","E","N"};

        JSONObject obj= new JSONObject();
        JSONObject param= new JSONObject();

        obj.put("action", "scout");

        param.put("direction", "W");
        obj.put("parameters", param);
        assertTrue(scoutW.toJSON().toString().equals(obj.toString()));

        for(int i = 0; i < dir.length; i++){
            param.put("direction", dir[i]);
            obj.put("parameters", param);
            assertFalse(scoutW.toJSON().toString().equals(obj.toString()));

        }
	}
}

