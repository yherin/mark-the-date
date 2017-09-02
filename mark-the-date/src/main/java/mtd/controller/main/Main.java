package mtd.controller.main;

import mtd.controller.logic.GamePlayer;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Main class, which offers main method.
 *
 * @author Jack Sheridan
 */
public class Main {

    /**
     * Start the application.
     *
     * @param args UNSUPPORTED command line arguments
     * @throws FileNotFoundException Thrown when events file not found in
     * src/resources
     * @throws IOException Thrown when file in src/resources cannot be read.
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {

        GamePlayer gp = new GamePlayer();
        int threads = gp.play();
        if (threads != 2) {
            System.err.println("No gui thread detected.");
            System.exit(-1);
        }
    }
}
