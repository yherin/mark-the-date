/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mtd.view;

import java.awt.Component;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import mtd.model.command.QuizMaster;
import mtd.model.models.Question;
import mtd.model.models.QuestionStopwatch;
import mtd.model.models.answer.Answer;

/**
 *
 * @author Jack Sheridan
 */
public class GameWindow implements Runnable {

    private Container container;
    private Question currentQuestion;
    private JFrame frame;
    private QuizMaster logic;
    private List<AnswerButton> answerButtons;
    private QuestionStopwatch timer;

    public GameWindow(QuizMaster logic) {
        this.logic = logic;
        this.currentQuestion = logic.getSpecifiedQuestion(GUICommand.CURRENT_QUESTION);
        frame = new JFrame("Mark the date");
        frame.setSize(500, 500);
        this.answerButtons = createAnswerButtons();
        frame.setLayout(new GridBagLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        container = frame.getContentPane();
        addComponents();
        this.showGUI();

    }

    private void addComponents() {
        addTimerLabel();
        addQuestionText();
        addAnswerButtons();
    }

    private void addTimerLabel() {
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        this.timer = new QuestionStopwatch();
        container.add(this.timer, gbc);
    }

    private void addQuestionText() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JTextArea questionText = new JTextArea(currentQuestion.getEvent().getDescription());
        questionText.setEditable(false);
        questionText.setLineWrap(false);
        questionText.setFocusable(false);
        container.add(questionText, gbc);
    }

    private void addAnswerButtons() {
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 3;
        for (AnswerButton answerButton : this.answerButtons) {
            gbc.gridx++;
            container.add(answerButton, gbc);
        }
    }

    private List<AnswerButton> createAnswerButtons() {
        List<Answer> answers = currentQuestion.getShuffled();
        List<AnswerButton> listOfAnswerButtons = new ArrayList<>();
        for (Answer a : answers) {
            AnswerButton aB = new AnswerButton(a);
            aB.setText(a.toString());
            listOfAnswerButtons.add(aB);
        }
        return listOfAnswerButtons;
    }

    public void addListenerToAnswerButton(ActionListener al, int index) {
        this.answerButtons.get(index).addActionListener(al);
    }

    private void showGUI() {
        this.timer.start();

        frame.setVisible(true);
        frame.repaint();
    }

    public final void update(Question nextQuestion) {
        Component[] comps = this.container.getComponents();
        List<Answer> answers = nextQuestion.getShuffled();
        int count = 0;
        for (Component comp : comps) {
            if (comp.getClass() == AnswerButton.class) {
                AnswerButton btn = (AnswerButton) comp;
                btn.setText(answers.get(count).toString());
                count++;
            }
            if (comp.getClass() == JTextArea.class) {
                JTextArea event = (JTextArea) comp;
                event.setText(nextQuestion.getEvent().getDescription());
            }
        }
        showGUI();
    }

    @Override
    public void run() {
        this.showGUI();
    }

    public QuestionStopwatch getTimer() {
        return timer;
    }

}
