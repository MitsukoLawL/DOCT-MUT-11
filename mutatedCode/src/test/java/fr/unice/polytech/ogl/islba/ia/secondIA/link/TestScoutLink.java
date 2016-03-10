package fr.unice.polytech.ogl.islba.ia.secondIA.link;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import fr.unice.polytech.ogl.islba.ia.secondIA.node.NodeScout;
import fr.unice.polytech.ogl.islba.ia.secondIA.node.NodeStop;
import fr.unice.polytech.ogl.islba.model.Case;
import fr.unice.polytech.ogl.islba.model.Direction;
import fr.unice.polytech.ogl.islba.model.EtatDeJeu;

public class TestScoutLink {

    @Test
    public void test() {
        Map<String,Integer> objective = new HashMap<String, Integer>();
        objective.put("Resource", 200);
        EtatDeJeu etat = new EtatDeJeu(100,"creek",100,objective);
        
        Link link = new ScoutLink(etat,Direction.NORTH);
        
        Case caseLanded = new Case();
        caseLanded.setMoveOn(true);
        etat.getMapMonde().addCaseMap(0,0,caseLanded);
        etat.getMapMonde().setCurrentCoo(0, 0);
        
        Case caseNorth = new Case();
        caseNorth.setScouted(true);
        etat.getMapMonde().addCaseMap(0,1,caseNorth);
        
        assertFalse(link.isNextNode());
        assertTrue(link.nextNode() instanceof NodeStop);
        
        caseNorth.setScouted(false);
        assertTrue(link.isNextNode());
        assertTrue(link.nextNode() instanceof NodeScout);
    }

}
