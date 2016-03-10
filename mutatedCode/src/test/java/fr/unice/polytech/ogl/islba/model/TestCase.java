package fr.unice.polytech.ogl.islba.model;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import fr.unice.polytech.ogl.islba.model.resource.Resource;

public class TestCase {

    @Test
    public void testInitCase() {
        Case c = new Case();

        assertFalse(c.getScouted());
        assertFalse(c.getMoveOn());
        assertEquals(c.getNumberCaseGlimpse(),-1);
    }

    @Test
    public void testAddRessource() {
        Resource r = new Resource("WOOD",null,null);
        Case c = new Case();
        
        assertTrue(c.getRessources().isEmpty());
        
        c.addRessource(r);
        assertTrue(c.getRessources().contains(r));
        assertTrue(c.hasRessource(r.getName()));
        
        assertFalse(c.hasRessource("NOT A RESSOURCE"));
        
        
    }

    @Test
    public void testAddBiome() {
        Case c = new Case();
        c.addBiome("TROPICAL_SEASONAL_FOREST", 42.0);
        
        assertTrue(c.hasBiome(Biome.TROPICAL_SEASONAL_FOREST));
        
        assertFalse(c.hasBiome(Biome.LAKE));
        
        assertTrue(c.getBiomePercentage(Biome.TROPICAL_SEASONAL_FOREST)==42.0);
        assertTrue(c.getBiomePercentage(Biome.LAKE)==-1);
    }

    @Test
    public void testIsOnMap() {
        Case c = new Case();
        
        assertTrue(c.isOnMap());
    }
    
    @Test
    public void testGlimpse(){
        Case c = new Case();
        
        c.setNumberCaseGlimpse(3);
        assertTrue(c.getNumberCaseGlimpse()==3);
    }
    
    @Test
    public void testMoveAndScout(){
        Case c = new Case();
        
       c.setMoveOn(true);
       assertTrue(c.getMoveOn());
       
       c.setScouted(true);
       assertTrue(c.getScouted());
    }
    
    @Test
    public void testResourceWithAmountAndCond(){
        Case c = new Case();
        
        Resource res = new Resource("croquettes", "HIGH", "FAIR");
        Resource res2 = new Resource("crocodile","LOW","HARSH");
        Resource res3 = new Resource("machin","HIGH","HARSH");
        Resource res4 = new Resource("truc", "LOW", "FAIR");
        Resource resWithNull = new Resource("null resource", null, null);
        Resource resWithAmountNull = new Resource("null amount",null, "FAIR");
        Resource resWithCondNull = new Resource("null cond","HIGH", null);
        
        c.addRessource(res);
        c.addRessource(res2);
        c.addRessource(res3);
        c.addRessource(res4);
        c.addRessource(resWithNull);
        c.addRessource(resWithAmountNull);
        c.addRessource(resWithCondNull);
    
        List<String> resources = c.getResourcesWithAmountAndCond("HIGH", "FAIR");
        
        assertTrue(resources.contains(res.getName()));
        assertTrue(resources.size()==1);
    }
}
