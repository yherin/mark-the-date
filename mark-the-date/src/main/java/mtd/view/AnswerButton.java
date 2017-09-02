package mtd.view;

import javax.swing.JButton;
import mtd.model.models.answer.Answer;
import mtd.view.error.ErrorInformer;

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
        if (this.answer == null) {
            showError();
        }
        return this.answer;
    }

    private void showError() {
        ErrorInformer.showError(new IllegalStateException(),
                "Fatal error creating game data. Installation is corrupted- please reinstall.");
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
        this.setText(this.answer.toString());
    }

    @Override
    public String toString() {
        return "AnswerButton for answer " + this.answer.toString();
    }

}
