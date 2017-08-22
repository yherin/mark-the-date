/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mtd.model.create;

import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;
import org.json.JSONObject;

/**
 *
 * @author Jack Sheridan
 */
public class EventPicker {

    private final JSONObject events;
    private final String[] uniqueIDs;
    private final TreeSet<String> usedUIDs;
    private final Random randomNumberGenerator;
    private final int quantity;

    public EventPicker(JSONObject events) {
        this.events = events;
        this.quantity = defineNumberOfEventsInJson();
        this.uniqueIDs = new String[quantity];
        this.randomNumberGenerator = new Random();
        putUIDsIntoIntegerArray();
        usedUIDs = new TreeSet<>();
    }

    /**
     * Selects an event which has not yet been selected.
     *
     * @return The key corresponding to an Event object which has not yet been
     * returned by this EventPicker.
     * @return A blank String, when no unchosen key exists.
     */
    public String selectEventNotYetSelected() {
        boolean foundSuitableUID = false;
        if (this.usedUIDs.size() == this.uniqueIDs.length) {
            return "";
        }
        while (!foundSuitableUID) {
            Integer chosenNumber = (createSuitableRandom());
            if (chosenNumber > uniqueIDs.length || chosenNumber < 0) {
                return "";
            }
            String key = uniqueIDs[chosenNumber];
            if (!usedUIDs.contains(key)) {
                usedUIDs.add(key);
                return key;
            }
        }
        return "";
    }

    private void putUIDsIntoIntegerArray() {
        int count = 0;
//        Set<String> keySet = this.events.keySet();

//        for (String key : keySet) {
        Iterator<String> ite = this.events.keys();
        while (ite.hasNext()){
            this.uniqueIDs[count] = ite.next();
            count++;
        }
//        }

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
