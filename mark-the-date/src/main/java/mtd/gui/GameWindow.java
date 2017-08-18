/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mtd.gui;

import java.awt.Component;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;
import mtd.data.command.QuizMaster;
import mtd.data.models.Question;
import mtd.data.models.QuestionStopwatch;
import mtd.data.models.answer.Answer;

/**
 *
 * @author sjack
 */
public class GameWindow implements Runnable {

    private Container container;
    private Question currentQuestion;
    private GridBagConstraints gbc;
    private JFrame frame;
    private QuizMaster logic;
    private List<AnswerButton> answerButtons;
    private QuestionStopwatch timer;

    public GameWindow(QuizMaster logic) {
        this.logic = logic;
        this.currentQuestion = logic.getSpecifiedQuestion(GUICommand.CURRENT_QUESTION);
        gbc = new GridBagConstraints();
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
        gbc.gridheight = 2;
        addTimerLabel();
        resetConstraints();
        addQuestionText();
        resetConstraints();
        addAnswerButtons();
        resetConstraints();

    }

    private void addTimerLabel() {
        gbc.gridx = 3;
        this.timer = new QuestionStopwatch();
        container.add(this.timer);
    }

    private void addQuestionText() {

        gbc.gridy = 0;
        container.add(new JLabel(currentQuestion.getEvent().getDescription()));
    }

    private void resetConstraints() {
        gbc = new GridBagConstraints();
        gbc.gridheight = 2;
    }

    private void addAnswerButtons() {
        gbc.gridy = 1;
        for (AnswerButton answerButton : this.answerButtons) {
            container.add(answerButton);
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
            if (comp.getClass() == JLabel.class) {
                JLabel event = (JLabel) comp;
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
