package fr.unice.polytech.ogl.islba.model;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import fr.unice.polytech.ogl.islba.model.resource.Resource;
import fr.unice.polytech.ogl.islba.model.resource.SecondaryResource;

public class TestEquipe {

    @Test
    public void testEquipe() {
        Map<String, Integer> objectives = new HashMap<String,Integer>();
        Map<String, Integer> objectives2 = new HashMap<String,Integer>();
        objectives.put("WOOD", 2000);
        objectives2.put("WOOD", 2000);
        objectives.put("IRON", 200);
        Equipe team=new Equipe(20, objectives);
        Equipe team2=new Equipe(20,objectives);
        Equipe teamLessMen = new Equipe(12,objectives);
        Equipe teamLessObjectives = new Equipe(12, objectives2);
        assertEquals(team,team);
        assertEquals(team,team2);
        assertNotEquals(team,teamLessMen);
        assertNotEquals(team,teamLessObjectives);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testExceptionEquipe(){
        Map<String, Integer> objectives = new HashMap<String,Integer>();
        new Equipe(-12,objectives);
    }
    
    
    @Test
    public void testLandMen() {
        Map<String, Integer> objectives = new HashMap<String,Integer>();
        Equipe team = new Equipe(100,objectives);
        int menLanded = 40;
        team.landMen(menLanded);
        assertEquals(team.getMen(),menLanded);
        menLanded=200;
        team.landMen(menLanded);
        assertEquals(team.getMen(),team.getMAXMEN());
        menLanded=0;
        team.landMen(menLanded);
        assertEquals(team.getMen(),1);
    }

    @Test
    public void testLoseMen() {
        Map<String, Integer> objectives = new HashMap<String,Integer>();
        Equipe team = new Equipe(100,objectives);
        team.landMen(50);
        team.loseMen(20);
        assertEquals(team.getMen(),30);
        assertEquals(team.getMAXMEN(),80);
        
        team.loseMen(30);
        assertEquals(team.getMen(),0);
        assertEquals(team.getMAXMEN(),50);
    }

    @Test
    public void testIsObjective() {
        Map<String, Integer> objectives = new HashMap<String,Integer>();
        objectives.put("WOOD", 2000);
        objectives.put("IRON",100);
        objectives.put("MACHIN",-100);
        objectives.put("TRUC", 0);
        objectives.put("GLASS",100);
        Equipe team = new Equipe(100,objectives);

        assertTrue(team.isObjective("WOOD"));
        assertFalse(team.isObjective("MACHIN"));
        assertFalse(team.isObjective("TRUC"));
        assertFalse(team.isObjective("RESSOURCE QUI N EXISTE PAS"));
        assertTrue(team.isObjective("QUARTZ"));
    }

    @Test
    public void testExploitResource() {
        Map<String, Integer> objectives = new HashMap<String,Integer>();
        objectives.put("WOOD", 2000);
        objectives.put("IRON",100);
        Equipe team = new Equipe(100,objectives);
        team.exploitResource("WOOD", 20);
        
        assertTrue(team.getObjectives().get(new Resource("WOOD")).equals(2000));
        assertTrue(team.getAmountInInventory("WOOD") == 20);
        
        team.exploitResource("MACHIN",3);
        assertNull(team.getObjectives().get("MACHIN"));
        assertTrue(team.getAmountInInventory("MACHIN") == 3);
        
        team.exploitResource("IRON", 100);
        assertNull(team.getObjectives().get("IRON"));
        assertTrue(team.getAmountInInventory("IRON") == 100);
        
        assertNull(team.getRarityOf("IRON"));
    }
    
    @Test
    public void testNoMoreObjectives(){
        Map<String, Integer> objectives = new HashMap<String,Integer>();
        objectives.put("FLOWER",239);
        objectives.put("WOOD", 2000);
        Equipe team = new Equipe(100,objectives);
        assertFalse(team.noMoreObjectives());
        
        team.exploitResource("FLOWER",239);
        assertFalse(team.noMoreObjectives());
        
        team.exploitResource("WOOD", 2000);
        assertTrue(team.noMoreObjectives());
    }
    
    @Test
    public void testObjectivesByRarity(){
        Map<String, Integer> objectives = new HashMap<String,Integer>();
        objectives.put("WOOD", 2000);
        objectives.put("FLOWER",700);
        objectives.put("IRON", 200);
        objectives.put("METAL", -800);
        Equipe team = new Equipe(100,objectives);
        assertEquals(Rarity.COMMON,team.getRarityOf("WOOD"));
        
        assertEquals(Rarity.UNCOMMON,team.getRarityOf("FLOWER"));
        assertEquals(Rarity.RARE,team.getRarityOf("IRON"));
        assertNull(team.getRarityOf("METAL"));
        
        assertNull(team.getRarityOf("resource that is not an objective"));
    }
    
    @Test
    public void testInventory(){
        Map<String, Integer> objectives = new HashMap<String,Integer>();
        Equipe team = new Equipe(100,objectives);
        
        assertFalse(team.isInInventory("truc"));
        assertTrue(team.getAmountInInventory("truc") == 0);
        team.exploitResource("truc", 42);
        assertTrue(team.isInInventory("truc"));
        assertTrue(team.getAmountInInventory("truc") == 42);
        
        team.removeResource("truc", 40);
        assertTrue(team.getAmountInInventory("truc") == 2);
        assertTrue(team.isInInventory("truc"));
        
        team.removeResource("truc", 3);
        assertTrue(team.getAmountInInventory("truc") == 0);
        assertFalse(team.isInInventory("truc"));
    }
    
    @Test
    public void testCanCreate(){
        Map<String, Integer> objectives = new HashMap<String,Integer>();
        objectives.put("GLASS", 2000);
        objectives.put("RUM",100);
        Equipe team = new Equipe(100,objectives);
        team.exploitResource("QUARTZ", 200);
        assertTrue(team.canCreate().size()==0);
        
        team.exploitResource("WOOD", 100);
        assertTrue(team.canCreate().size()==1);
        assertTrue(team.canCreate().get(SecondaryResource.GLASS)==20);
        
        team.exploitResource("QUARTZ", 99999);
        team.exploitResource("WOOD", 99999);
        
        assertTrue(team.canCreate().get(SecondaryResource.GLASS)==2000);
        
        team.exploitResource("GLASS",1000);
        assertTrue(team.canCreate().size()==1);
        assertTrue(team.canCreate().get(SecondaryResource.GLASS)==1000);
    }

    @Test
    public void testWithPlanks(){
        Map<String, Integer> objectives = new HashMap<String,Integer>();
        objectives.put("PLANK",100);
        Equipe team = new Equipe(100,objectives);
        assertTrue(team.isObjective("WOOD"));
        assertFalse(team.isInInventory("WOOD"));
        assertFalse(team.noMoreObjectives());
        assertTrue(team.whatWeNeed().containsKey(SecondaryResource.PLANK));
        assertTrue(team.whatWeNeed().get(SecondaryResource.PLANK) == 100);
        assertEquals(Rarity.RARE,team.getRarityOf("WOOD"));
    }
    
    @Test
    public void testWithAFewPlanks(){
        Map<String, Integer> objectives = new HashMap<String,Integer>();
        objectives.put("PLANK",2);
        Equipe team = new Equipe(100,objectives);
        assertTrue(team.isObjective("WOOD"));
        assertFalse(team.isInInventory("WOOD"));
        assertFalse(team.noMoreObjectives());
        assertTrue(team.whatWeNeed().containsKey(SecondaryResource.PLANK));
        assertTrue(team.whatWeNeed().get(SecondaryResource.PLANK) == 2);
        
        team.exploitResource("WOOD", 2000);
        Map<SecondaryResource, Integer> canCreate = team.canCreate();
        assertTrue(2==canCreate.get(SecondaryResource.PLANK));
    }
}
