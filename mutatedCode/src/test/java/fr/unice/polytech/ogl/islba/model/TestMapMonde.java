package fr.unice.polytech.ogl.islba.model;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class TestMapMonde {
    @Test
    public void TestMM() {
        Map<String, Integer> resources = new HashMap<String, Integer>();
        EtatDeJeu jeu = new EtatDeJeu(1000, "osef", 500, resources);
    }
}
