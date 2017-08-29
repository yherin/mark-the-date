
package mtd.model.models.answer;

import java.util.Random;

/**
 * RandomAnswer represents a randomly generated answer corresponding to a
 * specific event.
 * @author Jack Sheridan
 */
public class RandomAnswer extends Answer {

    /**
     * Used to generate random answers. Only public method returns a random
     * integer between 1 and 1000.
     *
     * @see Random
     */
    private final Random randomNumberGenerator;

    /**
     * Create a new random answer.
     * Constructor generates a random number between 1 and 1000 and assigns it
     * to 'value' (inherited from super).
     */
    public RandomAnswer() {
        randomNumberGenerator = new Random();
        this.year = generateRandomAnswer();

    }

    private int generateRandomAnswer() {
        return randomNumberGenerator.nextInt(1000);
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public Integer getScore() {
        return this.score;
    }

}
