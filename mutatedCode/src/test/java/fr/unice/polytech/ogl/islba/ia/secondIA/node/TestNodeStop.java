package fr.unice.polytech.ogl.islba.ia.secondIA.node;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import fr.unice.polytech.ogl.islba.command.Stop;
import fr.unice.polytech.ogl.islba.model.EtatDeJeu;

public class TestNodeStop {

    @Test
    public void test() {
        Map<String,Integer> objective = new HashMap<String, Integer>();
        objective.put("Resource", 200);
        EtatDeJeu etat = new EtatDeJeu(100,"creek",100,objective);
        
        Node stop = new NodeStop(etat);
        assertTrue(stop.nodeCommand() instanceof Stop);
        assertTrue(stop.nextNode() instanceof NodeStop);
        
        etat.reducePA(100);
        assertTrue(stop.nodeCommand() instanceof Stop);
        assertTrue(stop.nextNode() instanceof NodeStop);
    }

}
