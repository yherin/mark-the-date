/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mtd.controller.logic;

import java.util.List;
import javax.swing.JButton;
import javax.swing.JTextArea;
import mtd.model.command.QuizMaster;
import mtd.model.models.Question;
import mtd.model.models.Quiz;
import mtd.view.AnswerButton;
import mtd.view.GUICommand;
import mtd.view.GUIComponent;
import mtd.view.GUIComponentMap;
import mtd.view.GameWindow;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author sjack
 */
public class UserInputControllerTest {

    UserInputController controller;
    QuizMaster model;
    GameWindow view;

    public UserInputControllerTest() {
        model = new QuizMaster();
        view = new GameWindow(model);
    }

    @Before
    public void setUp() {
        controller = new UserInputController(view, model);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void newQuizAfterPlayAgainListenerPressed() {
        JButton playAgain = (JButton) GUIComponentMap.getComponentByEnum(GUIComponent.BUTTON_PLAY_AGAIN);
        Quiz firstQuiz = model.getQuiz();
        Quiz sameQuiz = model.getQuiz();
        assertTrue(firstQuiz.equals(sameQuiz));
        playAgain.doClick();
        Quiz secondQuiz = model.getQuiz();
        assertFalse(firstQuiz.equals(secondQuiz));
    }

    @Test
    public void scoreResetAfterPlayAgainListenerPressed() {
        JButton playAgain = (JButton) GUIComponentMap.getComponentByEnum(GUIComponent.BUTTON_PLAY_AGAIN);
        //generate score
        assertTrue(model.getScore() == 0);
        model.addPointsToScore(200);
        playAgain.doClick();
        assertTrue(model.getScore() + " != " + 0, model.getScore() == 0);
    }

    @Test
    public void scoreAddedCorrectlyAfterAnswerButtonPress() {
        AnswerButton answer1 = (AnswerButton) GUIComponentMap.getComponentByEnum(GUIComponent.BUTTON_ANSWER_1);
        int scoreToAdd = answer1.getAnswer().getScore();
        int currentScore = model.getScore();
        answer1.doClick();
        int newScore = model.getScore();
        assertEquals(newScore, scoreToAdd + currentScore);
    }

    @Test
    public void nonFinalQuestionUpdatedAfterAnswerButtonPress() {
        AnswerButton answer1 = (AnswerButton) GUIComponentMap.getComponentByEnum(GUIComponent.BUTTON_ANSWER_1);
        JTextArea questionText = (JTextArea) GUIComponentMap.getComponentByEnum(GUIComponent.LABEL_QUESTION_TEXT);
        Question oldQuestion = model.getSpecifiedQuestion(GUICommand.CURRENT_QUESTION);

        assertTrue(questionText.getText().equals(oldQuestion.getEvent().getDescription()));

        int index = model.getIndex(); //
        assert (index < model.getNumberOfQuestions() - 1); //must be at least 1 more question

        answer1.doClick();

        Question newQuestion = model.getSpecifiedQuestion(GUICommand.CURRENT_QUESTION);

        assertFalse(oldQuestion.equals(newQuestion));

        questionText = (JTextArea) GUIComponentMap.getComponentByEnum(GUIComponent.LABEL_QUESTION_TEXT);

        assertTrue(questionText.getText().equals(newQuestion.getEvent().getDescription()));

    }
}

// TODO add test methods here.
// The methods must be annotated with annotation @Test. For example:
//
// @Test
// public void hello() {}

