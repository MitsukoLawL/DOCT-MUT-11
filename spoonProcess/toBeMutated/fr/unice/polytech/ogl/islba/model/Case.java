package fr.unice.polytech.ogl.islba.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.unice.polytech.ogl.islba.model.resource.Resource;

/**
 * Cette classe représente une case de la carte
 * @author Cédric Lallemand
 *
 */
public class Case {

	private List<Resource> ressources; //Exemple Key "WOOD" Values : "HIGHT,EASY"
	private Map<String, Double> biomes;
	//precision of the glimpse on this case. If -1 : never glimpse
	private int numberCaseGlimpse;
	//true if we moved on it
	private boolean moveOn;
	//true if we have scouted it
	private boolean scouted;
	
	/**
	 * Constructor of the class Case, call to create a new Case
	 */
	public Case(){
		ressources = new ArrayList<>();
		biomes = new HashMap<String, Double>();
		scouted=false;
		moveOn=false;
		numberCaseGlimpse=-1;
	}

	/**
	 * @return the ressources
	 */
	public List<Resource> getRessources() {
		return ressources;
	}
	
	/**
	 * Reinitialize the ressources list
	 */
	public void reinitializeRessources(){
	    ressources = new ArrayList<Resource>();
	}
	/**
	 * Add a resource to the case, if the resource already exists the method replace the value
	 * @param newRes
	 */
	public void addRessource(Resource newRes){
	    ressources.add(newRes);
	}
		
	/**
	 * Return true if the resource give in parameter is present in the case
	 * @param ressource
	 * @return true if there is the resource on the case
	 */
	public boolean hasRessource(String ressource){
    	for(int i = 0; i<ressources.size(); i++){
    		if(ressources.get(i).getName().equals(ressource)){
    			return true;
    		}
    	}
    	return false;
	}
	
	/**
	 * Check if there is a resource with a specific amount and cond
	 * @param amount
	 *             the amount of this resource on this Case (HIGH, MEDIUM, or LOW)
	 *             which is different of null
	 * @param cond
	 *             the condition of this resource on this Case (EASY, FAIR, HARSH)  
	 *             which is different of null                      
	 * @return a List of String which contains the names of 
	 *         the resources which are in the same conditions that are asked
	 */
	public List<String> getResourcesWithAmountAndCond(String amount, String cond){
	    List<String> possibleResources=new ArrayList<String>();
	    String amountRes, condRes;
	    for(Resource res : ressources){
	        amountRes=res.getAmount();
	        condRes = res.getDifficulty();
	        if(amountRes!=null && condRes!=null && amountRes.equals(amount) && condRes.equals(cond)){
                possibleResources.add(res.getName()); 
	        }
	    }
	    return possibleResources;
	}
	
	/**
	 * Add a biome to the case 
	 * @param biome
	 * @param percentage - percentage of the biome on the case, -1 if unknown
	 */
	public void addBiome(String biome, Double percentage){
		this.biomes.put(biome, percentage);
	}
	
	/**
	 * Return true if the biome give in parameter is present in the case
	 * @param biome
	 * @return true if there is the biome on the case
	 */
	public boolean hasBiome(Biome biome){
		if(biomes.containsKey(biome.toString())){
			return true;
		}
		return false;
		
	}
	
	/**
	 * Call to know the percentage of the biome give in parameter in the case
	 * @param biome
	 * @return -1 if the case doesn't contains the binome, 0 if the percentage isn't know, otherwise the percentage
	 */
	public double getBiomePercentage(Biome biome){
		if(!hasBiome(biome)){
			return -1;
		} else {
			return biomes.get(biome.toString());
		}
	}

	/**
	 * Return the number of the case glimpse to know the precision of the biome or -1 if never glimpse
	 * @return precision glimpse between : 0 : high precision and 3 : low precision
	 */
	public int getNumberCaseGlimpse(){
		return numberCaseGlimpse;
	}
	
	/**
	 * Set the precision of glimpsed on this case
	 * @param n
	 */
	public void setNumberCaseGlimpse(int n){
		numberCaseGlimpse  = n;
	}
	
	/**
	 * Permits to know if we have already moved on this Case before
	 * @return moveOn
	 */
	public boolean getMoveOn(){
	    return moveOn;
	}
	
	/**
	 * Change the fact that we have already move or not on this Case.
	 * @param newMoveOn
	 *             the new boolean value for moveOn
	 */
	public void setMoveOn(boolean newMoveOn){
	    this.moveOn=newMoveOn;
	}
	
	/**
	 * Get scouted which permits to know if we have already scouted this Case
	 * @return scouted
	 */
	public boolean getScouted(){
	    return scouted;
	}
	
	/**
     * Change the scouted by a new boolean value
     * @param newScouted
     *             the new boolean value that we want for this Case
     */
    public void setScouted(boolean newScouted){
        this.scouted=newScouted;
    }
	
	/**
	 * Return true if the map is on the map
	 * @return
	 */
	public boolean isOnMap(){
		return true;
	}

	/**
	 * Remove the resource with the name passed in parameter
	 * from the ressources list of the Case
	 * @param ressource
	 *             the ressource we want to remove from the Case
	 */
    public void removeResource(String ressource) {
        this.ressources.remove(new Resource(ressource));
    }
}
