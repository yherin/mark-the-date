/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mtd.logic.score;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import mtd.data.create.QuestionCreator;
import mtd.data.create.RandomAnswerCreator;
import mtd.data.load.SettingsLoader;
import mtd.data.models.Event;
import mtd.data.models.Question;
import mtd.data.models.answer.Answer;
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
public class ScoreAssignerTest {

    private List<Question> questions;
    private Random random = new Random();
    private int test_length = new SettingsLoader().getSetting("number_of_events");

    public ScoreAssignerTest() {
    }

    @Before
    public void setUp() {
        List<Event> events = new ArrayList<>();
        for (int i = 0; i < test_length; i++) {
            events.add(new Event(random.nextInt(999) + 1, "desc"));
        }
        QuestionCreator qc = new QuestionCreator(events, new RandomAnswerCreator());

        questions = qc.getListOfQuestions();

    }

    @Test
    public void everyAnswerHasValidScore() {
        ScoreAssigner.assignScoresToQuestionList(questions);
        for (Question q : questions) {
            for (Answer a : q.getShuffled()) {
                Integer score = a.getScore();
                assertNotNull("score was null for answer: " + a, score);
                assertTrue("score was negative for answer: " + a + "- score: " + score, score >= 0);
            }
        }
    }
}
