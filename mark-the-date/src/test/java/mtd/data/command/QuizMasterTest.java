/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mtd.data.command;

import mtd.model.command.QuizMaster;
import mtd.model.models.Question;
import mtd.view.GUICommand;
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
public class QuizMasterTest {

    QuizMaster qm;

    public QuizMasterTest() {
        this.qm = new QuizMaster();
    }

    @Test
    public void currentQuestionReturnsSameQuestion() {
        Question q1 = qm.getSpecifiedQuestion(GUICommand.CURRENT_QUESTION);
        Question q2 = qm.getSpecifiedQuestion(GUICommand.CURRENT_QUESTION);
        assertNotNull(q1);
        assertNotNull(q2);

        assertEquals(q1, q2);
    }

    @Test
    public void nextQuestionReturnsDifferentQuestion() {
        Question q3 = qm.getSpecifiedQuestion(GUICommand.CURRENT_QUESTION);
        Question q4 = qm.getSpecifiedQuestion(GUICommand.NEXT_QUESTION);
        assertNotNull(q3);
        assertNotNull(q4);

        assertNotEquals(q3, q4);
    }

    @Test
    public void listOfQuestionsNotEmptyNorNull() {
        Integer q = qm.getNumberOfQuestions();
        assertNotNull(q);
        assertTrue(q > 0);
    }

    @Test
    public void basicScoreTest() {
        int initial = qm.getScore();
        int points = 20;
        qm.addPointsToScore(points);
        assertEquals(initial + points, qm.getScore());
    }

}
