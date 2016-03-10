package fr.unice.polytech.ogl.islba.model.resource;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestPrimaryResource {

    @Test
    public void testToString() {
        assertEquals("FISH",PrimaryResource.FISH.toString());
        assertEquals("FLOWER",PrimaryResource.FLOWER.toString());
        assertEquals("FRUITS",PrimaryResource.FRUITS.toString());
        assertEquals("FUR",PrimaryResource.FUR.toString());
        assertEquals("ORE",PrimaryResource.ORE.toString());
        assertEquals("QUARTZ",PrimaryResource.QUARTZ.toString());
        assertEquals("SUGAR_CANE",PrimaryResource.SUGAR_CANE.toString());
        assertEquals("WOOD",PrimaryResource.WOOD.toString());
    }

}
