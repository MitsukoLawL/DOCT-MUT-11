package fr.unice.polytech.ogl.islba.command;

import fr.unice.polytech.ogl.islba.model.Direction;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

import fr.unice.polytech.ogl.islba.model.EtatDeJeu;
import fr.unice.polytech.ogl.islba.model.Coordonnees;
import fr.unice.polytech.ogl.islba.model.Case;
import fr.unice.polytech.ogl.islba.model.OutOfMapCase;
import fr.unice.polytech.ogl.islba.model.resource.Resource;

/**
 * Representation of a Scout command for the Island.
 *
 */
public class Scout extends Command {
    //the Direction where we want to go
    private Direction direction;

    /**
     * Constructor of the Scout class
     * @param direction
     *          the Direction where we want to scout
     */
    public Scout(Direction direction){
        this.setName("scout");
        this.direction = direction;
    }
    
    /**
     * Convert the current instance of Scout into a JSON object
     * @return the JSONObject
     */
    @Override
    public JSONObject toJSON() {
        JSONObject obj = new JSONObject();
        JSONObject param = new JSONObject();
        try {
            obj.put("action", this.getName());
            param.put("direction", direction.getDirection());
            obj.put("parameters", param);
        } catch (JSONException e) {
            throw new JSONRuntimeException("Problem with Scout.toJSON()"+e);
        }
        return obj;
    }
    
    /**
     * Get and analyze the result of the Scout command
     */
    @Override
    public void doResult(JSONObject result, EtatDeJeu jeu){
        super.doResult(result,jeu);
        JSONObject res;
        try{
            res=result;
            //on choppe la coordoonnées de la prochaine case
            Coordonnees cooNextCase = Coordonnees.add(direction.getCoo(), (jeu.getMapMonde().getCurrentCoo()));
            //ainsi que la prochaine case dans la map
            Case nextCase = jeu.getMapMonde().getCase(cooNextCase);
            if(nextCase == null){
            	nextCase = new Case();
            }
            //on recupere dans le JSON les ressources
            JSONObject extras = res.getJSONObject("extras");
            JSONArray resources = extras.getJSONArray("resources");
            String resource;
            //et on les ajoute à la case
            for(int i=0;i<resources.length();i++){
                resource = resources.getString(i);
                nextCase.addRessource(new Resource(resource, "unknown", "unknown"));
            }
            if(extras.has("unreachable")){
                if(extras.getBoolean("unreachable")){
                    nextCase = new OutOfMapCase();
                }
            }
            nextCase.setScouted(true);
            //on l'ajoute à la map
            jeu.getMapMonde().addCaseMap(cooNextCase, nextCase);
        }catch (JSONException e){
            throw new JSONRuntimeException("Problem with Scout.doResult"+e);
            
        }
    }
}
