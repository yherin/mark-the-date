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
     * to 'value'.
     */
    public RandomAnswer() {
        RNG = new Random();
        this.value = generateRandomAnswer();

    }

    private int generateRandomAnswer() {
        return RNG.nextInt(1000);
    }

}
