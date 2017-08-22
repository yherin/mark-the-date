/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mtd.view.create;

import java.awt.Container;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import mtd.model.models.QuestionStopwatch;
import mtd.model.models.answer.Answer;
import mtd.view.AnswerButton;
import mtd.view.GUIComponent;
import mtd.view.GUIComponentMap;

/**
 *
 * @author sjack
 */
public class QuestionComponentCreator extends ComponentCreator {

    private List<AnswerButton> answerButtons;
    private JTextArea questionText;
    private QuestionStopwatch timer;

    public QuestionComponentCreator() {
        
    }

    @Override
    void createComponents() {
        createAnswerButtons();
        createQuestionText();
        createTimer();
    }

    @Override
    void mapComponents() {
        //Buttons
        GUIComponentMap.setComponentToEnum(GUIComponent.BUTTON_ANSWER_1, answerButtons.get(0));
        GUIComponentMap.setComponentToEnum(GUIComponent.BUTTON_ANSWER_2, answerButtons.get(1));
        GUIComponentMap.setComponentToEnum(GUIComponent.BUTTON_ANSWER_3, answerButtons.get(2));
        GUIComponentMap.setComponentToEnum(GUIComponent.BUTTON_ANSWER_4, answerButtons.get(3));
        //Text
        GUIComponentMap.setComponentToEnum(GUIComponent.LABEL_QUESTION_TEXT, questionText);
        //Timer
        GUIComponentMap.setComponentToEnum(GUIComponent.TIMER, timer);
    }

    private void createQuestionText() {
        JTextArea q = new JTextArea("question not loaded.",10,15);
        q.setEditable(false);
        q.setWrapStyleWord(true);
        q.setLineWrap(true);
        q.setFont(new Font("sansserif", 0, 20));
        questionText = q;
    }

    private void createTimer() {
        this.timer = new QuestionStopwatch();
    }

    private void createAnswerButtons() {
        List<AnswerButton> listOfAnswerButtons = new ArrayList<>();
        listOfAnswerButtons.add(new AnswerButton());
        listOfAnswerButtons.add(new AnswerButton());
        listOfAnswerButtons.add(new AnswerButton());
        listOfAnswerButtons.add(new AnswerButton());
        answerButtons = listOfAnswerButtons;
    }

}
