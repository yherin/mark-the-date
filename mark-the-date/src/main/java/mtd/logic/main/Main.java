/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mtd.logic.main;

import mtd.data.models.Event;
import mtd.gui.MyFrame;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import javax.swing.SwingUtilities;
import mtd.data.create.EventCreator;
import mtd.data.create.QuestionCreator;
import mtd.data.create.RandomAnswerCreator;
import mtd.data.models.Question;

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

        int numberOfEvents = 10;
        SwingUtilities.invokeLater(new MyFrame());
        EventCreator ec = new EventCreator(numberOfEvents);
        List<Event> list = ec.createEvents();

        QuestionCreator qc = new QuestionCreator(list, new RandomAnswerCreator());
        List<Question> q = qc.getListOfQuestions(numberOfEvents);

        for (Question x : q) {
            System.out.println(x);
        }
    }

}
