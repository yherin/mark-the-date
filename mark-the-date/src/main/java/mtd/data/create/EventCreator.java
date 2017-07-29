
package mtd.data.create;

import mtd.data.models.Event;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import mtd.data.load.EventLoader;

import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author sjack
 */
public class EventCreator {

    private final EventLoader eventLoader;
    private final int quantity;
    private final JSONObject eventsJSON;
    private final EventPicker eventPicker;
    private final List<Event> processedEvents;

    public EventCreator(int quantity) {
        eventLoader = new EventLoader(); //not a test
        this.quantity = quantity;

        eventsJSON = eventLoader.getJSONRoot().getJSONObject("events");
        processedEvents = new ArrayList<>();
        eventPicker = new EventPicker(eventsJSON);
    }

    public List<Event> createEvents() {
        return handleEventCreationLoop();
    }

    private List<Event> handleEventCreationLoop() {
        ArrayList<Event> listOfEvents = new ArrayList<>();
        for (int i = 0; i < quantity; i++) {
            String key = eventPicker.selectEventNotYetSelected();
            
            Event event = createIndividualEventObject(key);
            listOfEvents.add(event);
        }
        return listOfEvents;
    }

    private Event createIndividualEventObject(String key) {
        try {
            JSONObject event = this.eventsJSON.getJSONObject(key);
            Integer year = event.getInt("year");
            String desc = event.getString("desc");
            return new Event(year, desc);
        } catch (JSONException e) {
            System.out.println(e.getMessage());

        }
        throw new IllegalStateException("event object creation failed");

    }
}
