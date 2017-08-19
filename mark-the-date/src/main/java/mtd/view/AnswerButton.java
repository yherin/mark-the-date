package mtd.view;

import javax.swing.JButton;
import mtd.model.models.answer.Answer;

/**
 * JButton subclass which stores the Answer with which the button is associated.
 *
 * @author Jack Sheridan
 */
public class AnswerButton extends JButton {

    private Answer answer;

    public AnswerButton(Answer a) {
        this.answer = a;
    }

    public AnswerButton() {

    }

    public Answer getAnswer() {
        if (answer != null) {
            return answer;
        } else {
            throw new IllegalStateException("Answer not initialised.");

        }
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
        this.setText(this.answer.toString());
    }

}
