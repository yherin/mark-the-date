package mtd.model.load;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class SettingsLoaderTest {

    SettingsLoader loader;

    public SettingsLoaderTest() {
        loader = new SettingsLoader();
    }

    @Test
    public void loaderReturnsNonZeroNonNullForValidKey() {
        Integer events = loader.getSetting("number_of_events");
        assertNotNull(events);
        assertTrue(events > 0);
    }

    @Test
    public void loaderReturnsErrorCodeForUnknownKey() {
        Integer minusOne = loader.getSetting("asdf");
        assertNotNull(minusOne);
        assertTrue(minusOne == -1);
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
