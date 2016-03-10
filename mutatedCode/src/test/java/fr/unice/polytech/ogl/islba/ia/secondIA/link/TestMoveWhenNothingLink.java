package fr.unice.polytech.ogl.islba.ia.secondIA.link;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import fr.unice.polytech.ogl.islba.ia.secondIA.node.NodeMove;
import fr.unice.polytech.ogl.islba.model.Case;
import fr.unice.polytech.ogl.islba.model.EtatDeJeu;

public class TestMoveWhenNothingLink {

    @Test
    public void test() {
        Map<String,Integer> objective = new HashMap<String, Integer>();
        objective.put("Resource", 200);
        EtatDeJeu etat = new EtatDeJeu(100,"creek",100,objective);

        Case caseLanded = new Case();
        caseLanded.setMoveOn(true);
        etat.getMapMonde().addCaseMap(0,0,caseLanded);
        etat.getMapMonde().setCurrentCoo(0, 0);
        
        Link link = new MoveWhenNothingLink(etat);
        assertTrue(link.isNextNode());
        assertTrue(link.nextNode() instanceof NodeMove);
    }

}
