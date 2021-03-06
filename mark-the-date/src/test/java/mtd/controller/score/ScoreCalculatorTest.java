/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mtd.controller.score;

import mtd.model.score.ScoreCalculator;
import java.util.List;
import mtd.model.create.RandomAnswerCreator;
import mtd.model.models.Event;
import mtd.model.models.Question;
import mtd.model.models.answer.Answer;
import mtd.model.models.answer.CorrectAnswer;
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
public class ScoreCalculatorTest {

    private ScoreCalculator sc1;
    private Question q;
    private List<Answer> ans;

    public ScoreCalculatorTest() {
        sc1 = new ScoreCalculator();

    }

    @Before
    public void setup() {
        RandomAnswerCreator rac = new RandomAnswerCreator();
        Event e = new Event(100, "desc");
        CorrectAnswer ca = new CorrectAnswer(100);
        q = new Question(e, ca, rac.generateRandomAnswers(ca));
        sc1.assignScoreValuesToEachAnswer(q);
        ans = q.getShuffled();
    }

    @Test
    public void everyAnswerHasScore() {
        for (Answer an : ans) {
            assertNotNull(an.getScore());
        }
    }

    @Test
    public void everyScoreBetween0And500() {
        for (Answer an : ans) {
            assertTrue(0 + "-" + an.getScore() + "-" + 500, an.getScore() >= 0 && an.getScore() <= 500);
        }
    }

    @Test
    public void correctAnswerHasScoreOf500() {
        assertTrue(q.getCorrectAnswer().getScore() == 500);
    }
}
