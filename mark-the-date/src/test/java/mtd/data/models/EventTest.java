/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mtd.data.models;

import mtd.model.models.Event;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Jack Sheridan
 */
public class EventTest {

    private Event e1;
    private Event e2;
    private Event e3;
    private Event e4;
    private Event e5;

    public EventTest() {
        e1 = new Event(400, "Battle of Milivan Bridge");
        e2 = new Event(300, "Battle of Milivan Bridge");
        e3 = new Event(812, "First Siege of Baghdad");
        e4 = new Event(812, "Not the First Siege of Baghdad");
        e5 = new Event(400, "Battle of Milivan Bridge");
    }

    @Test
    public void sameEventsProduceSameHashCode() {
        assertTrue("Same events produced different hash.", e1.hashCode() == e5.hashCode());
    }

    @Test
    public void sameDateDifferentDescription() {
        assertTrue("Events with different desc produced same hash:\n" + e3.toString() + ": " + e3.hashCode()
                + "\n" + e4.toString() + ": " + e4.hashCode(), e3.hashCode() != e4.hashCode());
    }

    @Test
    public void equalsMethodWorks() {
        assertTrue(e1.equals(e5));
        assertFalse(e1.equals(e2));
    }

}
