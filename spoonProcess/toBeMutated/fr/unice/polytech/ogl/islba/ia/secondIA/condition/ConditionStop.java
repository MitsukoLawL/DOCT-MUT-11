package fr.unice.polytech.ogl.islba.ia.secondIA.condition;

import fr.unice.polytech.ogl.islba.ia.secondIA.node.Node;
import fr.unice.polytech.ogl.islba.ia.secondIA.node.NodeStop;
import fr.unice.polytech.ogl.islba.model.EtatDeJeu;

/**
 * The condition to stop the bot
 *
 */
public class ConditionStop implements Condition{
    
    private EtatDeJeu etat;
    
    public ConditionStop(EtatDeJeu etat){
        this.etat=etat;
    }
    
    /**
     * The condition to reach the Stop node is :
     *      we haven't enough pa to continue safely or we haven't any objectives left
     * @param etat
     *          the current EtatDeJeu
     * @return true if we have to stop the bot, false otherwise
     */
    @Override
    public boolean isOK() {
        //we stop if we haven't any PA to continue safely or if we have all the objectives finished
        return (etat.getPA() < paToStop() || etat.getTeam().noMoreObjectives());
    }

    /**
     * Permits to know how much pa we need to stop right now the bot
     * @param etat
     *          the current EtatDeJeu
     */
    private int paToStop(){
        int paToStop = 15;
        //it's the beginning if max pa is equals to the actual pa
        //so we need around 0 pa to stop
        if(etat.getPAMax() == etat.getPA()){
            return 0;
        }
        
        if(etat.getMapMonde().getCurrentCoo() != null){
            paToStop += Math.abs(etat.getMapMonde().getCurrentCoo().getX()) + Math.abs(etat.getMapMonde().getCurrentCoo().getY())*3;
        }
        //little algorithm to have a little idea of how much pa we need to stop
        return paToStop;
    }
    
    /**
     * The next node is a Stop node
     * @return a new NodeStop
     */
    @Override
    public Node nextNode() {
        return new NodeStop(etat);
    }
}
