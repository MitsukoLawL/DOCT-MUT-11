package fr.unice.polytech.ogl.islba.ia.secondIA.condition;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import fr.unice.polytech.ogl.islba.model.Biome;
import fr.unice.polytech.ogl.islba.model.Case;
import fr.unice.polytech.ogl.islba.model.Direction;
import fr.unice.polytech.ogl.islba.model.EtatDeJeu;
import fr.unice.polytech.ogl.islba.model.OutOfMapCase;

public class TestconditionMoveIfNoWater {

    @Test
    public void test() {
        Map<String,Integer> objective = new HashMap<String, Integer>();
        objective.put("WOOD", 200);
        EtatDeJeu etat = new EtatDeJeu(100,"creek",100,objective);
        
        //we land
        Case caseLanded = new Case();
        caseLanded.setMoveOn(true);
        etat.getMapMonde().addCaseMap(0,0,caseLanded);
        etat.getMapMonde().setCurrentCoo(0, 0);
        
        Case caseGlimpsed = new Case();
        caseGlimpsed.addBiome(Biome.TROPICAL_SEASONAL_FOREST.toString(), 0.2);
        caseGlimpsed.setNumberCaseGlimpse(1);
        etat.getMapMonde().addCaseMap(0, 3, caseGlimpsed);
        
        Condition move = new ConditionMoveIfNoWater(etat, Direction.NORTH);
        assertTrue(move.isOK());
        
        caseGlimpsed.addBiome(Biome.OCEAN.toString(),0.1);
        assertFalse(move.isOK());
        
        move = new ConditionMoveIfNoWater(etat, Direction.SOUTH);
        assertFalse(move.isOK());
        
        caseGlimpsed = new OutOfMapCase();
        etat.getMapMonde().addCaseMap(0, 3, caseGlimpsed);
        assertFalse(move.isOK());
    }
}
