package fr.unice.polytech.ogl.islba.ia.secondIA.condition;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import fr.unice.polytech.ogl.islba.model.Biome;
import fr.unice.polytech.ogl.islba.model.Case;
import fr.unice.polytech.ogl.islba.model.Direction;
import fr.unice.polytech.ogl.islba.model.EtatDeJeu;

public class TestConditionMoveWithhBiomes {

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
        Case caseGlimpsedFake = new Case();
        caseGlimpsedFake.setNumberCaseGlimpse(1);
        
        //all have a good biomes
        for(int i=1;i<4;i++){
            etat.getMapMonde().addCaseMap(0,i,caseGlimpsed);
            etat.getMapMonde().addCaseMap(0,-i,caseGlimpsed);
            etat.getMapMonde().addCaseMap(i,0,caseGlimpsed);
            etat.getMapMonde().addCaseMap(-i,0,caseGlimpsed);
        }
        //except in (0,2)
        etat.getMapMonde().addCaseMap(0, 2, caseGlimpsedFake);
        
        //tile without biome we need
        Condition move = new ConditionMoveWithBiomes(etat, Direction.NORTH,2);
        assertFalse(move.isOK());
        
        //tile with a biome we need
        move = new ConditionMoveWithBiomes(etat, Direction.NORTH,1);
        assertTrue(move.isOK());
        
        //tile where we have already moved
        caseGlimpsed.setMoveOn(true);
        assertFalse(move.isOK());
        
        //null tile
        move = new ConditionMoveWithBiomes(etat,Direction.NORTH,200);
        assertFalse(move.isOK());
    }

}
