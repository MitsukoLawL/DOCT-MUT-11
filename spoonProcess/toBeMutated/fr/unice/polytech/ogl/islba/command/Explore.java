package fr.unice.polytech.ogl.islba.command;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import fr.unice.polytech.ogl.islba.model.EtatDeJeu;
import fr.unice.polytech.ogl.islba.model.resource.Resource;

public class Explore extends Command {

    public Explore(){
        this.setName("explore");
    }

    /**
     * Create the JSONObject corresponding to the current Explore command
     * the form is : {"action":"explore"}
     */
    @Override
    public JSONObject toJSON() {
        JSONObject obj = new JSONObject();
        try {
            obj.put("action", this.getName());
        } catch (JSONException e) {
            throw new JSONRuntimeException("Problem with Explore.toJSON()"+e);
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
        super.doResult(result, jeu);
        try{
        	JSONObject res = result;
        	JSONArray ressources = res.getJSONObject("extras").getJSONArray("resources");
        	jeu.getMapMonde().getCurrentCase().reinitializeRessources();
        	for(int i = 0; i<ressources.length(); i++){
        		String resourceName = ressources.getJSONObject(i).getString("resource");
        		String amount  = ressources.getJSONObject(i).getString("amount");
        		String cond =  ressources.getJSONObject(i).getString("cond");
        		jeu.getMapMonde().getCurrentCase().addRessource(new Resource(resourceName, amount, cond));
        		
        	
        	}
        	//TODO faire le pois
        } catch(JSONException e){
        	throw new JSONRuntimeException("Problem with Explore.doResult"+e);
        }
    }
}
