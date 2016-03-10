package fr.unice.polytech.ogl.islba.command;

import fr.unice.polytech.ogl.islba.model.EtatDeJeu;
import org.json.JSONException;
import org.json.JSONObject;
/**
 * Representation of a command to interact with the engine through the string produced by the method toJSON
 */
public abstract class Command {
    //the name of the command
    private String name;
    
    /**
     * Get the name of the command
     * @return name
     */
    public String getName(){
        return this.name;
    }
    
    /**
     * Set the name of the command to name
     * @param name
     *          the new name of the command
     */
    public void setName(String name){
        this.name=name;
    }
    
    /**
     * Convert the command into a String representing a JSON
     * @return a JSONObject
     */
    public abstract JSONObject toJSON();
    
    /**
     * Parse the JSONObject result and apply the changes at the EtatDeJeu object
     * @param result
     *          the JSONObject with the result of the command
     * @param jeu
     *          the current game state that we want to change
     */
    public void doResult(JSONObject result, EtatDeJeu jeu){
        try{
            int cost = result.getInt("cost");
            jeu.reducePA(cost);
        }catch(JSONException e){
            throw new JSONRuntimeException("Error in parsing the result JSONObject in command."+this.getName()+"."+e);
        }
    }
}
