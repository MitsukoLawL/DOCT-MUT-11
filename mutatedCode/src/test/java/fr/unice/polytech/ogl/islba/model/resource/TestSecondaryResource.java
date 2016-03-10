package fr.unice.polytech.ogl.islba.model.resource;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestSecondaryResource {
/*
 * GLASS("GLASS",
            new PrimaryResource[]{PrimaryResource.QUARTZ, PrimaryResource.WOOD},
            new double[]{10,5}),
    INGOT("INGOT",
            new PrimaryResource[]{PrimaryResource.ORE, PrimaryResource.WOOD},
            new double[]{5,5}),
    PLANK("PLANK",
            new PrimaryResource[]{PrimaryResource.WOOD},
            new double[]{0.25}),
    LEATHER("LEATHER",
            new PrimaryResource[]{PrimaryResource.FUR},
            new double[]{3}),
    RUM("RUM",
            new PrimaryResource[]{PrimaryResource.SUGAR_CANE, PrimaryResource.FRUITS},
            new double[]{10,1});
    
 */
    private SecondaryResource GLASS = SecondaryResource.GLASS;
    private SecondaryResource INGOT = SecondaryResource.INGOT;
    private SecondaryResource PLANK = SecondaryResource.PLANK;
    private SecondaryResource LEATHER = SecondaryResource.LEATHER;
    private SecondaryResource RUM = SecondaryResource.RUM;
    
    @Test
    public void testGetComponentsNeeded() {

        assertTrue(PLANK.getComponentsNeeded().containsKey(PrimaryResource.WOOD));
        assertEquals(1,PLANK.getComponentsNeeded().size());
        
        assertTrue(GLASS.getComponentsNeeded().containsKey(PrimaryResource.QUARTZ));
        assertTrue(GLASS.getComponentsNeeded().containsKey(PrimaryResource.WOOD));
        assertEquals(2,GLASS.getComponentsNeeded().size());
        
        assertTrue(INGOT.getComponentsNeeded().containsKey(PrimaryResource.ORE));
        assertTrue(INGOT.getComponentsNeeded().containsKey(PrimaryResource.WOOD));
        assertEquals(2,INGOT.getComponentsNeeded().size());
        
        assertTrue(LEATHER.getComponentsNeeded().containsKey(PrimaryResource.FUR));
        assertEquals(1,LEATHER.getComponentsNeeded().size());
        
        assertTrue(RUM.getComponentsNeeded().containsKey(PrimaryResource.SUGAR_CANE));
        assertTrue(RUM.getComponentsNeeded().containsKey(PrimaryResource.FRUITS));
        assertEquals(2,RUM.getComponentsNeeded().size());
    }

    @Test
    public void testGetComponent() {
        assertEquals(new PrimaryResource[]{PrimaryResource.WOOD},PLANK.getComponent());
        assertEquals(new PrimaryResource[]{PrimaryResource.QUARTZ, PrimaryResource.WOOD},GLASS.getComponent());
        assertEquals(new PrimaryResource[]{PrimaryResource.ORE,PrimaryResource.WOOD},INGOT.getComponent());
        assertEquals(new PrimaryResource[]{PrimaryResource.FUR},LEATHER.getComponent());
        assertEquals(new PrimaryResource[]{PrimaryResource.SUGAR_CANE, PrimaryResource.FRUITS},RUM.getComponent());
    }

    @Test
    public void testGetAmount() {
        double[] resourcesForGlass = new double[]{10,5};
        for(int i=0;i<GLASS.getAmount().length;i++){
            assertEquals(resourcesForGlass[i], GLASS.getAmount()[i],0.001);
        }
        
        double[] resourcesForIngot= new double[]{5,5};
        for(int i=0;i<INGOT.getAmount().length;i++){
            assertEquals(resourcesForIngot[i], INGOT.getAmount()[i],0.001);
        }
        
        double[] resourcesForPlank = new double[]{0.25};
        for(int i=0;i<PLANK.getAmount().length;i++){
            assertEquals(resourcesForPlank[i], PLANK.getAmount()[i],0.001);
        }
        
        double[] resourcesForLeather = new double[]{3};
        for(int i=0;i<LEATHER.getAmount().length;i++){
            assertEquals(resourcesForLeather[i], LEATHER.getAmount()[i],0.001);
        }
        
        double[] resourcesForRum = new double[]{10,1};
        for(int i=0;i<RUM.getAmount().length;i++){
            assertEquals(resourcesForRum[i], RUM.getAmount()[i],0.001);
        }
    }

    @Test
    public void testToString() {
        assertEquals("RUM",RUM.toString());
        assertEquals("LEATHER",LEATHER.toString());
        assertEquals("PLANK",PLANK.toString());
        assertEquals("INGOT",INGOT.toString());
        assertEquals("GLASS",GLASS.toString());
    }

}
