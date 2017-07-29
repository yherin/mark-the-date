/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mtd.data.load;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import org.json.JSONObject;

/**
 *
 * @author sjack
 */
public class EventLoader {

    private final String EVENTS_FILE_NAME = "events.json";
    private final String EVENTS_FILE_PATH = "mtd/json/";

    public EventLoader() {
    }

    /**
     * Returns the root JSONObject from the events.json file. Access the key
     * pair values by calling .getJSONObject("events").
     *
     * @return the root JSONObject 
     * @see JSONObject
     */
    public JSONObject getJSONRoot() {

        return readEventJSONObject();
    }

    private InputStream getEventInputStream() {
        InputStream is = getClass().getClassLoader().getResourceAsStream(EVENTS_FILE_PATH + EVENTS_FILE_NAME);
        return is;
    }

    private BufferedReader getEventBufferedReader() {
        BufferedReader bf;
        InputStream is = getEventInputStream();
        assert (is != null);
        InputStreamReader inputReader = new InputStreamReader(is);

        bf = new BufferedReader(inputReader);
        return bf;
    }

    private JSONObject readEventJSONObject() {

        try (BufferedReader br = getEventBufferedReader()) {
            String jsonAsString = read(br);
            JSONObject jso = new JSONObject(jsonAsString);
            return jso;
        } catch (IOException e) {
            System.out.println("Fatal error when parsing events JSON. Stack: " + e.getLocalizedMessage());
            System.exit(-1);
        }
        throw new IllegalStateException("Error when passing JSON");

    }

    private String read(BufferedReader br) throws IOException {
        StringBuilder sb = new StringBuilder();
        try {
            String line = br.readLine();
            while (line != null) {
                sb.append(line);
                line = br.readLine();
            }
        } catch (IOException e) {
            System.out.println("Fatal error when parsing events JSON. Stack: " + e.getLocalizedMessage());
            System.exit(-1);
        } finally {
            try {
                br.close();
            } catch (IOException e) {
            };
        };
        return sb.toString();
    }

}
