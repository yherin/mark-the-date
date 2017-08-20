/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mtd.model.create;

import java.util.List;
import mtd.model.create.EventCreator;
import mtd.model.create.EventCreator;
import mtd.model.load.SettingsLoader;
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
public class EventCreatorTest {

    private EventCreator ec;
    private int desired = 3;

    public EventCreatorTest() {
        ec = new EventCreator();
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void eventCreatorProducesCorrectNumberOfEvents() {
        int created = ec.createEvents().size();
        this.desired = new SettingsLoader().getSetting("number_of_events");
        assertTrue("eventCreator produced " + created + " but should have only produced " + this.desired + ".",
                created == this.desired);
    }

    @Test
    public void eventCreatorProducedUniqueEvents() {
        List<Event> list1 = ec.createEvents();
        List<Event> list2 = ec.createEvents();
        for (Event event2 : list2) {
            for (Event event1 : list1) {
                assertFalse(event2.equals(event1));
            }
        }
    }
}
