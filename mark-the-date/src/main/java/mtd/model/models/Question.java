/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
 * Representation of a quiz question, containing the Event, one CorrectAnswer,
 * List<RandomAnswer> of length 3, and a List<Answer> which contains
 * CorrectAnswer and all RandomAnswer.
 */
public class Question {

    private final Event event;
    private final CorrectAnswer correctAnswer;
    private final List<RandomAnswer> randomAnswers;
    private List<Answer> shuffled;

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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.event.getDescription());
        sb.append("\n");
        sb.append(shuffled.get(0).getYear());
        sb.append("\t");
        sb.append(shuffled.get(1).getYear());
        sb.append("\t");
        sb.append(shuffled.get(2).getYear());
        sb.append("\t");
        sb.append(shuffled.get(3).getYear());
        return sb.toString();
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
