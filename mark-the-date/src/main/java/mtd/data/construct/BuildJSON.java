package mtd.data.construct;

import mtd.data.models.Event;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONWriter;
import mtd.data.load.EventLoader;

/**
 *
 * @author sjack
 */
public class BuildJSON { 
    public static void main(String[] args) throws FileNotFoundException, IOException {

        BufferedReader br = new BufferedReader(new FileReader("/home/sjack/response-ad3.html"));
        String line = br.readLine();
        List<String> lines = new ArrayList<String>();

        while (line != null) {
            lines.add(line);
            line = br.readLine();
        }
        System.out.println(lines.toString());

        final JSONObject currentFile = EventLoader.getJSONRoot();
        JSONObject eventsObject = currentFile.getJSONObject("events");
        final int BEGIN_FROM_ID = currentFile.getJSONObject("events").length() + 1;
        System.out.println(BEGIN_FROM_ID);
        int count = BEGIN_FROM_ID;
        for (String cs : lines) {
            if (cs.contains(":")) {
                String[] pair = cs.split(":");
                System.out.println(pair[0]);
                final Integer year = Integer.parseInt(pair[0].trim());
                final String desc = pair[1].trim();
                JSONObject event = new JSONObject();
                event.put("desc", desc);
                event.put("year", year);
                String eventHash = String.valueOf(new Event(year, desc).hashCode());
                eventsObject.put(eventHash, event);
            }
        }
        System.out.println(eventsObject.toString());
//        eventsObject.write(new FileWriter("/home/sjack/test.json"));
        BufferedWriter bw = new BufferedWriter(new FileWriter(new File("/home/sjack/test.json")));

        try {
            bw.write(eventsObject.toString());

        } catch (IOException e) {

        } finally {
            bw.close();
        }

    }
}
