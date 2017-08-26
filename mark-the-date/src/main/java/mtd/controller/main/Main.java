package mtd.controller.main;

import mtd.controller.logic.GamePlayer;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author Jack Sheridan
 */
public class Main {
    public static void main(String[] args) throws FileNotFoundException, IOException {

        GamePlayer gp = new GamePlayer();
        int threads = gp.play();
        if (threads != 2) {
            System.err.println("No gui thread detected.");
            System.exit(-1);
        }
    }
}
