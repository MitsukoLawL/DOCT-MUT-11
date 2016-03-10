package fr.unice.polytech.ogl.islba.ia.secondIA.condition;

import java.util.HashMap;
import java.util.Map;

import fr.unice.polytech.ogl.islba.ia.secondIA.node.Node;
import fr.unice.polytech.ogl.islba.ia.secondIA.node.NodeTransform;
import fr.unice.polytech.ogl.islba.model.EtatDeJeu;
import fr.unice.polytech.ogl.islba.model.resource.SecondaryResource;

/**
 * The condition to transform resources in another one
 * is to have the resources needed and that the
 * resource we can transform with them is useful
 *
 */
public class ConditionTransform implements Condition {

    private EtatDeJeu etat;
    private Map<SecondaryResource, Integer> whatWeCanCreate;
    
    /**
     * To know if we can transform, we need the
     * current state of the game
     * @param etat
     *          the current state of the game
     */
    public ConditionTransform(EtatDeJeu etat){
        this.etat = etat;
        this.whatWeCanCreate = null;
    }
    
    @Override
    public boolean isOK() {
        whatWeCanCreate = etat.getTeam().canCreate();
        if(etat.getTeam().canCreate().size()>0){
            return true;
        }
        return false;
    }

    @Override
    public Node nextNode() {
        Map<String,Integer> resourcesForTransform = new HashMap<String,Integer>();
        for(SecondaryResource res : whatWeCanCreate.keySet()){
            int amountOfThisResource;
            for(int i = 0; i<res.getComponent().length;i++){
                amountOfThisResource = (int)(res.getAmount()[i] * whatWeCanCreate.get(res));
                if(amountOfThisResource<=0) amountOfThisResource = 1;
                resourcesForTransform.put(res.getComponent()[i].toString(),amountOfThisResource);
            }
            break;
        }
        if(whatWeCanCreate==null || whatWeCanCreate.size() <= 0) return null;
        
        return new NodeTransform(this.etat, resourcesForTransform);
    }
}
