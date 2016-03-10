package fr.unice.polytech.ogl.islba.parser;

import org.json.JSONObject;

/**
 * Parser which transform the data passed in 
 * into JSON and vice versa
 *
 */
public interface Parser {
    /**
     * Transform the data passed in parameter
     * into a JSON. The data can follow a format
     * which is predefined
     * @param data
     *          the data that we want to transform into JSON
     * @return the corresponding JSONObject
     */
    public JSONObject StringtoJSON(String data);
    
    /**
     * Transform the JSON in parameter into a String
     * which follow a predefined format (as JSON, HTML, XML, etc...)
     * @param jsonData
     *          as data as a JSON
     * @return a String which follow the predefined format
     */
    public String JSONtoString(JSONObject data);
}
