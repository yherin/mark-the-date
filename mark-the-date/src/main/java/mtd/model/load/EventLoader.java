package mtd.model.load;


/**
 * Reads JSON data from events.json.
 * @author Jack Sheridan
 * @see Loader
 */
public final class EventLoader extends Loader {

    /**
     * Creates a new instance of EventLoader.
     * @see Loader
     */
    public EventLoader() {
        super();
    }

    @Override
    String filePathString() {
        String fp = "mtd/json/";
        return fp;
    }

    @Override
    String fileNameString() {
        String fn = "events.json";
        return fn;
    }

}
