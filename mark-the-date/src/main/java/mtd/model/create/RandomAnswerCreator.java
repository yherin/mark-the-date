/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mtd.model.create;

import java.util.ArrayList;
import java.util.List;
import mtd.model.models.answer.CorrectAnswer;
import mtd.model.models.answer.RandomAnswer;

/**
 * Creates RandomAnswer objects corresponding to a single CorrectAnswer.
 *
 * @see RandomAnswer
 * @author Jack Sheridan
 */
public class RandomAnswerCreator {

    /**
     * The number of random answers that should be created.
     */
    final int numberOfRandomAnswers = 3; //number of random answers

    /**
     * Create a new instance of RandomAnswerCreator, which provides
     * functionality to generate random answers based on a given CorrectAnswer.
     *
     * @see RandomAnswer
     * @see CorrectAnswer
     */
    public RandomAnswerCreator() {
    }

    /**
     * Generates random answers based on a given CorrectAnswer. The number of
     * random answers created is based on numberOfRandomAnswers.
     *
     * @param correct A CorrectAnswer from which RandomAnswers are created.
     * @return A list of generated RandomAnswers.
     */
    public final List<RandomAnswer> generateRandomAnswers(CorrectAnswer correct) {
        List<RandomAnswer> generatedAnswers = new ArrayList<>();
        while (generatedAnswers.size() < numberOfRandomAnswers) {
            RandomAnswer possibleAnswer = new RandomAnswer();
            boolean possibleAnswerIsValid = answersAreUnique(
                    generatedAnswers, correct, possibleAnswer);
            if (possibleAnswerIsValid) {
                generatedAnswers.add(possibleAnswer);
            }
        }
        return generatedAnswers;
    }

    /**
     *
     * @param randoms the current list of chosen random answers
     * @param real the correct answer
     * @param possibleAnswer the answer which is checked against real and the
     * list of random answers.
     * @return True if possibleAnswer is not present in the list of currently
     * chosen random answers, and is not equal to the real answer. Else false.
     */
    private boolean answersAreUnique(final List<RandomAnswer> randoms,
            final CorrectAnswer real, final RandomAnswer possibleAnswer) {
        if (possibleAnswer != null) {
            return (!randoms.contains(possibleAnswer)
                    && (possibleAnswer.getYear() != real.getYear()));
        } else {
            return false;
        }
    }

}
