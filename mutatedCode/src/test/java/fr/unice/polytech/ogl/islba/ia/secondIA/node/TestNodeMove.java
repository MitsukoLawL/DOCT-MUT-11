package fr.unice.polytech.ogl.islba.ia.secondIA.node;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import fr.unice.polytech.ogl.islba.model.Case;
import fr.unice.polytech.ogl.islba.model.Direction;
import fr.unice.polytech.ogl.islba.model.EtatDeJeu;
import fr.unice.polytech.ogl.islba.model.resource.Resource;

public class TestNodeMove {

    @Test
    public void test() {
        Map<String,Integer> objective = new HashMap<String, Integer>();
        objective.put("Resource", 200);
        EtatDeJeu etat = new EtatDeJeu(100,"creek",100,objective);
        Node move = new NodeMove(etat, Direction.EAST);
        
        Case caseLanded = new Case();
        caseLanded.setMoveOn(true);
        etat.getMapMonde().addCaseMap(0,0,caseLanded);
        etat.getMapMonde().setCurrentCoo(0, 0);
        
        Case caseNorth = new Case();
        caseNorth.setScouted(true);
        etat.getMapMonde().addCaseMap(0,1,caseNorth);
        caseNorth.addRessource(new Resource("Resource"));
        assertTrue(move.nextNode() instanceof NodeMove);
        
        caseNorth.setScouted(false);
        assertTrue(move.nextNode() instanceof NodeScout);
    }

}
