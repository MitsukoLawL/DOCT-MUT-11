package fr.unice.polytech.ogl.islba.command;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.junit.Test;

import fr.unice.polytech.ogl.islba.model.Coordonnees;
import fr.unice.polytech.ogl.islba.model.EtatDeJeu;

public class TestCommandTransform {

	@Test
	public void initTransformNULL(){
		Transform command = new Transform(null);	
		command.toJSON();
	}
	
	@Test
	public void initTransform(){
		HashMap<String, Integer> resources = new HashMap<String, Integer>();
		resources.put("SUGAR_CANE", 100);
		resources.put("FRUIT", 10);
		
		Transform command = new Transform(resources);
		JSONObject transform = command.toJSON();
		assertEquals("transform",transform.getString("action"));
		assertTrue(transform.getJSONObject("parameters").getInt("SUGAR_CANE")==100);
		assertTrue(transform.getJSONObject("parameters").getInt("FRUIT")==10);
	}
	
	@Test
	public void testdoResult(){
		HashMap<String, Integer> resources = new HashMap<String, Integer>();
		resources.put("SUGAR_CANE", 100);
		resources.put("FRUITS", 10);
		
		Transform command = new Transform(resources);
		
		Map<String, Integer> objective = new HashMap<String, Integer>();
		objective.put("RUM", 9);
		
		JSONObject res = new JSONObject("{\"status\":\"OK\",cost:12,\"extras\":{\"kind\":\"RUM\",\"production\":9}}");
		EtatDeJeu etat = new EtatDeJeu(500, "creek", 10, objective);
		etat.getMapMonde().setCurrentCoo(new Coordonnees(0, 0));
		etat.getTeam().exploitResource("SUGAR_CANE", 100);
		etat.getTeam().exploitResource("FRUITS", 10);
		
		command.doResult(res, etat);
		
		assertEquals(etat.getTeam().getAmountInInventory("SUGAR_CANE"),0);
		assertEquals(etat.getTeam().getAmountInInventory("FRUITS"),0);
		assertTrue(etat.getTeam().noMoreObjectives());
	}
	
}
