/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mtd.model.score;

import java.util.List;
import mtd.model.models.Question;

/**
 *
 * @author Jack Sheridan
 */
public class ScoreAssigner {

    private ScoreAssigner() {
        //private constructor for utility class.
    }

    public static void assignScoresToQuestionList(List<Question> ql) {
        ScoreCalculator sc = new ScoreCalculator();
        for (Question question : ql) {
            sc.assignScoreValuesToEachAnswer(question);
        }

    }
}
