package fr.unice.polytech.ogl.islba.ia;

import fr.unice.polytech.ogl.islba.command.*;
import fr.unice.polytech.ogl.islba.model.*;
import fr.unice.polytech.ogl.islba.model.resource.Resource;
import fr.unice.polytech.ogl.islba.model.resource.SecondaryResource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FirstIA {

    private Direction dirMove;
    private boolean beginning;
    private Map<String,Integer> exploitResources;
    private Command c;
    private EtatDeJeu etat;
    private int moveWithoutGlimpse;
    private boolean exploreThisCase;
    private int numberWithoutExploit;
    private boolean exploitPossible;
    
    public FirstIA(){
        exploitResources= new HashMap<String,Integer>();
        beginning = true;
        dirMove = Direction.EAST;
        moveWithoutGlimpse = 20000;
        exploreThisCase = true;
        numberWithoutExploit=20;
        exploitPossible=true;
    }
    
    /**
     * @param etatJeu
     * @param equipe
     *
     * @return
     */
    public Command takeDecision(EtatDeJeu etatJeu, Equipe equipe) {
        this.etat=etatJeu;
        this.c=null;
        //if we have less than 25% of the PA at the beginning, we stop
        if(etat.getPA() < paForStop() || equipe.noMoreObjectives()){
            c = new Stop();
        }
        //if it's the beginning, we land
        if (beginning) {
            c = new Land(1, etat.getCreekID());
            beginning = false;
            return c;
        }
        if(c==null){
            c = transform();
        }
        if(c==null){
            c = explore();
        }
        if(c==null){
            c = exploit();
        }
        //if no command yet, we scout around us or move on a Case with needed resources
        if(c == null) {
            c=moveOrScout();
        }
        //if no Case with resources that we need, we glimpse around with a range of 4 to know where we have to go next
        if(c==null && moveWithoutGlimpse>3){
            c=glimpse();
        }
        //if all the glimpse has been done
        //we move in a Direction where we know there isn't any water
        if(c==null){
            c = moveAfterGlimpse();
        }
        //if no Direction where there isn't any water, we continue in the Direction which is on our right
        if(c == null){
            Case next = etat.getMapMonde().getCase(Coordonnees.add(dirMove.getCoo(), etat.getMapMonde().getCurrentCoo()));
            changeMoveWithoutGlimpse(dirMove.getNewDirection());
            dirMove= dirMove.getNewDirection();
            //but if the next Case is out of bound, we turn back
            //so it's two calls of the getNewDirection() method
            if((next!=null && !next.isOnMap())){
                dirMove= dirMove.getNewDirection();
                next = etat.getMapMonde().getCase(Coordonnees.add(dirMove.getCoo(), etat.getMapMonde().getCurrentCoo()));
            }
            c=new Move(dirMove);
            exploitResources = new HashMap<String,Integer>();
            exploitPossible=true;
            exploreThisCase=true;
        }
        
        return c;
    }
    
    /**
     * Return a command Tranform if we wan transform some of our resources
     * into something useful
     * @return a Transform command if we can create sth, null otherwise
     */
    private Command transform() {
        Map<SecondaryResource, Integer> whatWeCanCreate = etat.getTeam().canCreate();
        if(etat.getTeam().canCreate().size()>0){
            
            //TODO faire en Map
            Map<String,Integer> resourcesForTransform = new HashMap<String,Integer>();
            for(SecondaryResource res : whatWeCanCreate.keySet()){
                int amountOfThisResource;
                for(int i = 0; i<res.getComponent().length;i++){
                    amountOfThisResource = (int)(res.getAmount()[i] * whatWeCanCreate.get(res));
                    if(amountOfThisResource<=0) amountOfThisResource = 1;
                    resourcesForTransform.put(res.getComponent()[i].toString(),amountOfThisResource);
                }
                c = new Transform(resourcesForTransform);
                break;
            }
        }
        return c;
    }

    /**
     * Do all the glimpse we need
     * @return a Glimpse Command or null if it isn't necessary to do a glimpse here
     */
    private Command glimpse(){
        Case nextCase;
        //for each direction
        for (Direction dir : Direction.DIRECTIONS) {
            nextCase = etat.getMapMonde().getCase(Coordonnees.add(dir.getCoo(), etat.getMapMonde().getCurrentCoo()));
            //if the Case hasn't been glimpsed yet or has a bad accuracy
            if(nextCase==null || nextCase.getNumberCaseGlimpse()<0){
                c=new Glimpse(dir, 4);
                return c;
            }
        }
        moveWithoutGlimpse=0;
        return null;
    }
    
    /**
     * Scout a Case. If the Case has already been scouted,
     * we check if there is an interesting resource on it.
     * If yes, we move on it, otherwise we repeat this for all the others directions
     * @return a Scout Command if the Case has not been scouted, a Move Command if there is an interesting resource, null otherwise
     */
    private Command moveOrScout(){
        Case nextCase;
        //for each Direction
        for (Direction dir : Direction.DIRECTIONS) {
            nextCase = etat.getMapMonde().getCase(Coordonnees.add(dir.getCoo(), etat.getMapMonde().getCurrentCoo()));
            //if the Case hasn't already been scouted
            if(nextCase == null || !nextCase.getScouted()){
                c=new Scout(dir);
                return c;
            }
            //we check the resources on it to see if move on it permits to obtains resources
            for(Resource res : nextCase.getRessources()){
                if(etat.getTeam().isObjective(res.getName()) && !nextCase.getMoveOn()){
                    changeMoveWithoutGlimpse(dir);
                    dirMove = dir;
                    c=new Move(dirMove);
                    exploreThisCase=true;
                    exploitPossible=true;
                    exploitResources = new HashMap<String,Integer>();
                    return c;
                }
            }       
        }
        return null;
    }
    
    /**
     * Now that we have glimpse in the four directions
     * we need to know if there is something usefull
     * @return a move Command if there is a good Direction to go, null otherwise
     */
    private Command moveAfterGlimpse(){
        c = moveWithBiomes();
        if(c==null){
            c = moveAfterGlimpseWater();
        }
        return c;
    }
    
    /**
     * If after the glimpse we learn that there is an Ocean or a Lake
     * in a Direction, we move in another Direction
     * @return  a Move command in a direction where there isn't any water, null otherwise
     */
    private Command moveAfterGlimpseWater(){
        Coordonnees cooCase3 = Coordonnees.add(etat.getMapMonde().getCurrentCoo(), (Coordonnees.multiplyBy(dirMove.getCoo(), 3)));
        Case nextCase3 = etat.getMapMonde().getCase(cooCase3);
        if(nextCase3 == null || (nextCase3.isOnMap() && !nextCase3.hasBiome(Biome.OCEAN) && !nextCase3.hasBiome(Biome.LAKE))){
            changeMoveWithoutGlimpse(dirMove);
            exploreThisCase=true;
            exploitPossible=true;
            exploitResources = new HashMap<String,Integer>();
            return new Move(dirMove);
        }
        //we check the Case in each Direction to see
        for(Direction dir : Direction.DIRECTIONS){
            //the Case at 3 Case from there has as Coordonnees :  
            //the Coordonnees of here + 3 times the Coordonnees of the Direction
            cooCase3 = Coordonnees.add(etat.getMapMonde().getCurrentCoo(), Coordonnees.multiplyBy(dir.getCoo(), 3));
            nextCase3 = etat.getMapMonde().getCase(cooCase3);
            
            //if it's not a lake or an Ocean in majority, we go in this direction
            if(nextCase3 != null && nextCase3.isOnMap() && !nextCase3.hasBiome(Biome.OCEAN) && !nextCase3.hasBiome(Biome.LAKE) && !nextCase3.getMoveOn()){
                changeMoveWithoutGlimpse(dir);
                dirMove = dir;
                c=new Move(dirMove);
                exploreThisCase=true;
                exploitPossible=true;
                exploitResources = new HashMap<String,Integer>();
                return c;
            }
        }
        return null;
    }
    
    /**
     * Move in a direction where there is a biome with
     * resources we need
     * @return a Move command if there is a good biome for us, null otherwise
     */
    private Command moveWithBiomes(){
        //the max dist where we will check
        int maxDist = 4;
        Case nextCase;
        Coordonnees nextCoo;
        Coordonnees currentCoo = etat.getMapMonde().getCurrentCoo();
        for(int i=1;i<maxDist;i++){
            //we check in each direction the case which is at i range of current case
            for(Direction dir : Direction.DIRECTIONS){
                nextCoo = Coordonnees.multiplyBy(dir.getCoo(), i);
                nextCoo = Coordonnees.add(nextCoo, currentCoo);
                nextCase = etat.getMapMonde().getCase(nextCoo);
                //for each biome we know
                for(Biome biome : Biome.values()){
                    //if the next case has this biome
                    if(nextCase!=null && nextCase.hasBiome(biome) && !nextCase.getMoveOn()){
                        //we check each resource of the biome
                        for(String resource : biome.getResources()){
                            //and see if it's an objective
                            if(etat.getTeam().isObjective(resource)){
                                changeMoveWithoutGlimpse(dir);
                                dirMove = dir;
                                c=new Move(dirMove);
                                exploitPossible=true;
                                exploreThisCase=true;
                                exploitResources = new HashMap<String,Integer>();
                                return c;
                            }
                        }
                    }
                }
            }
        }
        return null;
    }
    
    /**
     * When we move, we call this function
     * It permits to put moveWithoutGlimpse at 0 if we move in a new Dir
     * and to incremennt moveWithoutGlimpse otherwise
     * @param newDir
     *          the direction where we want to move
     */
    private void changeMoveWithoutGlimpse(Direction newDir){
        moveWithoutGlimpse++;
        if(!newDir.equals(dirMove)){
            moveWithoutGlimpse=0;
        }
    }
    
    /**
     * Calculate how many PA we need in order to come back
     * at the boat and quit the island
     * @return the number of PA we need to stop
     */
    private int paForStop(){
        if(beginning){
            return 0;
        }
        return (Math.abs(etat.getMapMonde().getCurrentCoo().getX()) + Math.abs(etat.getMapMonde().getCurrentCoo().getY()))*3;

    }
    
    /**
     * Check if the Case put in parameter contains a needed resource
     * @param caseCheck
     *          the Case we want to check if it has a resource we need
     * @return true if there is a resource we need, false otherwise         
     */
    private boolean checkResourceOnCase(Case caseCheck){
        for(Resource res : caseCheck.getRessources()){
            if(etat.getTeam().isObjective(res.getName())){
                return true;
            }
        }
        return false;
    }
    
    /**
     * Do the exploitation if we have a resource we need
     * on the current Case
     * @return a correct Exploit command if there is a needed resource, null otherwise
     */
    private Command exploit(){
        Equipe team = etat.getTeam();
        Rarity rarity;
        String resourceName="";
        Case currentCase = etat.getMapMonde().getCurrentCase();
        int numberOfExploit=0;
        boolean exploit = false;
        //if we know that the current case have resources that we need, we exploit them
        for(Resource res : currentCase.getRessources()){
            exploit=false;
            if(team.isObjective(res.getName())){
                //TODO remove
                resourceName=res.getName();
                rarity = team.getRarityOf(res.getName());
                numberOfExploit = exploitResources.get(res.getName())==null? 0:exploitResources.get(res.getName());              
                
                if(checkAmountAndCondOnCase(currentCase, "HIGH", "EASY", res.getName())
                        || checkAmountAndCondOnCase(currentCase, "MEDIUM", "EASY",res.getName())
                        || checkAmountAndCondOnCase(currentCase, "HIGH", "FAIR",res.getName())){
                    exploit=true;
                }else if(!rarity.equals(Rarity.COMMON) 
                        && (checkAmountAndCondOnCase(currentCase, "MEDIUM", "FAIR", res.getName())
                        || checkAmountAndCondOnCase(currentCase, "HIGH", "FAIR", res.getName())
                        || checkAmountAndCondOnCase(currentCase, "MEDIUM", "EASY",res.getName())
                        || checkAmountAndCondOnCase(currentCase, "HIGH", "EASY",res.getName()))){
                    exploit = true;
                }else if(!rarity.equals(Rarity.COMMON)&& !rarity.equals(Rarity.UNCOMMON) && numberOfExploit<1){
                    exploit = true;
                }
                if(!exploit && numberOfExploit<1){
                    numberWithoutExploit--;
                    if(numberWithoutExploit<0 && exploitPossible){
                        exploit=true;
                    }
                }
                if(numberOfExploit<1 && exploit){
                    c=new Exploit(resourceName);
                    numberOfExploit=1;
                    exploitResources.put(resourceName, numberOfExploit+1);
                    break;
                }
            }
        }
        
        return c;
    }
    
    /**
     * Return a correct Explore command if there is a resource
     * we need on the current Case and if we want to explore this Case
     * @return a correct Explore Command if we want to explore it, null otherwise
     */
    private Command explore() {
        if(checkResourceOnCase(etat.getMapMonde().getCurrentCase()) && exploreThisCase){
            exploreThisCase=false;
            c = new Explore();
        }
        return c;
    }
    
    /**
     * Check if the Resource pass in parameter is
     * in a desire amount and condition on the Case
     * we want
     * @param caseChecked
     *          the Case we want to check
     * @param amount
     *          the amount we want for the resource
     * @param cond
     *          the condition we want for the resource
     * @param res
     *          the resource we want to check
     *          
     * @return true if the resource is in an amount of 'amount' and in a condition of 'cond' on the caseChecked, false otherwise
     */
    private boolean checkAmountAndCondOnCase(Case caseChecked,String amount, String cond, String res){
        List<String> resources = caseChecked.getResourcesWithAmountAndCond(amount,cond);
        if(resources.contains(res)){
            return true;
        }
        return false;
    }

}

