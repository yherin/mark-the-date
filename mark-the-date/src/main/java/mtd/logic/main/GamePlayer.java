/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mtd.logic.main;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import javax.swing.SwingUtilities;
import mtd.data.command.QuizMaster;
import mtd.data.create.EventCreator;
import mtd.data.create.QuestionCreator;
import mtd.data.create.RandomAnswerCreator;
import mtd.data.models.Event;
import mtd.data.models.Question;
import mtd.data.models.answer.Answer;
import mtd.gui.GUICommand;
import mtd.gui.GameWindow;
import mtd.gui.UserInputController;
import mtd.logic.score.ScoreAssigner;
import mtd.logic.score.ScoreCalculator;

/**
 *
 * @author sjack
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
