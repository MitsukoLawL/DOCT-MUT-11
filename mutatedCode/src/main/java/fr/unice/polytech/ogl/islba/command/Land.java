package fr.unice.polytech.ogl.islba.command;

import org.json.JSONException;
import org.json.JSONObject;

import fr.unice.polytech.ogl.islba.model.EtatDeJeu;
import fr.unice.polytech.ogl.islba.model.Case;

/**
 *Command Land for the Island.
 *Build the Command as a JSON and can apply the changes on the EtatDeJeu
 *after receiving the results of the command on the game arena.
 */
public class Land extends Command {
    //number of man to land
    private int nbMen;
    //creek to land
    private String creek;

    /**
     * A Land command needs the number of men we want to land
     * and the id of the creek where we want to land
     * @param nbMen
     *          the number of men we want to land on the island
     * @param creek
     *          the id of the creek where we want to land
     */
    public Land(int nbMen, String creek){
        this.nbMen = nbMen;
        this.creek = creek;
        this.setName("land");
    }

    /** @return the JSONObject for landing
     * =>   {   "action": "land",
     *          "parameters": {"creek": "creek_id", "people": 42 }
     *      }
     */
     @Override
    public JSONObject toJSON() {
        JSONObject obj = new JSONObject();
        JSONObject param = new JSONObject();
        try {
            obj.put("action", this.getName());
            param.put("creek", creek);
            param.put("people", nbMen);
            obj.put("parameters", param);
        } catch (JSONException e) {
            throw new JSONRuntimeException("Problem with Land.toJSON"+e);
        }
        return obj;
    }
     
     /**
      * Apply the results of the Land Command
      * thanks to the result JSONObject that the arena
      * sent us.
      * @param result
      *             a JSONObject which represent the result of our Land command
      * @param jeu
      *             the EtatDeJeu we have to modify after the Land Command has been executed                
      */
     @Override
     public void doResult(JSONObject result, EtatDeJeu jeu){
         super.doResult(result,jeu);
         try{
             //TODO a changer
             Case caseLanded = new Case();
             caseLanded.setMoveOn(true);
             jeu.getMapMonde().addCaseMap(0,0,caseLanded);
             jeu.getMapMonde().setCurrentCoo(0,0);
         }catch (JSONException e){
             throw new JSONRuntimeException("Problem with Land.doResult"+e);   
         }
     }
}
