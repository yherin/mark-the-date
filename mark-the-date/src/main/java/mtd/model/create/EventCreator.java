package mtd.model.create;

import java.util.ArrayList;
import java.util.List;
import mtd.model.load.EventLoader;
import mtd.model.load.SettingsLoader;
import mtd.model.models.Event;
import mtd.view.error.ErrorInformer;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Used to create Event objects based on data stored in events.json.
 *
 * @see Event
 * @see JSONObject
 * @author Jack Sheridan
 */
public class EventCreator {

    /**
     * The quantity of events to be created.
     */
    private final int quantity;
    /**
     * The JSONObject which will store the events.
     */
    private JSONObject eventsJSON;
    /**
     * EventPicker which will choose which specific events make up the created
     * events.
     */
    private EventPicker eventPicker;
    /**
     * List of the chosen events.
     */
    private List<Event> processedEvents;

    /**
     * Initialise a new EventCreator object. Instantiates an EventPicker and
     * EventLoader which choose and load event data respectively. Loads a
     * JSONObject from which Event objects can be created.
     */
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
     * Creates and returns a list of Events.
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
                this.eventPicker.getUsedUIDs().clear();
            }
        }
        return listOfEvents;
    }

    private Event createIndividualEventObject(String key) {
        JSONObject eventData;
        Integer year = -1;
        String desc = "null";
        try {
            eventData = this.eventsJSON.getJSONObject(key);
            year = eventData.getInt("year");
            desc = eventData.getString("desc");
            return new Event(year, desc);
        } catch (JSONException e) {
            ErrorInformer.showError(new JSONException(""), "Fatal error creating game data. Installation is corrupted - please reinstall.");

        }
        eventIsValid(year, desc);
        return new Event(year, desc);
    }

    private void eventIsValid(Integer year, String desc) {
        if (year == -1 || desc.equals("null")) {
            ErrorInformer.showError(new JSONException(""), "Fatal error creating game data. Installation is corrupted - please reinstall.");
        }
    }

}
