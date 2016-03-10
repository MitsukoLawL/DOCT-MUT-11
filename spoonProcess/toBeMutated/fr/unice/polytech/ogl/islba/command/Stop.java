package fr.unice.polytech.ogl.islba.command;
import org.json.*;

public class Stop extends Command {

    public Stop(){
        this.setName("stop");
    }
    
    /**
     * Create the JSONObject Stop which corresponds to the Stop Command
     * the form is : {"action":"stop"};
     * @return a JSONObject which corresponds to the stop
     */
    @Override
    public JSONObject toJSON() {
        JSONObject obj = new JSONObject();
        try {
            obj.put("action", this.getName());
        } catch (JSONException e) {
            throw new JSONRuntimeException("Problem with Stop.ToJSon()"+e);
        }
        return obj;
    }

}
