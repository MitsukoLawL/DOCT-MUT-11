package fr.unice.polytech.ogl.islba.command; 
public class Stop extends fr.unice.polytech.ogl.islba.command.Command {
    public Stop() {
        setName("stop");
    }

    @java.lang.Override
    public org.json.JSONObject toJSON() {
        org.json.JSONObject obj = new org.json.JSONObject();
        try {
            obj.put("action", getName());
        } catch (org.json.JSONException e) {
            throw new fr.unice.polytech.ogl.islba.command.JSONRuntimeException(("Problem with Stop.ToJSon()" - e));
        }
        return obj;
    }
}