/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mtd.data.create;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;
import org.json.JSONObject;

/**
 *
 * @author sjack
 */
public class EventPicker {

    private final JSONObject EVENTS;
    private final String[] UIDs;
    private final TreeSet<String> usedUIDs;
    private final Random RNG;
    private final int quantity;

    public EventPicker(JSONObject events) {
        this.EVENTS = events;
        this.quantity = defineNumberOfEventsInJson();
        this.UIDs = new String[quantity];
        this.RNG = new Random();
        putUIDsIntoIntegerArray();
        usedUIDs = new TreeSet<>();
    }
    

    public String selectEventNotYetSelected() {
        boolean foundSuitableUID = false;
        if (this.usedUIDs.size() == this.UIDs.length) {
            return "";
        }
        while (!foundSuitableUID) {
            Integer chosenNumber = (createSuitableRandom());
            if (chosenNumber > UIDs.length || chosenNumber < 0) {
                return "";
            }
            String key = UIDs[chosenNumber];
            if (!usedUIDs.contains(key)) {
                usedUIDs.add(key);
                return key;
            }
        }
        return "";
    }

    private void putUIDsIntoIntegerArray() {
        int count = 0;
        Set<String> keySet = this.EVENTS.keySet();
        
        for (String key : keySet) {

            this.UIDs[count] = key;
            count++;
        }

    }

    private int defineNumberOfEventsInJson() {
        int quantityOfEvents = this.EVENTS.length();
        if (quantityOfEvents >= 1) {
            return quantityOfEvents;
        } else {
            return -1;
        }
    }

    private int createSuitableRandom() {
        return this.RNG.nextInt(this.quantity);
    }

    public JSONObject getEVENTS() {
        return EVENTS;
    }

    public String[] getUIDs() {
        return UIDs;
    }

    public TreeSet<String> getUsedUIDs() {
        return usedUIDs;
    }

    public Random getRNG() {
        return RNG;
    }

    public int getQuantity() {
        return quantity;
    }
    
    

}
