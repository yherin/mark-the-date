/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mtd.model.load;

import java.util.HashMap;
import javax.swing.JFrame;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Jack Sheridan
 */
public final class SettingsLoader extends Loader {

    private static final HashMap<String, Integer> SETTINGS = new HashMap<>();

    public SettingsLoader() {
        super();
    }

    public final int getSetting(String key) {
        try {
            if (!SETTINGS.containsKey(key)) {
                JSONObject settingsFile = readJSONObject();
                SETTINGS.put(key, settingsFile.getInt(key));
                return SETTINGS.get(key);
            } else {

                return SETTINGS.get(key);
            }
        } catch (JSONException | NullPointerException e) {
            return -1;
        }
    }

    @Override
    String filePathString() {
        String fp = "mtd/json/";
        return fp;
    }

    @Override
    String fileNameString() {
        String fn = "settings.json";
        return fn;
    }

}
