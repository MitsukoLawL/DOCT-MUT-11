package fr.unice.polytech.ogl.islba.command;

import fr.unice.polytech.ogl.islba.model.Direction;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import fr.unice.polytech.ogl.islba.model.Case;
import fr.unice.polytech.ogl.islba.model.Coordonnees;
import fr.unice.polytech.ogl.islba.model.EtatDeJeu;
import fr.unice.polytech.ogl.islba.model.OutOfMapCase;

public class Glimpse extends Command {

	private Direction direction;
	private int range;

	public Glimpse(Direction direction, int range) {
		this.setName("glimpse");
		this.direction = direction;
		if (range > 4) {
			this.range = 4;
		} else {
			this.range = range;
		}
	}

	/**
	 * Create the JSONObject which corresponds to the current Glimpse Command
	 * The form is : {"action":"glimpse", "parameters":{"direction": "N/E/S/W", "range": 1/2/3/4}}
	 */
	@Override
	public JSONObject toJSON() {
		JSONObject obj = new JSONObject();
		JSONObject param = new JSONObject();
		try {
			obj.put("action", this.getName());
			param.put("direction", direction.getDirection());
			param.put("range", range);
			obj.put("parameters", param);
		} catch (JSONException e) {
			throw new JSONRuntimeException("Problem with Glimpse.toJSON"+e);
		}
		return obj;
	}
	
    /**
     * Apply the results from the JSONObject in parameter
     * to the EtatDeJeu
     * @param result
     *          the JSONObject which contains all the informations about what happens after the command
     *          was executed
     * @param jeu
     *          the EtatDeJeu we have to modify
     */
    @Override
    public void doResult(JSONObject result, EtatDeJeu jeu){
    	super.doResult(result,jeu);
        try{
        	JSONObject res = result;
        	JSONArray ressources = res.getJSONObject("extras").getJSONArray("report");
        	int nbCaseGlimpse = ressources.length();
        	Coordonnees currentCoo = jeu.getMapMonde().getCurrentCoo();
        	Case c = jeu.getMapMonde().getCase(currentCoo);

        	for(int i = 0;i<nbCaseGlimpse;i++){	//for each case glimpse
        		if(c == null){
        			c = new Case();
        			jeu.getMapMonde().addCaseMap(currentCoo, c);
        		}        		
        		if(c.getNumberCaseGlimpse() == -1 || c.getNumberCaseGlimpse() > i){	//si nous avons déjà glimpse avec une meilleurs précision nous n'actualisons pas la case
	    			c.setNumberCaseGlimpse(i);
        			JSONArray biomesCase = ressources.getJSONArray(i);
	        		for(int j = 0;  j < biomesCase.length();j++){	//for each biome
        				if(i == 0 || i == 1){	//If there is a percentage 
        					JSONArray biome = biomesCase.getJSONArray(j); 
            				c.addBiome(biome.getString(0), biome.getDouble(1));
        				} else {	//else biome whithout percentage
        					String biome = biomesCase.getString(j);
            				c.addBiome(biome, (double) -1);
        				}
	        		}
        		}
        		currentCoo = Coordonnees.add(direction.getCoo(), currentCoo);
        		c = jeu.getMapMonde().getCase(currentCoo);
        	}
    		if(nbCaseGlimpse < range){	//si le glipse est en sortie de carte
    			for(int r = nbCaseGlimpse; r<range;r++){
    			    c = jeu.getMapMonde().getCase(currentCoo);
    			    if(c == null){
    			        c=new OutOfMapCase();
    			        jeu.getMapMonde().addCaseMap(currentCoo, c);
    			    }
    			    c.setNumberCaseGlimpse(r);
			        currentCoo = Coordonnees.add(direction.getCoo(), currentCoo);

    			}
    		}        	
        } catch(JSONException e){
        	throw new JSONRuntimeException("Problem with Glimpse.doResult"+e);
        }
    }
}








