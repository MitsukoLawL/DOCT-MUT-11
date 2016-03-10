/**
 * 
 */
package fr.unice.polytech.ogl.islba.model;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Test the methods of the Direction enum
 */
public class TestDirection {

    @Test
    public void testDirectionString() {
        assertEquals(Direction.NORTH.getDirection(),"N");
        assertEquals(Direction.EAST.getDirection(),"E");
        assertEquals(Direction.SOUTH.getDirection(),"S");
        assertEquals(Direction.WEST.getDirection(),"W");
    }
    
    @Test
    public void testCoordonneesDirection(){
        assertEquals(Direction.NORTH.getCoo(),new Coordonnees(0,1));
        assertEquals(Direction.EAST.getCoo(),new Coordonnees(1,0));
        assertEquals(Direction.SOUTH.getCoo(),new Coordonnees(0,-1));
        assertEquals(Direction.WEST.getCoo(),new Coordonnees(-1,0));
    }
    
    @Test
    public void testGetNewDirection(){
        assertEquals(Direction.NORTH.getNewDirection(),Direction.EAST);
        assertEquals(Direction.EAST.getNewDirection(),Direction.SOUTH);
        assertEquals(Direction.SOUTH.getNewDirection(),Direction.WEST);
        assertEquals(Direction.WEST.getNewDirection(),Direction.NORTH);
    }

}
