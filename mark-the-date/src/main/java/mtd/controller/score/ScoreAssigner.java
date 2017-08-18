/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mtd.controller.score;

import java.util.List;
import mtd.model.models.Question;

/**
 *
 * @author sjack
 */
public class ScoreAssigner {

    public static void assignScoresToQuestionList(List<Question> ql) {
        ScoreCalculator sc = new ScoreCalculator();
        for (Question question : ql) {
            sc.assignScoreValuesToEachAnswer(question);
        }

    }
}
