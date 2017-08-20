/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mtd.model.create;

import mtd.model.create.QuizCreator;
import mtd.model.create.QuizCreator;
import mtd.model.load.SettingsLoader;
import mtd.model.models.Question;
import mtd.model.models.Quiz;
import mtd.model.models.answer.Answer;
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
public class QuizCreatorTest {

    private QuizCreator qc1;
    private SettingsLoader sl;
    private int quantity;
    private Quiz quiz;

    public QuizCreatorTest() {
        qc1 = new QuizCreator();
        sl = new SettingsLoader();
        quantity = sl.getSetting("number_of_events");
        quiz = qc1.createQuiz();

    }

    @Test
    public void createdQuizHasSpecifiedNumberOfEvents() {
        assertTrue(quiz.getQuestions().size() == quantity);
    }

    @Test
    public void everyAnswerHasValidScore() {
        for (Question question : quiz.getQuestions()) {
            for (Answer answer : question.getShuffled()) {
                assertNotNull(answer.getScore());
                assertTrue(answer.getScore() >= 0);
                assertTrue(answer.getScore() <= 500);
            }
        }
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
