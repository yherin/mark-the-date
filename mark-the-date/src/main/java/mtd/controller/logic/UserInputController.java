package mtd.controller.logic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JTextArea;
import mtd.model.command.QuizMaster;
import mtd.model.models.Question;
import mtd.view.AnswerButton;
import mtd.view.GUICommand;
import mtd.view.GUIComponent;
import mtd.view.GUIComponentMap;
import mtd.view.GameWindow;

/**
 * UserInputController performs functions of the controller in the MVC modelwa. It
 * controls the GUI View and asks for data from Model (GameWindow and
 * QuizMaster).
 *
 * @author Jack Sheridan
 *
 */
public class UserInputController {

    private GameWindow gui;
    private QuizMaster model;

    public UserInputController(GameWindow gui, QuizMaster model) {
        this.gui = gui;
        this.model = model;
        addListenerButtonsToGUI();
    }

    class AnswerButtonListener implements ActionListener {

        /**
         * When AnswerButton is clicked: timer is stopped, score for the
         * question is added to the total score, timer is reset. Then QuizMaster
         * (Model) is asked to provide the next question.
         *
         * @param ActionEvent ae caused by clicking AnswerButton.
         */
        @Override
        public void actionPerformed(ActionEvent ae) {
            AnswerButton clicked = (AnswerButton) ae.getSource();
            clicked.setEnabled(false);
            gui.getTimer().stop();
            model.addPointsToScore(clicked.getAnswer().getScore());
            nextQuestion();
            gui.getTimer().reset();
            clicked.setEnabled(true);
        }

    }

    class PlayAgainButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            createNewQuiz();
            model.resetScore();
            Question q = model.getSpecifiedQuestion(GUICommand.CURRENT_QUESTION);
            gui.updateGUIToShowNextQuestion(q);
        }

        private void createNewQuiz() {
            model.buildNewQuiz();
        }

    }

    private void addListenerButtonsToGUI() {
        List<AnswerButtonListener> listeners = createListeners();
        for (int i = 0; i < listeners.size(); i++) {
            this.gui.addListenerToAnswerButton(listeners.get(i), i);
        }
        JButton playAgain = (JButton) GUIComponentMap.getComponentByEnum(GUIComponent.BUTTON_PLAY_AGAIN);
        playAgain.addActionListener(new PlayAgainButtonListener());
    }

    private List<AnswerButtonListener> createListeners() {
        List<AnswerButtonListener> aBLL = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            aBLL.add(new AnswerButtonListener());
        }
        return aBLL;
    }

    private void nextQuestion() {
        Question current = this.model.getSpecifiedQuestion(GUICommand.CURRENT_QUESTION);
        Question next = this.model.getSpecifiedQuestion(GUICommand.NEXT_QUESTION);
        if (!current.equals(next)) {
            this.gui.updateGUIToShowNextQuestion(next);
        } else {
            JTextArea score = (JTextArea) GUIComponentMap.getComponentByEnum(GUIComponent.LABEL_SCORE_INT);
            score.setText(String.valueOf(this.model.getScore()));
            this.gui.addSummaryComponents();
        }

    }

}
