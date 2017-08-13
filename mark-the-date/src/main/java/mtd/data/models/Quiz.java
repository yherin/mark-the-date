/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mtd.data.models;

import java.util.List;

/**
 *
 * @author sjack
 */
public class Quiz {
    
    private final List<Question> questions;
    
    public Quiz(List<Question> questions){
        this.questions = questions;
    }

    public List<Question> getQuestions() {
        return questions;
    }
    
    
}
