/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mtd.model.models.answer;

/**
 * CorrectAnswer represents the correct answer to a specific event.
 * @author Jack Sheridan
 */
public class CorrectAnswer extends Answer {

    private final int maxScore = 500;
    /**
     * Create a new instance of CorrectAnswer
     * @param  Integer value         The value representing the year of the
     * correct answer.
     */
    public CorrectAnswer(Integer value) {
        this.year = value;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public final int getMaxScore() {
        return this.maxScore;
    }

    @Override
    public final Integer getScore() {
        return getMaxScore();
    }
}
