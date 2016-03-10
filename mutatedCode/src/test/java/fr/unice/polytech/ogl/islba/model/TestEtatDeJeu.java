package fr.unice.polytech.ogl.islba.model;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class TestEtatDeJeu {

	@Test
	public void testCreationVide(){
		EtatDeJeu jeu = new EtatDeJeu();
		assertNull(jeu.getMapMonde().getCurrentCase());
		assertNull(jeu.getCreekID());
		assertNull(jeu.getMapMonde().getCurrentCoo());
		assertNull(jeu.getTeam());
	}
	
	@Test
	public void testCreation(){
		Map<String, Integer> resources = new HashMap<String, Integer>();
		resources.put("Fish", 50);
		EtatDeJeu jeu = new EtatDeJeu((5000), "creekID", 5, resources);
		assertEquals("creekID", jeu.getCreekID());
		assertEquals(5000, jeu.getPA());
	}
	
	@Test
	public void testGestionPA(){
	    Map<String, Integer> resources = new HashMap<String, Integer>();
		EtatDeJeu jeu = new EtatDeJeu((5000), "creekID", 5, resources);
		jeu.reducePA(1000);
		assertEquals(4000, jeu.getPA());
	}
	
	@Test
	public void testGestionCase(){
	    Map<String, Integer> resources = new HashMap<String, Integer>();
		EtatDeJeu jeu = new EtatDeJeu((5000), "creekID", 5, resources);
		jeu.getMapMonde().setCurrentCoo(new Coordonnees(0, 0));
		Case c = new Case();
		jeu.getMapMonde().addCaseMap(new Coordonnees(0, 0), c);
		
		Case  c1 = new Case();
		jeu.getMapMonde().addCaseMap(new Coordonnees(0, 1), c1);

		assertEquals(c, jeu.getMapMonde().getCurrentCase());
		assertEquals(new Coordonnees(0, 0), jeu.getMapMonde().getCurrentCoo());
		
		assertEquals(c1,jeu.getMapMonde().getCase(new Coordonnees(0, 1)));
		assertFalse(c.equals(jeu.getMapMonde().getCase(new Coordonnees(0, 1))));
	}
}
