package mtd.logic.score;

import java.util.List;
import mtd.data.models.Question;
import mtd.data.models.answer.CorrectAnswer;
import mtd.data.models.answer.RandomAnswer;

/**
 *
 * @author sjack
 */
public class ScoreCalculator {

    public ScoreCalculator() {
    }

    public final void assignScoreValuesToEachAnswer(Question q) {
        calculateAndAssignScores(q);
    }

    /**
     * Returns the score given for a random answer.
     *
     * @param correct the correct answer
     * @param rand the randomly generated answer
     * @return the calculated score
     */
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

    private void calculateAndAssignScores(Question q) {
        List<RandomAnswer> randomList = q.getRandomAnswers();
        for (RandomAnswer randomAnswer : randomList) {
            int score = calculateScoreForRandomAnswer(q.getCorrectAnswer(), randomAnswer);
            assignScoreForRandomAnswer(score, randomAnswer);
        }
    }
}
