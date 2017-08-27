package mtd.model.score;

public class ScoreTracker {

    private int score;
    private int highest_score;

    public ScoreTracker() {
        this.score = 0;
        this.highest_score = 0;
    }

    public void addPointsToScore(int points) {
        this.score += points;
        evaluateHighScore();
    }

    public int getScore() {
        return this.score;
    }

    public boolean currentScoreIsHighestScore() {
        evaluateHighScore();
        return (this.score == this.highest_score);
    }

    private void evaluateHighScore() {
        if (this.score > this.highest_score) {
            this.highest_score = this.score;
        }
    }

    public void reset() {
        this.score = 0;
    }

}
