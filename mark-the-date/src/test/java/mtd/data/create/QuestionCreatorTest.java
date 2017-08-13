/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mtd.data.create;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import mtd.data.create.QuestionCreator;
import mtd.data.create.RandomAnswerCreator;
import mtd.data.load.SettingsLoader;
import mtd.data.models.Event;
import mtd.data.models.Question;
import mtd.data.models.answer.CorrectAnswer;
import mtd.data.models.answer.RandomAnswer;
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
public class QuestionCreatorTest {

    private final List<Event> events;
    private final RandomAnswerCreator rac;
    private List<Question> ql;
    private final int test_length = SettingsLoader.getSetting("number_of_events");

    public QuestionCreatorTest() {
        this.rac = new RandomAnswerCreator();
        this.events = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < test_length; i++) {
            events.add(new Event(random.nextInt(999) + 1, "desc"));
        }

    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        ql = new QuestionCreator(this.events, this.rac).getListOfQuestions();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void questionCreatorListNotEmptyNorNull() {
        assertNotNull("list of questions was null", ql);
        assertTrue("list of questions was incorrect length", ql.size() >= 1);
    }

    @Test
    public void createdQuestionsHaveThreeRandomAnswers() {
        Question q = ql.get(0);
        assertTrue("question did not have 3 random answers", q.getRandomAnswers().size() == 3);
    }

    @Test
    public void questionHasCorrectAnswer() {
        Question q = ql.get(0);
        assertNotNull("correct answer was null", q.getCorrectAnswer());
        assertEquals("correct answer was not of class CorrectAnswer", CorrectAnswer.class, q.getCorrectAnswer().getClass());
    }

}
