/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mtd.model.create;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import mtd.model.create.EventPicker;
import mtd.model.create.EventPicker;
import mtd.model.load.EventLoader;
import org.json.JSONException;
import org.json.JSONObject;
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
public class EventPickerTest {

    private EventPicker ep1;
    private EventPicker ep2;
    private EventPicker ep3;
    private EventLoader el;
    private JSONObject jso;
    private int shouldFailAfterThisManyEvents;

    /*
    private final String[] UIDs;
    private final TreeSet<String> usedUIDs;
    private final Random RNG;
    private final int quantity;
     */
    public EventPickerTest() throws JSONException {

        jso = new EventLoader().getJSONRoot().getJSONObject("events");
        ep1 = new EventPicker(jso);
        ep2 = new EventPicker(jso);
        ep3 = new EventPicker(jso);

    }

    @Before
    public void setupEventPicker1() {
        shouldFailAfterThisManyEvents = jso.length() + 1;
        ep1.selectEventNotYetSelected();
    }

    @Before
    public void setupEventPicker2() {
        shouldFailAfterThisManyEvents = jso.length() + 1;
        for (int i = 0; i < shouldFailAfterThisManyEvents - 2; i++) {
            ep2.selectEventNotYetSelected();
        }

    }

    @Before
    public void setupEventPicker3() {
        //intentially no setup
    }

    @Test
    public void eventPickerReturnsErrorStringWhenNoUnpickedEvents() {
        assertNotEquals("eventPicker returned \"NO_EVENT\" after first call", ep2.selectEventNotYetSelected(), "NO_EVENT");
        for (int i = 0; i < shouldFailAfterThisManyEvents + 1; i++) {
            ep2.selectEventNotYetSelected();
        }
        assertEquals("eventPicker did not return \"NO_EVENT\" when calling more than number of events",
                ep2.selectEventNotYetSelected(), "NO_EVENT");
    }

    @Test
    public void arrayOfIDsIsCreatedInConstructor() {
        assertNotNull("constructor failed to create String[] uid",
                ep3.getUIDs());
        assertTrue("String[] uid was empty", ep3.getUIDs().length > 0);
    }

    @Test
    public void usedUIDsEmptyWhenNoneUsed() {
        Set<String> usedUniqueIDs = ep3.getUsedUIDs();
        assertTrue("some IDs were used when none should have been",
                usedUniqueIDs.isEmpty());
    }

    @Test
    public void usedUIDsNotEmptyWhenUsed() {
        Set<String> usedUniqueIDs = ep2.getUsedUIDs();
        String chosen = ep2.selectEventNotYetSelected();
        assertTrue("chosen ID was not in used IDs list",
                ep2.getUsedUIDs().contains(chosen));

    }

    @Test
    public void validEventsJSONObject() {
        assertNotNull("null JSONObject", jso);
        assertTrue("empty JSONObject", jso.length() >= 1);

    }

}
