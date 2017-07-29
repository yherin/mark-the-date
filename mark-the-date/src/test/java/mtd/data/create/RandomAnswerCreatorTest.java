/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mtd.data.create;

import mtd.data.models.Event;
import mtd.data.models.answer.CorrectAnswer;
import mtd.data.models.answer.RandomAnswer;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import mtd.data.create.RandomAnswerCreator;
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
public class RandomAnswerCreatorTest {

    private CorrectAnswer correct1;
    private CorrectAnswer correct2;
    private RandomAnswerCreator rac;

    public RandomAnswerCreatorTest() {
    }

    @Before
    public void setUp() {
        correct1 = new CorrectAnswer(510);
        correct2 = new CorrectAnswer(511);
        rac = new RandomAnswerCreator();
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void randomAnswerCreatorReturnsUniqueAnswers() {
        List<RandomAnswer> answers = rac.generateRandomAnswers(correct1);
        assertFalse(answers.isEmpty());
        for (int i = 0; i < answers.size(); i++) {
            RandomAnswer ra = answers.get(i);
            assertNotNull(ra.getValue());
            assertNotNull(correct1.getValue());
            assertFalse("two answers were equal:\n" + correct1.getValue() + " == " + ra.getValue(),
                    correct1.getValue() == ra.getValue());
            answers.remove(i);
            assertFalse("answer was removed but still contained in the list", answers.contains(ra));
        }

    }

    @Test
    public void randomAnswerCreatorCreatesOnlyThreeAnswers() {
        List<RandomAnswer> answers = rac.generateRandomAnswers(correct1);
        assertTrue(answers.size() + " random answers were created", answers.size() == 3);
    }
}
