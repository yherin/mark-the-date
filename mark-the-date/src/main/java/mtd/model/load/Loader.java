package mtd.model.load;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import mtd.view.error.ErrorInformer;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Abstract class for loading data from JSON file. Inherited by EventLoader and
 * SettingsLoader.
 *
 * @author Jack Sheridan
 */
public abstract class Loader {

    private String filePath;
    private String fileName;

    /**
     * Creates a new subclass of Loader. Prepares the Loader for loading data.
     */
    protected Loader() {
        assignFileName();
        assignFilePath();
    }

    /**
     * Gets a JSONObject representation of the .json file defined in the
     * specific implementation of this abstract class.
     *
     * @return the parsed JSONObject
     * @throws JSONException JSONException thrown if json file is not valid.
     * @see JSONObject
     */
    public JSONObject getJSONRoot() {

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
        if (is == null) {
            handleLoadingFailure(new IOException(fileName));
        }
        InputStreamReader inputReader = new InputStreamReader(is);

        bf = new BufferedReader(inputReader);
        return bf;
    }

    protected final JSONObject readJSONObject() {

        try (BufferedReader br = getBufferedReader()) {
            String jsonAsString = read(br);
            JSONObject jso = new JSONObject(jsonAsString);
            if (jso.length() < 1) { //INVALID JSON
                handleLoadingFailure(new JSONException(""));
            }
            return jso;
        } catch (NullPointerException | IOException | JSONException e) {
            handleLoadingFailure(e);
        }
        return new JSONObject(); //not possible to reach here.
    }

    protected final String read(BufferedReader br) {
        StringBuilder sb = new StringBuilder();
        try {
            String line = br.readLine();
            while (line != null) {
                sb.append(line);
                line = br.readLine();
            }
        } catch (IOException e) {
            handleLoadingFailure(e);
        } finally {
            try {
                br.close();
            } catch (IOException e) {
            }
        }
        return sb.toString();
    }

    protected void handleLoadingFailure(Throwable e) {
        ErrorInformer.showError(e, "Fatal error reading JSON data from internal resource "
                + fileName + ". Installation is corrupted - please reinstall.");
    }
}
