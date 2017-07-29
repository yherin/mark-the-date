/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mtd.data.command;

import mtd.data.models.answer.RandomAnswer;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author sjack
 */
public class AnswerEvaluator {

    public static List<RandomAnswer> evalAnswers(List<RandomAnswer> answers) {
        //closest answer to correct is at index 0
        Collections.sort(answers);
        return answers;
    }

}
