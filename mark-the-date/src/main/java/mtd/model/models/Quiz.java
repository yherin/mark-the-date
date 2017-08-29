/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mtd.model.models;

import java.util.List;

/**
 * Quiz encapsulates all data needed to play one round of questions.
 * @author Jack Sheridan
 */
public class Quiz {

    /**
     * The list of questions for this round of the game.
     */
    private final List<Question> questions;


    /**
     * Create a new quiz object.
     * @param  List<Question> questions    list of questions that can be asked.
     */
    public Quiz(List<Question> questions) {
        this.questions = questions;
    }

    public List<Question> getQuestions() {
        return questions;
    }

}
