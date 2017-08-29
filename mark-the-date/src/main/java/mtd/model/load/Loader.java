package mtd.model.load;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Abstract class for loading data from JSON file. Inherited by EventLoader and
 * SettingsLoader.
 * @author Jack Sheridan
 */
public abstract class Loader {

    private String filePath;
    private String fileName;

    public Loader() {
        assignFileName();
        assignFilePath();
    }

    /**
     * Gets a JSONObject representation of the .json file defined in the
     * specific implementation of this abstract class.
     *
     * @return the read JSONObject
     * @throws JSONException
     * @see JSONObject
     */
    public JSONObject getJSONRoot() throws JSONException {

        return readJSONObject();
    }

    protected final void assignFilePath() {
        this.filePath = filePathString();
    }

    protected final void assignFileName() {
        this.fileName = fileNameString();
    }

    abstract String filePathString();

    abstract String fileNameString();

    protected final InputStream getInputStream() {
        InputStream is = EventLoader.class.getClassLoader().getResourceAsStream(
                filePath + fileName);
        return is;
    }

    protected final BufferedReader getBufferedReader() {
        BufferedReader bf;
        InputStream is = getInputStream();
        assert (is != null);
        InputStreamReader inputReader = new InputStreamReader(is);

        bf = new BufferedReader(inputReader);
        return bf;
    }

    protected final JSONObject readJSONObject() throws JSONException {

        try (BufferedReader br = getBufferedReader()) {
            String jsonAsString = read(br);
            JSONObject jso = new JSONObject(jsonAsString);
            return jso;
        } catch (IOException e) {
            System.err.println("Fatal error when parsing JSON file. ");
            System.exit(-1);
        }
        throw new IllegalStateException("Fatal error when parsing JSON file");

    }

    protected final String read(BufferedReader br) throws IOException {
        StringBuilder sb = new StringBuilder();
        try {
            String line = br.readLine();
            while (line != null) {
                sb.append(line);
                line = br.readLine();
            }
        } catch (IOException e) {
            System.err.println("Fatal error when parsing events JSON.  ");
            System.exit(-1);
        } finally {
            try {
                br.close();
            } catch (IOException e) {
            }
        }
        return sb.toString();
    }
}
