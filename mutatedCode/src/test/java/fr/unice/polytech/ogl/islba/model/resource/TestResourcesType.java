package fr.unice.polytech.ogl.islba.model.resource;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestResourcesType {
    ResourcesType type;
    
    @Before
    public void testResourcesType() {
        type = new ResourcesType();
    }

    @Test
    public void testGetPrimaryResource() {
        assertEquals(PrimaryResource.FRUITS,type.getPrimaryResource("FRUITS"));
        assertEquals(PrimaryResource.FRUITS,type.getPrimaryResource("fruits"));
        assertEquals(PrimaryResource.FRUITS,type.getPrimaryResource("FruItS"));
        assertFalse(PrimaryResource.FRUITS.equals(type.getPrimaryResource("FISH")));
        
        assertNull(type.getPrimaryResource("GLASS"));
        assertNull(type.getPrimaryResource("YoLO"));
    }

    @Test
    public void testGetSecondaryResource() {
        assertEquals(SecondaryResource.GLASS,type.getSecondaryResource("GLASS"));
        assertEquals(SecondaryResource.GLASS,type.getSecondaryResource("glass"));
        assertEquals(SecondaryResource.GLASS,type.getSecondaryResource("GlAsS"));
        assertFalse(SecondaryResource.GLASS.equals(type.getSecondaryResource("RUM")));
        
        assertNull(type.getSecondaryResource("FRUITS"));
        assertNull(type.getSecondaryResource("YOLO"));
    }

    @Test
    public void testIsPrimaryResource() {
        assertTrue(type.isPrimaryResource("Fruits"));
        assertTrue(type.isPrimaryResource("FrUIts"));
        assertFalse(type.isPrimaryResource("GLASS"));
    }

    @Test
    public void testIsSecondaryResource() {
        assertTrue(type.isSecondaryResource("Glass"));
        assertTrue(type.isSecondaryResource("GlAss"));
        assertFalse(type.isSecondaryResource("FRUITS"));
    }

}
