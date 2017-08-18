/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mtd.controller.main;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author sjack
 */
public class Main {

    /*
    Current state of the main method:
    -Load questions from JSON.
    -Randomly generate answers from them.
    -Print those answers.
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {

        GamePlayer gp = new GamePlayer();
        int threads = gp.play();
        if (threads != 2) {
            System.exit(-1);
        } else {
            System.out.println("Mark the date GUI loaded successfully.");
        }
    }

}
