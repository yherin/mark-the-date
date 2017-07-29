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
import java.util.Objects;

/**
 *
 * @author sjack
 */
public class RandomAnswerCreator {

    public RandomAnswerCreator() {

    }

    public List<RandomAnswer> generateRandomAnswers(CorrectAnswer realAnswer) {
        List<RandomAnswer> generatedAnswers = new ArrayList<>();
        while (generatedAnswers.size() < 3) {
            RandomAnswer possibleAnswer = new RandomAnswer(realAnswer.getValue());
            boolean possibleAnswerIsValid = answersAreUnique(generatedAnswers, realAnswer, possibleAnswer);
            if (possibleAnswerIsValid) {
                generatedAnswers.add(possibleAnswer);
            }
        }
        return generatedAnswers;
    }

    private boolean answersAreUnique(List<RandomAnswer> randoms, CorrectAnswer real, RandomAnswer possibleAnswer) {
        //return true if and only if possibleAnswer is not in the list and is not equal to real answer
        return (!randoms.contains(possibleAnswer) && !Objects.equals(possibleAnswer.getValue(), real.getValue()));
    }

}
