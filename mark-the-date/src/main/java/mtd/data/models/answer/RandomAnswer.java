/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mtd.data.models.answer;

import java.util.Random;

/**
 *
 * @author sjack
 */
public class RandomAnswer extends Answer {

    /**
     * Used to generate random answers. Only public method returns a random
     * integer between 1 and 1000.
     *
     * @see Random
     */
    private final Random RNG;

    /**
     * Constructor generates a random number between 1 and 1000 and assigns it
     * to 'value' (inherited from super).
     */
    public RandomAnswer() {
        RNG = new Random();
        this.year = generateRandomAnswer();

    }

    private int generateRandomAnswer() {
        return RNG.nextInt(1000);
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public Integer getScore() {
        return this.score;
    }

}
