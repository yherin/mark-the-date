/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mtd.model.models.answer;

/**
 *
 * @author Jack Sheridan
 */
public class CorrectAnswer extends Answer {

    private final int maxScore = 500;

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