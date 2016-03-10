package fr.unice.polytech.ogl.islba.ia.secondIA.condition; 
public class ConditionTransform implements fr.unice.polytech.ogl.islba.ia.secondIA.condition.Condition {
    private fr.unice.polytech.ogl.islba.model.EtatDeJeu etat;

    private java.util.Map<fr.unice.polytech.ogl.islba.model.resource.SecondaryResource, java.lang.Integer> whatWeCanCreate;

    public ConditionTransform(fr.unice.polytech.ogl.islba.model.EtatDeJeu etat) {
        this.etat = etat;
        this.whatWeCanCreate = null;
    }

    @java.lang.Override
    public boolean isOK() {
        whatWeCanCreate = etat.getTeam().canCreate();
        if ((etat.getTeam().canCreate().size()) > 0) {
            return true;
        } 
        return false;
    }

    @java.lang.Override
    public fr.unice.polytech.ogl.islba.ia.secondIA.node.Node nextNode() {
        java.util.Map<java.lang.String, java.lang.Integer> resourcesForTransform = new java.util.HashMap<java.lang.String, java.lang.Integer>();
        for (fr.unice.polytech.ogl.islba.model.resource.SecondaryResource res : whatWeCanCreate.keySet()) {
            int amountOfThisResource;
            for (int i = 0 ; i < (res.getComponent().length) ; i++) {
                amountOfThisResource = ((int)((res.getAmount()[i]) * (whatWeCanCreate.get(res))));
                if (amountOfThisResource <= 0)
                    amountOfThisResource = 1;
                
                resourcesForTransform.put(res.getComponent()[i].toString(), amountOfThisResource);
            }
            break;
        }
        if (((whatWeCanCreate) == null) && ((whatWeCanCreate.size()) <= 0))
            return null;
        
        return new fr.unice.polytech.ogl.islba.ia.secondIA.node.NodeTransform(this.etat , resourcesForTransform);
    }
}