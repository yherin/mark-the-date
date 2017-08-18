package mtd.model.create;

import mtd.model.models.Event;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import mtd.model.load.EventLoader;
import mtd.model.load.SettingsLoader;

import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Jack Sheridan
 */
public class EventCreator {

    private final int quantity;
    private final JSONObject eventsJSON;
    private final EventPicker eventPicker;
    private final List<Event> processedEvents;

    public EventCreator() {
        this.quantity = new SettingsLoader().getSetting("number_of_events");

        eventsJSON = new EventLoader().getJSONRoot().getJSONObject("events");
        processedEvents = new ArrayList<>();
        eventPicker = new EventPicker(eventsJSON);
    }

    /**
     * Creates a list of events.
     *
     * @return List of Event objects.
     */
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
