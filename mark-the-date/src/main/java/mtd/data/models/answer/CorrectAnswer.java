/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mtd.data.models.answer;

/**
 *
 * @author sjack
 */
public class CorrectAnswer extends Answer {

    private final int MAX_SCORE = 500;

    public CorrectAnswer(Integer value) {
        this.year = value;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public final int getMaxScore() {
        return this.MAX_SCORE;
    }

    @Override
    public final Integer getScore() {
        return getMaxScore();
    }
}
