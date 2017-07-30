/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mtd.data.create;

import mtd.data.models.answer.CorrectAnswer;
import mtd.data.models.answer.RandomAnswer;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sjack
 */
public class RandomAnswerCreator {

    /**
     * The number of random answers that should be created.
     */
    final int NORA = 3; //number of random answers

    public RandomAnswerCreator() {
    }

    public final List<RandomAnswer> generateRandomAnswers(CorrectAnswer correct) {
        List<RandomAnswer> generatedAnswers = new ArrayList<>();
        while (generatedAnswers.size() < NORA) {
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
