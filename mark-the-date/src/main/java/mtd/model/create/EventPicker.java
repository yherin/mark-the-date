package mtd.model.create;

import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;
import org.json.JSONObject;

/**
 * Used to select events randomly. Will select previously unselected events, until none are left.
 * When none are left, it will then mark all as unselected and start again.
 * @author Jack Sheridan
 */
public class EventPicker {

    /**
     * JSONObject represenation of events.json file.
     * @see EventLoader
     */
    private final JSONObject events;
    /**
     * Stores hashcodes of events contained in events.json
     * @see Historical#hashCode().
     */
    private final String[] uniqueIDs;
    /**
     * Set used to store hashcodes of already used Events.
     */
    private final TreeSet<String> usedUIDs;
    /**
     * Random number generator.
     */
    private final Random randomNumberGenerator;
    /**
     * Quantity of events to be loaded.
     */
    private final int quantity;

    /**
     *
     * @param events JSONObject represenation of the events file.
     */
    public EventPicker(JSONObject events) {
        this.events = events;
        this.quantity = defineNumberOfEventsInJson();
        this.uniqueIDs = new String[quantity];
        this.randomNumberGenerator = new Random();
        putUIDsIntoIntegerArray();
        usedUIDs = new TreeSet<>();
    }

    /**
     * Selects an String represenation of an event (from the JSONObject) which was previously unselected.
     * @return String key corresponding to an Event object which has not yet been
     * returned by this EventPicker OR String "NO_EVENT" if all events have been previously chosen.
     */
    public String selectEventNotYetSelected() {
        boolean foundSuitableUID = false;
        if (this.usedUIDs.size() == this.uniqueIDs.length) {
            return "NO_EVENT";
        }
        while (!foundSuitableUID) {
            Integer chosenNumber = (createSuitableRandom());
            if (chosenNumber < uniqueIDs.length && chosenNumber >= 0) {

                String key = uniqueIDs[chosenNumber];
                if (!usedUIDs.contains(key)) {
                    usedUIDs.add(key);
                    return key;
                }
            }
        }
        return "NO_EVENT";
    }

    private void putUIDsIntoIntegerArray() {
        int count = 0;
        Iterator<String> ite = this.events.keys();
        while (ite.hasNext()) {
            this.uniqueIDs[count] = ite.next();
            count++;
        }

    }

    private int defineNumberOfEventsInJson() {
        int quantityOfEvents = this.events.length();
        if (quantityOfEvents >= 1) {
            return quantityOfEvents;
        } else {
            return -1;
        }
    }

    private int createSuitableRandom() {
        return this.randomNumberGenerator.nextInt(this.quantity);
    }

    public JSONObject getEVENTS() {
        return events;
    }

    public String[] getUIDs() {
        return uniqueIDs;
    }

    public TreeSet<String> getUsedUIDs() {
        return usedUIDs;
    }

    public Random getRNG() {
        return randomNumberGenerator;
    }

    public int getQuantity() {
        return quantity;
    }
}
