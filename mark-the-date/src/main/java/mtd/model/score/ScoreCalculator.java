package mtd.model.score;

import java.util.List;
import mtd.model.models.Question;
import mtd.model.models.answer.Answer;
import mtd.model.models.answer.CorrectAnswer;
import mtd.model.models.answer.RandomAnswer;

/**
 * Provides functionality for calculating scores for both RandomAnswer and
 * CorrectAnswer.
 *
 * @author Jack Sheridan
 */
public class ScoreCalculator {

    /**
     * For a given question, calculates scores for its Answer objects.
     *
     * @param q Question to which scores are assigned.
     * @see Answer
     */
    public final void assignScoreValuesToEachAnswer(Question q) {
        calculateAndAssignScores(q);
    }

    private void calculateAndAssignScores(Question q) {
        List<RandomAnswer> randomList = q.getRandomAnswers();
        for (RandomAnswer randomAnswer : randomList) {
            int score = calculateScoreForRandomAnswer(q.getCorrectAnswer(), randomAnswer);
            assignScoreForRandomAnswer(score, randomAnswer);
        }
    }

    private int calculateScoreForRandomAnswer(final CorrectAnswer correct,
            final RandomAnswer rand) {
        int score = correct.getMaxScore() - Math.abs(correct.getYear() - rand.getYear());
        if (score > 0) {
            return score;
        } else {
            return 0;
        }
    }

    private void assignScoreForRandomAnswer(final int score,
            final RandomAnswer ran) {
        ran.setScore(score);
    }

}
