/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mtd.data.models;

import mtd.data.models.Historical;

/**
 *
 * @author sjack
 */
public class Event extends Historical {

    public Event(Integer date, String description) {
        super(date, description);
    }

}
