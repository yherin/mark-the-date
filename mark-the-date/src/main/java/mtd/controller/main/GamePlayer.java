/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mtd.controller.main;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import javax.swing.SwingUtilities;
import mtd.model.command.QuizMaster;
import mtd.model.create.EventCreator;
import mtd.model.create.QuestionCreator;
import mtd.model.create.RandomAnswerCreator;
import mtd.model.models.Event;
import mtd.model.models.Question;
import mtd.model.models.answer.Answer;
import mtd.view.GUICommand;
import mtd.view.GameWindow;
import mtd.controller.score.ScoreAssigner;
import mtd.controller.score.ScoreCalculator;

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
