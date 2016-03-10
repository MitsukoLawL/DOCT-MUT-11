package fr.unice.polytech.ogl.islba.ia.secondIA.link;

import fr.unice.polytech.ogl.islba.ia.secondIA.condition.ConditionTransform;
import fr.unice.polytech.ogl.islba.model.EtatDeJeu;

public class TransformLink extends Link{

    public TransformLink(EtatDeJeu etat) {
        super(etat);
        this.conditions.add(new ConditionTransform(this.getEtat()));
    }

}
