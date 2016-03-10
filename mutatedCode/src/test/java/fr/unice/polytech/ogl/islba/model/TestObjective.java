package fr.unice.polytech.ogl.islba.model;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import fr.unice.polytech.ogl.islba.model.resource.PrimaryResource;
import fr.unice.polytech.ogl.islba.model.resource.Resource;
import fr.unice.polytech.ogl.islba.model.resource.SecondaryResource;

public class TestObjective {
    
    Map<String,Integer> objectives;
    @Before
    public void begin(){
        objectives = new HashMap<String, Integer>();
        objectives.put("FRUITS", 4400);
        objectives.put("GLASS", 800);
        objectives.put("QUARTZ",100);
        //42, that's all
        objectives.put("FUR",42);
        
        //IT'S LESS THAN 9000
        objectives.put("VEGETA",-9001);
        //0 fuck was given this day
        objectives.put("FUCK",0);
    }
    
    @Test
    public void testConstructor() {
        Objective obj = new Objective(objectives);

        assertEquals(obj,obj);
        assertEquals(4,obj.getObjectives().size());
        assertEquals(Rarity.values().length,obj.getObjectivesByRarity().size());
        assertFalse(obj.isObjective(new Resource("VEGETA")));
        assertFalse(obj.isObjective(new Resource("FUCK")));
        assertTrue(obj.isObjective(new Resource("FRUITS")));
        assertTrue(obj.isObjective(new Resource("GLASS")));
        assertTrue(obj.isObjective(new Resource("FUR")));
        assertTrue(obj.isObjective(new Resource("QUARTZ")));
        
    }

    @Test
    public void testGetRarityOf(){
        Objective obj = new Objective(objectives);
        assertEquals(Rarity.COMMON,obj.getRarityOf(new Resource("FRUITS")));
        assertEquals(Rarity.RARE,obj.getRarityOf(new Resource("FUR")));
        assertNull(obj.getRarityOf(new Resource("not a resource")));
    }
    
    @Test
    public void testObjectivesAsPrimaryResources(){
        Objective obj = new Objective(objectives);
        Map<PrimaryResource, Integer> objectives = obj.getObjectiveAsPrimaryResources();
        assertTrue(objectives.containsKey(PrimaryResource.QUARTZ));
        assertTrue(objectives.containsKey(PrimaryResource.WOOD));
        assertTrue(objectives.containsKey(PrimaryResource.FRUITS));
        assertTrue(objectives.containsKey(PrimaryResource.FUR));
        
        assertTrue(objectives.get(PrimaryResource.QUARTZ) == 8100);
        assertTrue(objectives.get(PrimaryResource.WOOD) == 4000);
        assertTrue(objectives.get(PrimaryResource.FRUITS) == 4400);
        
        Map<Resource, Integer> inventory = new HashMap<Resource,Integer>();
        inventory.put(new Resource("FRUITS"), 9999);
        inventory.put(new Resource("GLASS"),600);
        obj.updateObjectives(inventory);
        objectives = obj.getObjectiveAsPrimaryResources();
        assertTrue(objectives.containsKey(PrimaryResource.QUARTZ));
        assertTrue(objectives.containsKey(PrimaryResource.WOOD));
        assertFalse(objectives.containsKey(PrimaryResource.FRUITS));
        assertTrue(objectives.containsKey(PrimaryResource.FUR));
        
        inventory.put(new Resource("GLASS"),9999);
        obj.updateObjectives(inventory);
        objectives = obj.getObjectiveAsPrimaryResources();
        
        assertFalse(objectives.containsKey(PrimaryResource.WOOD));
        assertTrue(objectives.get(PrimaryResource.QUARTZ)==100);
    }
    
    @Test
    public void testSecondaryResourcesObjectives(){
        Objective obj = new Objective(objectives);
        
        Map<SecondaryResource, Integer> secondaryObjectives = obj.getSecondaryResourcesObjectives();
        assertTrue(secondaryObjectives.size() == 1);
        assertTrue(secondaryObjectives.containsKey(SecondaryResource.GLASS));
        assertTrue(obj.getObjective(new Resource("QUARTZ"))>1000);
        assertEquals(Rarity.COMMON,obj.getRarityOf(new Resource("QUARTZ")));
    }
    
    @Test
    public void testWithPlank(){
        objectives.put("PLANK", 100);
        Objective obj = new Objective(objectives);
        assertTrue(obj.isObjective(new Resource("WOOD")));
        //4000 from the Glass + 400 for the planks
        assertTrue(4025 == obj.getObjective(new Resource("WOOD")));
    }
    
}
