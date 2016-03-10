package fr.unice.polytech.ogl.islba.parser;

import org.json.JSONObject;

/**
 * Json parser. Permits to transform a String which
 * followed the JSON format into a JSONObject and vice versa.
 *
 */
public class JSONParser implements Parser{

    /**
     * Transform the String pass in parameter
     * into a JSONObject
     * @param data
     *              the String which follow the JSON format
     * @return the JSONObject which corresponds to the String             
     */
    @Override
    public JSONObject StringtoJSON(String data) {
        return new JSONObject(data);
    }

    /**
     * Transform the JSONObject passed in parameter
     * into a String which follow the JSON format
     * @param data
     *          the JSONObject we want to transform into a String
     * @return the JSONObject as a String
     */
    @Override
    public String JSONtoString(JSONObject data) {
        return data.toString();
    }
    
}
