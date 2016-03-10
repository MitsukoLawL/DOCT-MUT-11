package fr.unice.polytech.ogl.islba.model;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import fr.unice.polytech.ogl.islba.model.resource.PrimaryResource;
import fr.unice.polytech.ogl.islba.model.resource.Resource;
import fr.unice.polytech.ogl.islba.model.resource.SecondaryResource;


public class Equipe {
    private int MAXMEN;
    private Objective objective;
    private int men;
    private Map<Resource, Integer> inventory;
    
    /**
     * @param maxMen
     *      max men we can have
     * @param objectives
     *      the objectives of the exploration
     * @throws IllegalArgumentException 
     *          -if maxMen isn't positive and different of 0.   
     *          -if objectives is null  
     */
    public Equipe(int maxMen, Map<String, Integer> objectives){
        if(maxMen<=0 || objectives==null){
            throw new IllegalArgumentException("maxMen should be positive in Equipe and the objectives Map shouldn't be null.");
        }
        
        this.MAXMEN=maxMen;
        this.objective=new Objective(objectives);
        
        this.men=0;
        inventory = new HashMap<Resource, Integer>();
    }
    
    /**
     * When we land on the island, we have a certain number of men on it
     * @param nbMen
     *          the men who lands
     */
    public void landMen(int nbMen){
        this.men=nbMen;
        
        if(this.men>MAXMEN){
            this.men=MAXMEN;
        }else if(men<=0){
            this.men=1;
        }
    }
    
    /**
     * Mens can die, so we have a method in case we lose some of them
     * @param nbMen
     *          number of men who dies
     */
    public void loseMen(int nbMen){
        this.men-=nbMen;
        this.MAXMEN-=nbMen;
    }
 
    /**
     * Check if a resource is one of the objectives
     * @param resource
     *              the resource we want to check
     * @return true if this resource is one of their objectives and they haven't take all they need
     */
    public boolean isObjective(String resource){
        Resource res = new Resource(resource);
        if(objective.isObjective(res)){
            if(inventory.get(res)==null) return true;
            return inventory.get(res) < objective.getObjective(res);
        }
        return false;
    }
    
    /**
     * Check if a Resource is in the inventory
     * @param resource
     *          the resource we want to check
     * @return true if it's in the inventory, false otherwise
     */
    public boolean isInInventory(String resource){
        Resource res = new Resource(resource);
        return inventory.containsKey(res);
    }

    /**
     * Return the amount of the resource we have in our inventory
     * @param resource
     *          the resource we want to check the amount we have
     * @return the number of this resource we have, 0 if we haven't any
     */
    public int getAmountInInventory(String resource){
        Resource res = new Resource(resource);
        Integer amount = inventory.get(res);
        if(amount!=null){
            return amount;
        }
        return 0;
    }
    
    /**
     * Modify the amount of a certain resource of the HashMap
     * @param resource
     *          the resource we exploited
     * @param amount
     *          the amount of it we have
     */
    public void exploitResource(String resource, int amount){
        Resource res = new Resource(resource);
        int newAmount = amount;
        if(inventory.containsKey(res)){
            newAmount+=inventory.get(res);
        }
        inventory.put(res,newAmount);
        objective.updateObjectives(inventory);
    }
    
    /**
     * Remove a resource in a specific amount from the inventory
     * @param resource
     *          the resource we want to remove
     * @param amount
     *          the amount of this resource we want to remove
     */
    public void removeResource(String resource,int amount){
        Resource res = new Resource(resource);
        int oldAmount = inventory.get(res);
        if((oldAmount-amount)<=0){
            inventory.remove(res);
        }else{
            inventory.put(res, oldAmount-amount);
        }
    }

    /**
     * Check if the team has still an objective
     * @return true if there isn't any objectives, false otherwise
     */
    public boolean noMoreObjectives(){
        for(Resource res : objective.getObjectives().keySet()){
            if(!inventory.containsKey(res) || inventory.get(res) < objective.getObjectives().get(res)){
                return false;
            }
        }
        return true;
    }
    
    /**
     * Method to know the rarity of an objective
     * @param resource
     *          the name of the resource we want to check its rarity
     * @return the Rarity of the resource that we want, null if it isn't an objective
     */
    public Rarity getRarityOf(String resource){
        Resource res = new Resource(resource);
        if(!this.isObjective(resource)) return null;
        return this.objective.getRarityOf(res);
    }
    
    public Map<SecondaryResource, Integer> canCreate(){
        Map<SecondaryResource, Integer> whatWeNeed;
        Map<SecondaryResource, Integer> whatWeNeedClean = new HashMap<SecondaryResource,Integer>();
        Map<SecondaryResource,Integer> whatWeCanCreate = new HashMap<SecondaryResource,Integer>();
        
        whatWeNeed = whatWeNeed();
        for(SecondaryResource res : whatWeNeed.keySet()){
            PrimaryResource[] resourcesNeeded = res.getComponent();
            double[] amount = res.getAmount();
            int i;
            for(i=0;i<resourcesNeeded.length;i++){
                Resource resource = new Resource(resourcesNeeded[i].toString());
                if(!inventory.containsKey(resource) || inventory.get(resource) < amount[i]){
                    break;
                }
            }
            if(i==resourcesNeeded.length){
                whatWeNeedClean.put(res,whatWeNeed.get(res));
            }
        }
        
        for(SecondaryResource res : whatWeNeedClean.keySet()){
            PrimaryResource[] resourcesNeeded = res.getComponent();
            double[] amount = res.getAmount();
            int i;
            List<Integer> amounts = new ArrayList<Integer>();
            for(i=0;i<resourcesNeeded.length;i++){
                int montant = (int) (inventory.get(new Resource(resourcesNeeded[i].toString()))/amount[i]);
                amounts.add(montant);
            }
            int min=amounts.get(0);
            for(int montant : amounts){
                if(montant<min) min = montant;
            }
            if(min>whatWeNeedClean.get(res)) min = whatWeNeedClean.get(res);
            if(min>0){
                whatWeCanCreate.put(res, min);
            }
        }
        return whatWeCanCreate;
    }

    /**
     * Get a map of what secondary resource we need
     * and in which quantity
     * @return a map with the secondary resources we need and in which amount
     */
    public Map<SecondaryResource, Integer> whatWeNeed(){
        Map<SecondaryResource,Integer> secondaryObjectives = objective.getSecondaryResourcesObjectives();
        Map<SecondaryResource, Integer> whatWeNeed = new HashMap<SecondaryResource,Integer>();
        
        for(SecondaryResource res : secondaryObjectives.keySet()){
            Resource resource = new Resource(res.toString());
            if(!inventory.containsKey(resource)){
                whatWeNeed.put(res, secondaryObjectives.get(res));
            }else if(inventory.get(resource) < secondaryObjectives.get(res)){
                whatWeNeed.put(res, secondaryObjectives.get(res) - inventory.get(resource));
            }
        }
        
        return whatWeNeed;
    }
    
    public int getMAXMEN(){
        return MAXMEN;
    }
    
    public int getMen(){
        return men;
    }
    
    public Map<Resource, Integer> getObjectives(){
        return objective.getObjectives();
    }
    
    public Map<Resource, Integer> getInventory(){
        return inventory;
    }
    
    /**
     * Two Equipe are equals if they have the same objectives
     * and the same number of men and MAXMEN.
     * @param obj
     *          the object we want to compare to this
     */
    @Override
    public boolean equals(Object obj){
        if(obj instanceof Equipe){
            Equipe equipe2=(Equipe) obj;
            return equipe2.getMAXMEN() == this.MAXMEN && equipe2.getObjectives().equals(this.getObjectives()) && equipe2.getMen()==this.men;
        }
        return false;
    }

    /**
     * The hashcode of Equipe
     * @return the hashcode
     */
    @Override
    public int hashCode(){
        int cle = 11;
        int code = 337;
        code = code * cle + MAXMEN;
        code = code * cle + objective.hashCode();
        code = code * cle + men;
        code = code * cle + inventory.hashCode();
        return code;
    }
    
}
