package fr.unice.polytech.ogl.islba.ia.secondIA.link;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import fr.unice.polytech.ogl.islba.model.Case;
import fr.unice.polytech.ogl.islba.model.EtatDeJeu;

public class TestGlimpseLink {

    @Test
    public void test() {
        Map<String,Integer> objective = new HashMap<String, Integer>();
        objective.put("Resource", 200);
        EtatDeJeu etat = new EtatDeJeu(100,"creek",100,objective);
        
        Case caseLanded = new Case();
        caseLanded.setMoveOn(true);
        etat.getMapMonde().addCaseMap(0,0,caseLanded);
        etat.getMapMonde().setCurrentCoo(0, 0);
        
        Link link = new GlimpseLink(etat);
        
        assertTrue(link.isNextNode());
        
        Case caseGlimpsed = new Case();
        caseGlimpsed.setNumberCaseGlimpse(3);
        
        etat.getMapMonde().addCaseMap(0,1,caseGlimpsed);
        assertTrue(link.isNextNode());
        
        etat.getMapMonde().addCaseMap(1,0,caseGlimpsed);
        assertTrue(link.isNextNode());
        
        etat.getMapMonde().addCaseMap(0,-1,caseGlimpsed);
        assertTrue(link.isNextNode());
        
        etat.getMapMonde().addCaseMap(-1,0,caseGlimpsed);
        assertFalse(link.isNextNode());
    }

}
