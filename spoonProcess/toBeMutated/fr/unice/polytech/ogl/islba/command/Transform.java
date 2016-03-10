package fr.unice.polytech.ogl.islba.command;


import java.util.Iterator;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import fr.unice.polytech.ogl.islba.model.Equipe;
import fr.unice.polytech.ogl.islba.model.EtatDeJeu;

public class Transform extends Command {

	Map<String, Integer> resources;

	/**
	 * Constructor of a Transform Command.
	 * We need the ressource to transform in the hashmap in parameter
	 * @param resourcesForTransform	
	 *         the resources to transform
	 */
	public Transform(Map<String, Integer> resourcesForTransform){
		this.setName("transform");
		this.resources = resourcesForTransform;
	}

	/**
	 * Create a JSON Object which represent the current Transform Command
	 * @return the JSON Object
	 */
	@Override
	public JSONObject toJSON() {
		JSONObject obj = new JSONObject();
		JSONObject param = new JSONObject();
		try {
			obj.put("action", this.getName());
			if(this.resources != null){
				Iterator it = resources.entrySet().iterator();
				while (it.hasNext()) {
					Map.Entry pair = (Map.Entry)it.next();
					param.put((String) pair.getKey(), (Integer) pair.getValue());
				}
			}
			obj.put("parameters", param);


		} catch (JSONException e) {
			throw new JSONRuntimeException("Problem with Transform.toJSON"+e);
		}
		return obj;
	}

	@Override
	public void doResult(JSONObject result, EtatDeJeu jeu){
		super.doResult(result, jeu);
		String kind;
		int production;
		try{
			kind = result.getJSONObject("extras").getString("kind");
			production = result.getJSONObject("extras").getInt("production");
		}catch (JSONException e){
			throw new JSONRuntimeException("Problem with Transform.doResult"+e);
		}
		Equipe team = jeu.getTeam();
		team.exploitResource(kind, production);
		Iterator it = resources.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry)it.next();
				team.removeResource((String)pair.getKey(), (Integer)pair.getValue());
			}
		
	}
}
