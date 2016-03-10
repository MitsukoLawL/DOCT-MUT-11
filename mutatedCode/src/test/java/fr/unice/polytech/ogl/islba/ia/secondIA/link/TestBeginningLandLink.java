package fr.unice.polytech.ogl.islba.ia.secondIA.link;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import fr.unice.polytech.ogl.islba.ia.secondIA.node.NodeLand;
import fr.unice.polytech.ogl.islba.model.EtatDeJeu;

public class TestBeginningLandLink {

    @Test
    public void test() {
        Map<String,Integer> objective = new HashMap<String, Integer>();
        objective.put("Resource", 200);
        EtatDeJeu etat = new EtatDeJeu(100,"creek",100,objective);
        
        Link link = new BeginningLandLink(etat);
        
        assertTrue(link.isNextNode());
        assertTrue(link.nextNode() instanceof NodeLand);
    }

}
