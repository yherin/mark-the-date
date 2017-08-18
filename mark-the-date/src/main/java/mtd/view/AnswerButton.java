/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mtd.view;

import javax.swing.JButton;
import mtd.model.models.answer.Answer;

/**
 * JButton subclass which stores the Answer with which the button is associated.
 *
 * @author sjack
 */
public class AnswerButton extends JButton {

    private Answer answer;

    public AnswerButton(Answer a) {
        this.answer = a;
    }

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

}
