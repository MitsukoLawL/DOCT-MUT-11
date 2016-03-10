package fr.unice.polytech.ogl.islba.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class RarityTest {

    @Test
    public void test() {
        assertEquals(Rarity.UNCOMMON,Rarity.getRarityFromAmount(500));
        assertEquals(Rarity.COMMON,Rarity.getRarityFromAmount(2000));
        assertEquals(Rarity.RARE,Rarity.getRarityFromAmount(2));
    }

}
