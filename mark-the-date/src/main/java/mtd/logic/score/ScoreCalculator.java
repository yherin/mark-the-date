package mtd.logic.score;

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

    public boolean assignScoreValuesToEachAnswer(Question q) {

    }

    /**
     * Returns the score given for a random answer.
     *
     * @param correct the correct answer
     * @param ran the randomly generated answer
     * @return the calculated score
     */
    private int calculateScoreForRandomAnswer(final CorrectAnswer correct,
            final RandomAnswer ran) {
        return Math.abs(MAXPOINTS - correct.getValue() - ran.getValue());
    }
    
    private boolean assignScoreForRandomAnswer(final int score,
            final RandomAnswer ran){
        ran.
    }
}
