package fr.unice.polytech.ogl.islba.command;

import fr.unice.polytech.ogl.islba.model.Direction;
import org.json.JSONException;
import org.json.JSONObject;

import fr.unice.polytech.ogl.islba.model.Case;
import fr.unice.polytech.ogl.islba.model.Coordonnees;
import fr.unice.polytech.ogl.islba.model.EtatDeJeu;

/**
 * Representation of a Move command for the Island.
 *
 */
public class Move extends Command {
	//the Direction where we want to move
	private Direction direction;

	/**
	 * Constructor of a Move Command.
	 * We just need a Direction to move somewhere.
	 * @param direction
	 *         the direction where we want to go
	 */
    public Move(Direction direction){
        this.setName("move_to");
        this.direction = direction;
    }
    
    /**
     * Create a JSON Object which represent the current Move Command
     * @return the JSON Object
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
            throw new JSONRuntimeException("Problem with Move.toJSON"+e);
        }
        return obj;
    }
    
    /**
     * Parse the JSONObject result and apply the changes at the EtatDeJeu object.
     * The JSON from a move command is not really different from the land : 
     * it's just the cost in PA.
     * The doResult method here will just change the EtatDeJeu to do the move.
     * 
     * @param result
     *          the JSONObject with the result of the command
     * @param jeu
     *          the current game state that we want to change
     */
    @Override
    public void doResult(JSONObject result, EtatDeJeu jeu){
        super.doResult(result, jeu);
        Coordonnees nextCoo = Coordonnees.add(jeu.getMapMonde().getCurrentCoo(), (direction.getCoo()));
        Case nextCase = jeu.getMapMonde().getCase(nextCoo);
        if(nextCase==null){
            nextCase = new Case();
            jeu.getMapMonde().addCaseMap(nextCoo, nextCase);
        }
        nextCase.setMoveOn(true);
        jeu.getMapMonde().setCurrentCoo(nextCoo);
    }
}
