package mtd.model.create;

import java.util.ArrayList;
import java.util.List;
import mtd.model.load.EventLoader;
import mtd.model.load.SettingsLoader;
import mtd.model.models.Event;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Jack Sheridan
 */
public class EventCreator {

    private final int quantity;
    private JSONObject eventsJSON;
    private EventPicker eventPicker;
    private List<Event> processedEvents;

    public EventCreator() {
        this.quantity = new SettingsLoader().getSetting("number_of_events");
        try {
            eventsJSON = new EventLoader().getJSONRoot().getJSONObject("events");
            processedEvents = new ArrayList<>();
            eventPicker = new EventPicker(eventsJSON);
        } catch (JSONException e) {
            System.err.println(e.getCause());
        }

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
            if (!key.equals("NO_EVENT")) {
                Event event = createIndividualEventObject(key);
                listOfEvents.add(event);
            } else {
                i--;
                System.out.println("UUIDS RESET! Ran out of questions");
                this.eventPicker.getUsedUIDs().clear();
            }
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
            System.err.println(e.getCause());

        }
        throw new IllegalStateException("event object creation failed");

    }
}
