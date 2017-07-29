/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mtd.data.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import mtd.data.models.answer.Answer;
import mtd.data.models.answer.CorrectAnswer;
import mtd.data.models.answer.RandomAnswer;

/**
 *
 * @author sjack
 */
public class Question {

    private final Event event;
    private final CorrectAnswer correctAnswer;
    private final List<RandomAnswer> randomAnswers;

    public Question(Event event, CorrectAnswer correctAnswer, List<RandomAnswer> randomAnswers) {
        this.event = event;
        this.correctAnswer = correctAnswer;
        this.randomAnswers = randomAnswers;
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
        List<Answer> answers = createShuffledAnswers();
        sb.append("\n");
        sb.append(answers.get(0).getValue());
        sb.append("\t");
        sb.append(answers.get(1).getValue());
        sb.append("\t");
        sb.append(answers.get(2).getValue());
        sb.append("\t");
        sb.append(answers.get(3).getValue());
        return sb.toString();
    }

    private List<Answer> createShuffledAnswers() {
        List<Answer> answers = new ArrayList<Answer>();
        answers.addAll(this.randomAnswers);
        answers.add(correctAnswer);
        Collections.shuffle(answers);
        return answers;
    }

}
