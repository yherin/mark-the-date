package mtd.view;

import java.awt.Component;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import mtd.model.command.QuizMaster;
import mtd.model.models.Question;
import mtd.model.models.QuestionStopwatch;
import mtd.model.models.answer.Answer;
import mtd.view.create.QuestionComponentCreator;
import mtd.view.create.ResultsComponentCreator;

/**
 *
 * @author Jack Sheridan
 */
public class GameWindow implements Runnable {

    //core
    private Container container;
    private Question currentQuestion;
    private JFrame frame;
    private QuizMaster logic;

    //question view
    private JTextArea questionText;
    private List<AnswerButton> answerButtons;
    private QuestionStopwatch timer;

    //summary view
    private JTextArea summary;
    private JButton quit;
    private JButton newGame;

    //Utility
    private ResultsComponentCreator summaryCreator;
    private QuestionComponentCreator questionComponentCreator;

    public GameWindow(QuizMaster logic) {
        assignMembers(logic);
        createFrame();
        createAndMapComponents();
        assignCreatedComponentsToLocalVariables();
        addQuestionComponents();
        updateAnswerLabels(currentQuestion.getShuffled());
        this.showGUI();

    }

    private void createFrame() {
        frame = new JFrame("Mark the date");
        frame.setSize(500, 500);
        frame.setLayout(new GridBagLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        container = frame.getContentPane();
    }

    private void assignMembers(QuizMaster logic) {
        this.logic = logic;
        this.currentQuestion = this.logic.getSpecifiedQuestion(GUICommand.CURRENT_QUESTION);
    }

    private void assignCreatedComponentsToLocalVariables() {
        //Question components
        answerButtons = new ArrayList<>();
        answerButtons.add((AnswerButton) GUIComponentMap.getComponentByEnum(GUIComponent.BUTTON_ANSWER_1));
        answerButtons.add((AnswerButton) GUIComponentMap.getComponentByEnum(GUIComponent.BUTTON_ANSWER_2));
        answerButtons.add((AnswerButton) GUIComponentMap.getComponentByEnum(GUIComponent.BUTTON_ANSWER_3));
        answerButtons.add((AnswerButton) GUIComponentMap.getComponentByEnum(GUIComponent.BUTTON_ANSWER_4));
        questionText = (JTextArea) GUIComponentMap.getComponentByEnum(GUIComponent.LABEL_QUESTION_TEXT);
        timer = (QuestionStopwatch) GUIComponentMap.getComponentByEnum(GUIComponent.TIMER);

        //Summary components
        this.quit = (JButton) GUIComponentMap.getComponentByEnum(GUIComponent.BUTTON_QUIT);
        this.newGame = (JButton) GUIComponentMap.getComponentByEnum(GUIComponent.BUTTON_PLAY_AGAIN);
        this.summary = (JTextArea) GUIComponentMap.getComponentByEnum(GUIComponent.LABEL_SUMMARY_TEXT);
    }

    private void createAndMapComponents() {
        questionComponentCreator = new QuestionComponentCreator();
        questionComponentCreator.createAndMapComponents();
        summaryCreator = new ResultsComponentCreator();
        summaryCreator.createAndMapComponents();
    }

    public void addListenerToAnswerButton(ActionListener al, int index) {
        this.answerButtons.get(index).addActionListener(al);
    }

    public final void updateGUIToShowNextQuestion(Question nextQuestion) {
        updateAnswerLabels(nextQuestion.getShuffled());
        updateQuestionText(nextQuestion.getEvent().getDescription());
        timer.reset();
        addQuestionComponents();
        showGUI();
    }

    public final void updateAnswerLabels(List<Answer> shuffledAnswers) {
        for (int i = 0; i < shuffledAnswers.size(); i++) {
            answerButtons.get(i).setAnswer(shuffledAnswers.get(i));
        }

    }

    private void updateQuestionText(String text) {
        JTextArea event = (JTextArea) GUIComponentMap.getComponentByEnum(GUIComponent.LABEL_QUESTION_TEXT);
        event.setText(text);
    }

    public final void addSummaryComponents() {
        container.removeAll();
        container.add(summary);
        container.add(quit);
        container.add(newGame);
        showGUI();
    }

    private void addQuestionComponents() {
        container.removeAll();
        container.add(questionText);
        for (AnswerButton answerButton : answerButtons) {
            container.add(answerButton);

        }
        container.add(timer);
    }

    private void clearComponents() {

    }

    @Override
    public void run() {
        this.showGUI();
    }

    private void showGUI() {

        frame.setVisible(true);
        frame.repaint();
        this.timer.start();
    }

    public QuestionStopwatch getTimer() {
        return timer;
    }

}
