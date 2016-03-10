package fr.unice.polytech.ogl.islba.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.unice.polytech.ogl.islba.model.resource.PrimaryResource;
import fr.unice.polytech.ogl.islba.model.resource.Resource;
import fr.unice.polytech.ogl.islba.model.resource.ResourcesType;
import fr.unice.polytech.ogl.islba.model.resource.SecondaryResource;

/**
 * Represent the objective of the current exploration,
 * the objective is different resources with different amount.
 *
 */
public class Objective {
    //the objectives of the exploration
    private Map<Resource, Integer> objectives;
    //same objectives but classed by rarity
    private Map<Rarity, List<Resource>> objectivesByRarity;
    //objectives as PrimaryResources
    private Map<PrimaryResource, Integer> objectivesAsPrimaryResources;
    //SecondaryResources objectives that we have
    private Map<SecondaryResource, Integer> secondaryResourcesObjectives;

    //the bank which contains all the resources we know
    private ResourcesType bank;
    
    /**
     * Construct an Objective object
     * It will be composed by 2 Map : one with Resource as key and the amount the client is asking as value, 
     * and one with the Rarity as key and a List of Resource as value.
     * @param obj
     *          the Map with the objectives by string and amount
     */
    public Objective(Map<String,Integer> obj){
        //we initialize the two map
        this.objectives = new HashMap<Resource, Integer>();
        this.objectivesByRarity=new HashMap<Rarity, List<Resource>>();
        this.objectivesAsPrimaryResources = new HashMap<PrimaryResource, Integer>();
        this.secondaryResourcesObjectives = new HashMap<SecondaryResource, Integer>();
        this.bank = new ResourcesType();
        
        for(Rarity r : Rarity.values()){
            objectivesByRarity.put(r, new ArrayList<Resource>());
        }
        
        //and we put each Resource into them
        Resource resource;
        int amount;
        List<Resource> resources;
        for(String res : obj.keySet()){
            resource = new Resource(res);
            amount = obj.get(res);
            if(amount>0){
                objectives.put(resource,amount);
                fillObjectivesByRarity(resource, amount);
                fillObjectivesAsPrimaryResources(res,amount);
                if(bank.isSecondaryResource(res)){
                    secondaryResourcesObjectives.put(bank.getSecondaryResource(res), amount);
                }
            }
        }
    }
    
    /**
     * Fill the map objectivesByRarity with the resource passed in parameter.
     * If it is a secondary resource, we have to put the primary resource associated into the 
     * map, checking the total amount of this type of resource.
     * @param resource
     *          the resource we want to add to the map
     * @param amount
     *          the amount of the resource
     */
    private void fillObjectivesByRarity(Resource resource, int amount) {
        if(bank.isSecondaryResource(resource.getName())){
            SecondaryResource r2 = bank.getSecondaryResource(resource.getName());
            for(PrimaryResource r1 : r2.getComponentsNeeded().keySet()){
                addToTheObjectivesByRarity(new Resource(r1.toString()), (int)(amount * r2.getComponentsNeeded().get(r1)));
            }
        }
        addToTheObjectivesByRarity(resource, amount);
        
    }

    /**
     * Add the resource to the objectivesByRarity map int the specified amount
     * @param resource
     * @param amount
     */
    private void addToTheObjectivesByRarity(Resource resource, int amount){
        PrimaryResource prim = bank.getPrimaryResource(resource.getName());
        int ancientAmount = objectivesAsPrimaryResources.get(prim) != null ? 
                objectivesAsPrimaryResources.get(prim) : 0;
        Rarity ancientRar = Rarity.getRarityFromAmount(ancientAmount);
        int toRemove = -1;
        for(int i = 0; i<objectivesByRarity.get(ancientRar).size();i++){
            if((objectivesByRarity.get(ancientRar).get(i)).equals(resource)){
                toRemove = i;
                break;
            }
        }
        if(toRemove>-1){
            objectivesByRarity.get(ancientRar).remove(toRemove);
        }
        Rarity rar=Rarity.getRarityFromAmount(amount+ancientAmount);
        List<Resource> resources = objectivesByRarity.get(rar);
        resources.add(resource);
        objectivesByRarity.put(rar, resources);
    }
    
    /**
     * Fill the objectivesAsPrimaryResources map with the resource pass in parameter
     * and with the amount given. If the resource is a SecondaryResource we add to the map
     * the different PrimaryResources that we need to create it.
     * @param res
     *          the resource we want to add to the map
     * @param amount
     *          the amount of the resource we want to add to the map
     */
    private void fillObjectivesAsPrimaryResources(String res, int amount) {
        if(bank.isSecondaryResource(res)){
            Map<PrimaryResource, Double> secondaryRes = bank.getSecondaryResource(res).getComponentsNeeded();
            for(PrimaryResource primeRes : secondaryRes.keySet()){
                addResourceToObjectiveAsPrimaryResources(primeRes, (int)((secondaryRes.get(primeRes) * amount)));
            }
        }else if(bank.isPrimaryResource(res)){
            addResourceToObjectiveAsPrimaryResources(bank.getPrimaryResource(res), amount);
        }
    }

    /**
     * Add a PrimaryResource to the objectivesAsPrimaryResources map
     * @param res
     *          the PrimaryResource we want to add to the map
     * @param amount
     *          the amount of the PrimaryResource we want to add to the map
     */
    private void addResourceToObjectiveAsPrimaryResources(PrimaryResource res, int amount){
        int newAmount = amount;
        if(objectivesAsPrimaryResources.containsKey(res)){
            newAmount+= objectivesAsPrimaryResources.get(res);
        }
        objectivesAsPrimaryResources.put(res, newAmount);
    }
    
    /**
     * Check if a resource is one of the objectives
     * @param resource
     *              the resource we want to check
     * @return true if this resource is one of their objectives and they haven't take all they need
     */
    public boolean isObjective(Resource res){
        return objectives.containsKey(res) || objectivesAsPrimaryResources.containsKey(bank.getPrimaryResource(res.getName()));
    }
    
    /**
     * Update the resources from the objectivesAsPrimaryResources map
     * to always know what we need as objective.
     * @param inventory
     *          the current inventory we have
     */
    public void updateObjectives(Map<Resource, Integer> inventory){
        objectivesAsPrimaryResources.clear();
        for(Resource resource : objectives.keySet()){
            if(inventory.get(resource)==null){
                fillObjectivesAsPrimaryResources(resource.getName(), objectives.get(resource));
            }else if(inventory.get(resource) < objectives.get(resource)){
                fillObjectivesAsPrimaryResources(resource.getName(), objectives.get(resource)-inventory.get(resource));
            }
        }
    }
    
    /**
     * Method to know the rarity of an objective
     * @param resource
     *          the name of the resource we want to check its rarity
     * @return the Rarity of the resource that we want, null if it isn't an objective
     */
    public Rarity getRarityOf(Resource resource){
        for(Rarity r : Rarity.values()){
            if(objectivesByRarity.get(r).contains(resource)){
                return r;
            }
        }
        return null;
    }
    
    /**
     * Get the Objective Map
     * @return objectives, the map containing all the objectives
     */
    public Map<Resource, Integer> getObjectives(){
        return objectives;
    }
    
    /**
     * Return the number of a certain resource we need
     * to complete the objective
     * @param res
     *              the resource we want to check the amount we need
     * @return the amount of this resource we need
     */
    public int getObjective(Resource res){
        int amount = 0;
        if(objectives.containsKey(res)) amount += objectives.get(res);
        if(objectivesAsPrimaryResources.containsKey(bank.getPrimaryResource(res.getName())))
            amount += objectivesAsPrimaryResources.get(bank.getPrimaryResource(res.getName()));
        return amount;
    }
    
    /**
     * Get the ObjectiveAsPrimaryResources map
     * @return objectivesAsPrimaryResources, the map containing the objectives as PrimaryResources
     */
    public Map<PrimaryResource, Integer> getObjectiveAsPrimaryResources(){
        return objectivesAsPrimaryResources;
    }
    
    /**
     * Get the Objective By Rarity Map
     * @return objectivesByRarity, the map containing all the objectives
     */
    public Map<Rarity, List<Resource>> getObjectivesByRarity(){
        return objectivesByRarity;
    }
    
    /**
     * Get the secondaryResourcesObjectives map
     * @return secondaryResourcesObjectives, the map containing all the secondary resources objectives
     */
    public Map<SecondaryResource, Integer> getSecondaryResourcesObjectives() {
        return secondaryResourcesObjectives;
    }
    
}
