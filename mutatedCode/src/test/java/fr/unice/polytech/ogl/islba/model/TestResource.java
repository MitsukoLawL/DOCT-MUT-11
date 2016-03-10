package fr.unice.polytech.ogl.islba.model;

import static org.junit.Assert.*;

import org.junit.Test;

import fr.unice.polytech.ogl.islba.model.resource.Resource;

public class TestResource {

	@Test
	public void testCreationRessource(){
		Resource ressource = new Resource("Fish", "medium", "hard");
		assertEquals("Fish", ressource.getName());
		assertEquals("medium", ressource.getAmount());
		assertEquals("hard", ressource.getDifficulty());
		
		ressource.setAmount("low");
		ressource.setDifficulty("medium");
		
		assertEquals("low", ressource.getAmount());
		assertEquals("medium", ressource.getDifficulty());
	}
	
	@Test
	public void testEqualityRessource(){
		Resource ressource1 = new Resource("Fish", "medium", "hard");
		Resource ressource2 = new Resource("Fish", "high", "hard");
		
		assertTrue(ressource1.equals(ressource2));
	}
	
}