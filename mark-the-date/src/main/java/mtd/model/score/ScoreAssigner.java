package mtd.model.score;

import java.util.List;
import mtd.model.models.Question;

/**
 * Utility class that provides static methods to assign score values to Question
 * objects.
 *
 * @author Jack Sheridan
 */
public class ScoreAssigner {

    private ScoreAssigner() {
        //private constructor for utility class.
    }

    /**
     * Assign scores to a list of questions. Uses ScoreCalculator for logic.
     *
     * @param ql the list of Question objects.
     * @see ScoreCalculator
     */
    public static void assignScoresToQuestionList(List<Question> ql) {
        ScoreCalculator sc = new ScoreCalculator();
        for (Question question : ql) {
            sc.assignScoreValuesToEachAnswer(question);
        }

    }
}
