package mtd.model.load;

import java.awt.Component;
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

    /*
    This test has been removed because error popup (see ErrorInformer) could not easily be closed 
    through JUnit and interefered with the build process.
     */
//    @Test
//    public void loaderReturnsErrorCodeForUnknownKey() {
//        Integer minusOne = loader.getSetting("asdf");
//        assertNotNull(minusOne);
//        assertTrue(minusOne == -1);
//    }
}
