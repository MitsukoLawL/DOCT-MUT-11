package fr.unice.polytech.ogl.islba.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Test of class Coordonnees
 */
public class TestCoordonnees {

    /**
     * Test of the constructor of the coordonnees
     */
    @Test
    public void testCreateCoordonnees() {
        Coordonnees c1 = new Coordonnees(0, 0);
        assertEquals(c1, new Coordonnees(c1.getX(), c1.getY()));
    }

    /**
     * Test of the static méthode MultiplyBy of Coordonnees
     */
    @Test
    public void testMultiplyBy() {
        Coordonnees c1 = new Coordonnees(0, 0);
        assertEquals(Coordonnees.multiplyBy(c1, 40), new Coordonnees(0, 0));

        c1 = new Coordonnees(1, 3);
        assertEquals(Coordonnees.multiplyBy(c1, 3), new Coordonnees(3, 9));
    }

    /**
     * Test of the static méthode Add of Coordonnees
     */
    @Test
    public void testAdd(){
        Coordonnees c1 = new Coordonnees(0, 0);
        Coordonnees c2 = new Coordonnees(3, 5);
        assertEquals(Coordonnees.add(c1, c2), (new Coordonnees(3, 5)));
        c1 = new Coordonnees(7, 15);
        c2 = new Coordonnees(3, 5);
        assertEquals(Coordonnees.add(c1, c2), (new Coordonnees(10, 20)));

    }
}
