package fr.unice.polytech.ogl.islba.ia.secondIA.node;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import fr.unice.polytech.ogl.islba.command.Stop;
import fr.unice.polytech.ogl.islba.model.EtatDeJeu;

public class TestNodeStart {

    @Test
    public void test() {
        Map<String,Integer> objective = new HashMap<String, Integer>();
        objective.put("Resource", 200);
        EtatDeJeu etat = new EtatDeJeu(100,"creek",100,objective);
        
        MyNode start = new NodeStart(etat);
        
        assertTrue(start.nextNode() instanceof NodeLand);
        assertTrue(start.nodeCommand() instanceof Stop);
        
        etat.reducePA(1000);
        assertTrue(start.nextNode() instanceof NodeStop);
    }

}
