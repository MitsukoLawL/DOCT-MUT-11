package fr.unice.polytech.ogl.islba.command;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import fr.unice.polytech.ogl.islba.model.Biome;
import fr.unice.polytech.ogl.islba.model.Coordonnees;
import fr.unice.polytech.ogl.islba.model.Direction;
import fr.unice.polytech.ogl.islba.model.EtatDeJeu;

import org.json.JSONObject;
import org.junit.Test;
public class TestCommandGlimpse {

	/**
	 * Test the String produce by the command
	 */
	@Test
	public void testToString(){
		String commande = "{\"action\":\"glimpse\",\"parameters\":{\"range\":2,\"direction\":\"N\"}}";
		Glimpse commandeGlimpse = new Glimpse(Direction.NORTH, 2);
		assertEquals(commande, commandeGlimpse.toJSON().toString());
	}
	
	/**
	 *  check if the command update correctly etatDeJeu
	 */
	@Test
	public  void  testResult(){
	    Map<String, Integer> resources = new HashMap<String, Integer>();
		EtatDeJeu jeu = new EtatDeJeu(1000, "osef", 500,resources);

        Glimpse commandeGlimpse = new Glimpse(Direction.NORTH, 4);
        jeu.getMapMonde().setCurrentCoo(new Coordonnees(0, 0));
		commandeGlimpse.doResult(new JSONObject("{ \"status\": \"OK\", \"cost\": 12,\"extras\": {\"asked_range\": 4,\"report\": [[[\"MANGROVE\", 80.0], [\"BEACH\", 20.0]],[[\"MANGROVE\", 40.0], [\"TROPICAL_RAIN_FOREST\", 40.0], [\"TROPICAL_SEASONAL_FOREST\", 20.0]],[\"TROPICAL_RAIN_FOREST\", \"TROPICAL_SEASONAL_FOREST\"],[\"TROPICAL_RAIN_FOREST\"]]}}"), jeu);	
		assertEquals(0,jeu.getMapMonde().getCase(new Coordonnees(0, 0)).getNumberCaseGlimpse());
		assertTrue(jeu.getMapMonde().getCase(new Coordonnees(0, 0)).hasBiome(Biome.MANGROVE));
		assertEquals(80.0, jeu.getMapMonde().getCase(new Coordonnees(0, 0)).getBiomePercentage(Biome.MANGROVE), 0);
		
		assertEquals(1,jeu.getMapMonde().getCase(new Coordonnees(0, 1)).getNumberCaseGlimpse());
		assertEquals(2,jeu.getMapMonde().getCase(new Coordonnees(0, 2)).getNumberCaseGlimpse());
		assertEquals(3,jeu.getMapMonde().getCase(new Coordonnees(0, 3)).getNumberCaseGlimpse());
		assertTrue(jeu.getMapMonde().getCase(new Coordonnees(0, 3)).hasBiome(Biome.TROPICAL_RAIN_FOREST));
		assertEquals(-1, jeu.getMapMonde().getCase(new Coordonnees(0, 3)).getBiomePercentage(Biome.MANGROVE), 0);
	}
	
	/**
	 * Check if the outOfMapCase are correctly manage
	 */
	@Test
	public void outOfMap(){
	    Map<String, Integer> resources = new HashMap<String, Integer>();
		EtatDeJeu jeu = new EtatDeJeu(1000, "osef", 500,resources);
		Glimpse commandeGlimpse = new Glimpse(Direction.NORTH, 4);
		jeu.getMapMonde().setCurrentCoo(new Coordonnees(0, 0));
		commandeGlimpse.doResult(new JSONObject("{ \"status\": \"OK\", \"cost\": 12,\"extras\": {\"asked_range\": 4,\"report\": [[[\"MANGROVE\", 80.0], [\"BEACH\", 20.0]],[[\"MANGROVE\", 40.0], [\"TROPICAL_RAIN_FOREST\", 40.0], [\"TROPICAL_SEASONAL_FOREST\", 20.0]],[\"TROPICAL_RAIN_FOREST\", \"TROPICAL_SEASONAL_FOREST\"]]}}"), jeu);	
		assertEquals(0,jeu.getMapMonde().getCase(new Coordonnees(0, 0)).getNumberCaseGlimpse());
		
		assertTrue(jeu.getMapMonde().getCase(new Coordonnees(0, 2)).isOnMap());
		assertFalse(jeu.getMapMonde().getCase(new Coordonnees(0, 3)).isOnMap());
	}
}
