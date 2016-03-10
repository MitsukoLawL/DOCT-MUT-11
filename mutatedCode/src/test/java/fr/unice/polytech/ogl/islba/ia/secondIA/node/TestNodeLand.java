package fr.unice.polytech.ogl.islba.ia.secondIA.node;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import fr.unice.polytech.ogl.islba.command.Land;
import fr.unice.polytech.ogl.islba.model.Case;
import fr.unice.polytech.ogl.islba.model.EtatDeJeu;

public class TestNodeLand {

    @Test
    
    
    public void test() {
        Map<String,Integer> objective = new HashMap<String, Integer>();
        objective.put("Resource", 200);
        EtatDeJeu etat = new EtatDeJeu(100,"creek",100,objective);
        
        Node land = new NodeLand(etat, 1, "creek");
        
        Case caseLanded = new Case();
        caseLanded.setMoveOn(true);
        etat.getMapMonde().addCaseMap(0,0,caseLanded);
        etat.getMapMonde().setCurrentCoo(0, 0);
        
        assertTrue(land.nextNode() instanceof NodeScout);
        assertTrue(land.nodeCommand() instanceof Land);
    }

}
