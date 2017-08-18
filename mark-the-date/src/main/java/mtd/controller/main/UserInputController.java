/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mtd.controller.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Timer;
import mtd.model.command.QuizMaster;
import mtd.model.models.Question;
import mtd.view.AnswerButton;
import mtd.view.GUICommand;
import mtd.view.GameWindow;

/**
 * UserInputController performs functions of the controller in the MVC model. It
 * controls move the GUI and data (GameWindow and QuizMaster).
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

        @Override
        public void actionPerformed(ActionEvent ae) {
            AnswerButton clicked = (AnswerButton) ae.getSource();
            gui.getTimer().stop();
            model.addPointsToScore(clicked.getAnswer().getScore());
            System.out.println(model.getScore());
            gui.getTimer().reset();
            nextQuestion();
        }

    }

    private void addListenerButtonsToGUI() {
        List<AnswerButtonListener> listeners = createListeners();
        for (int i = 0; i < listeners.size(); i++) {
            this.gui.addListenerToAnswerButton(listeners.get(i), i);
        }
    }

    private List<AnswerButtonListener> createListeners() {
        List<AnswerButtonListener> aBLL = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            aBLL.add(new AnswerButtonListener());
        }
        return aBLL;
    }

    private void nextQuestion() {
        Question next = this.model.getSpecifiedQuestion(GUICommand.NEXT_QUESTION);
        this.gui.update(next);

    }

}
