/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mtd.data.load;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import org.json.JSONObject;

/**
 *
 * @author sjack
 */
public final class SettingsLoader {

    /**
     * The JSON file where event data is stored.
     */
    private static final String SETTINGS_FILE_NAME = "settings.json";
    /**
     * Path (location WITHIN /src/main/resources) to above file.
     */
    private static final String SETTINGS_FILE_PATH = "mtd/json/";
    private static final HashMap<String, Integer> SETTINGS = new HashMap<>();

    private SettingsLoader() {
        //not called
    }

    public static int getSetting(String key) {
        if (!SETTINGS.containsKey(key)) {
            JSONObject settingsFile = readSettingsJSONObject();
            SETTINGS.put(key, settingsFile.getInt(key));

        }
        return SETTINGS.get(key);
    }

    private static BufferedReader getSettingsBufferedReader() {
        BufferedReader bf;
        InputStream is = getSettingsInputStream();
        assert (is != null);
        InputStreamReader inputReader = new InputStreamReader(is);

        bf = new BufferedReader(inputReader);
        return bf;
    }

    private static InputStream getSettingsInputStream() {
        InputStream is = SettingsLoader.class.getClassLoader().getResourceAsStream(
                SETTINGS_FILE_PATH + SETTINGS_FILE_NAME);
        return is;
    }

    private static JSONObject readSettingsJSONObject() {

        try (BufferedReader br = getSettingsBufferedReader()) {
            String jsonAsString = read(br);
            JSONObject jso = new JSONObject(jsonAsString);
            return jso;
        } catch (IOException e) {
            System.out.println("Fatal error when parsing settings JSON. Stack: "
                    + e.getLocalizedMessage());
            System.exit(-1);
        }
        throw new IllegalStateException("Error when passing JSON");

    }

    private static String read(BufferedReader br) throws IOException {
        StringBuilder sb = new StringBuilder();
        try {
            String line = br.readLine();
            while (line != null) {
                sb.append(line);
                line = br.readLine();
            }
        } catch (IOException e) {
            System.out.println("Fatal error when parsing settings JSON. Stack: "
                    + e.getLocalizedMessage());
            System.exit(-1);
        } finally {
            try {
                br.close();
            } catch (IOException e) {
            }
        }
        return sb.toString();
    }
}
