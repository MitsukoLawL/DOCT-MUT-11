package fr.unice.polytech.ogl.islba.ia.secondIA.link;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import fr.unice.polytech.ogl.islba.ia.secondIA.node.NodeStop;
import fr.unice.polytech.ogl.islba.model.EtatDeJeu;

public class TestStopLink {

    @Test
    public void test() {
        Map<String,Integer> objective = new HashMap<String, Integer>();
        objective.put("Resource", 200);
        EtatDeJeu etat = new EtatDeJeu(100,"creek",100,objective);
        
        Link link = new StopLink(etat);
        
        assertFalse(link.isNextNode());
        
        etat.reducePA(100);
        assertTrue(link.isNextNode());
        assertTrue(link.nextNode() instanceof NodeStop);
        
        etat.reducePA(-100);
        assertFalse(link.isNextNode());
        
        etat.getTeam().exploitResource("Resource", 100);
        assertFalse(link.isNextNode());
        
        etat.getTeam().exploitResource("Resource",100);
        assertTrue(link.isNextNode());
        assertTrue(link.nextNode() instanceof NodeStop);
    }

}
