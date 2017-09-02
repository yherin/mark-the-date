/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mtd.model.models;

/**
 * Implementation of abstract class Historical. No additional functionality.
 *
 * @author Jack Sheridan
 * @see Historical
 */
public class Event extends Historical {

    /**
     * Creates a new Event.
     *
     * @param date the year that the event occurred.
     * @param description the event's description.
     */
    public Event(Integer date, String description) {
        super(date, description);
    }

}
