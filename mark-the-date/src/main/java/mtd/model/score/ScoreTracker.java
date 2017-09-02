package mtd.model.score;

/**
 * Provides functionality to keep track of the player's score.
 *
 * @author Jack Sheridan
 */
public class ScoreTracker {

    /**
     * Player's current score.
     */
    private int score;
    /**
     * Highest score this session.
     */
    private int highestScore;

    /**
     * Creates a new ScoreTracker. Sets score values to zero.
     */
    public ScoreTracker() {
        this.score = 0;
        this.highestScore = 0;
    }

    /**
     * Add points to player's score.
     *
     * @param points the amount of points to add.
     */
    public void addPointsToScore(int points) {
        this.score += points;
        evaluateHighScore();
    }

    public int getScore() {
        return this.score;
    }

    /**
     * Checks if the current score is the highest score this session.
     *
     * @return True if this score is also the highest score.
     */
    public boolean currentScoreIsHighestScore() {
        evaluateHighScore();
        return (this.score == this.highestScore);
    }

    private void evaluateHighScore() {
        if (this.score > this.highestScore) {
            this.highestScore = this.score;
        }
    }

    /**
     * Reset the player's score to 0.
     */
    public void reset() {
        this.score = 0;
    }

}
