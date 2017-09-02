
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