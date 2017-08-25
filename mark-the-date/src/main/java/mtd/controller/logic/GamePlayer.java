/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mtd.controller.logic;

import javax.swing.SwingUtilities;
import mtd.model.command.QuizMaster;
import mtd.model.models.Question;
import mtd.view.GUICommand;
import mtd.view.GameWindow;

/**
 * @author Jack Sheridan
 */
public class GamePlayer {

    private final QuizMaster quizMaster;
    private final GameWindow gui;
    private final UserInputController listener;
    private Question currentQuestion;
    private final int numberOfQuestions;

    public GamePlayer() {
        quizMaster = new QuizMaster();
        numberOfQuestions = quizMaster.getNumberOfQuestions();
        updateCurrentQuestion();
        gui = new GameWindow(quizMaster);
        this.listener = new UserInputController(gui, quizMaster);
    }

    private void updateCurrentQuestion() {
        this.currentQuestion = this.quizMaster.getSpecifiedQuestion(GUICommand.CURRENT_QUESTION);
    }

    public final int play() {
        SwingUtilities.invokeLater(gui);
        return Thread.activeCount();

    }
}
