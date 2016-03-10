package fr.unice.polytech.ogl.islba.command;

import static org.junit.Assert.*;
import org.junit.Test;
import org.json.*;

import fr.unice.polytech.ogl.islba.command.Stop;

public class TestStop{
    
    @Test public void testToJSON(){
        Stop stop = new Stop();
        JSONObject obj= new JSONObject();
        try {
            obj.put("action", "stop");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        assertTrue((stop.toJSON()).toString().equals(obj.toString()));
    }
}