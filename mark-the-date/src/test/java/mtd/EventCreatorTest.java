/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mtd;

import mtd.data.create.EventCreator;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author sjack
 */
public class EventCreatorTest {

    private EventCreator ec;
    private int desired = 3;

    public EventCreatorTest() {
        ec = new EventCreator(desired);
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void eventCreatorProducesCorrectNumberOfEvents() {
        int created = ec.createEvents().size();
        this.desired = 3;
        assertTrue("eventCreator produced " + created + " but should have only produced " + this.desired + ".",
                created == this.desired);
    }
}
