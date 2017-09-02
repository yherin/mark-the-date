package mtd.model.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import mtd.model.models.answer.Answer;
import mtd.model.models.answer.CorrectAnswer;
import mtd.model.models.answer.RandomAnswer;

/**
 *
 * @author Jack Sheridan
 */
/**
 * Representation of a game question, containing the Event, one CorrectAnswer,
 * List<RandomAnswer> of length 3, and a List<Answer> which contains
 * CorrectAnswer and all RandomAnswers.
 */
public class Question {

    private final Event event;
    private final CorrectAnswer correctAnswer;
    private final List<RandomAnswer> randomAnswers;
    private List<Answer> shuffled;

    /**
     * Create a new Question object.
     *
     * @param event the event to which the question is related
     * @param correctAnswer the correct answer based on the event.
     * @param randomAnswers random answers generated from correctAnswer.
     */
    public Question(Event event, CorrectAnswer correctAnswer, List<RandomAnswer> randomAnswers) {
        this.event = event;
        this.correctAnswer = correctAnswer;
        this.randomAnswers = randomAnswers;
        shuffled = createShuffledAnswers();

    }

    public final Event getEvent() {
        return event;
    }

    public final CorrectAnswer getCorrectAnswer() {
        return correctAnswer;
    }

    public final List<RandomAnswer> getRandomAnswers() {
        return randomAnswers;
    }

    private List<Answer> createShuffledAnswers() {
        List<Answer> answers = new ArrayList<Answer>();
        answers.addAll(this.randomAnswers);
        answers.add(correctAnswer);
        Collections.shuffle(answers);
        return answers;
    }

    public List<Answer> getShuffled() {
        return shuffled;
    }

}
