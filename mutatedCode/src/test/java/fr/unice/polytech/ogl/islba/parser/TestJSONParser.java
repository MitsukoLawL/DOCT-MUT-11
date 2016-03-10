package fr.unice.polytech.ogl.islba.parser;

import static org.junit.Assert.*;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

public class TestJSONParser {
    JSONParser p;
    
    @Before
    public void beforeAll(){
        p=new JSONParser();
    }
    
    @Test
    public void testStringtoJSON() {
        String data = "{\"name\":\"thing\",\"table\":[\"thing\",\"thing2\",\"thing3\"]}";
        JSONObject jsonData=p.StringtoJSON(data);
        JSONObject jsonDataReal=new JSONObject(data);
        assertEquals(jsonDataReal.get("name"),jsonData.get("name"));
        for(int i=0;i<3;i++){
            assertEquals(jsonDataReal.getJSONArray("table").get(i),jsonData.getJSONArray("table").get(i));
        }
    }

    @Test (expected=org.json.JSONException.class)
    public void testStringtoJSONFalse(){
        String dataFalse="{\"name\":\"thing\"";
        p.StringtoJSON(dataFalse);
    }
    
    @Test
    public void testJSONtoString() {
        String data = "{\"name\":\"thing\",\"table\":[\"thing\",\"thing2\",\"thing3\"]}";
        JSONObject jsonData = p.StringtoJSON(data);
        assertEquals(data,p.JSONtoString(jsonData));
    }
}
