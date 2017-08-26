package mtd.view;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import mtd.model.command.QuizMaster;
import mtd.model.models.Question;
import mtd.model.models.QuestionStopwatch;
import mtd.model.models.answer.Answer;
import mtd.view.create.QuestionComponentCreator;
import mtd.view.create.ResultsComponentCreator;
import mtd.view.create.WelcomeComponentCreator;

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

    //welcome screen
    private JTextArea welcomeText;
    private JButton startButton;

    //question view
    private JTextArea questionText;
    private List<AnswerButton> answerButtons;
    private QuestionStopwatch timer;

    //summary view
    private JTextArea scoreText;
    private JTextArea scoreInt;
    private JButton quit;
    private JButton newGame;

    //Utility
    private ResultsComponentCreator summaryCreator;
    private QuestionComponentCreator questionComponentCreator;

    public GameWindow(QuizMaster logic) {
        performInitialSetup(logic);
        this.showGUI();
    }

    private void performInitialSetup(QuizMaster logic) {
        assignMembers(logic);
        createFrame();
        createAndMapComponents();
        assignCreatedComponentsToLocalVariables();
        loadWelcomeView();

    }

    /*
    Component creation methods.
     */
    private void createAndMapComponents() {
        new WelcomeComponentCreator().createAndMapComponents();
        new QuestionComponentCreator().createAndMapComponents();
        new ResultsComponentCreator().createAndMapComponents();
    }

    private void createFrame() {
        frame = new JFrame("Mark the date");
        frame.setSize(750, 750);
        frame.setLayout(new GridBagLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        container = frame.getContentPane();
    }

    /*
    Component assignment methods.
     */
    private void assignMembers(QuizMaster logic) {
        this.logic = logic;
        this.currentQuestion = this.logic.getSpecifiedQuestion(GUICommand.CURRENT_QUESTION);
    }

    private void assignCreatedComponentsToLocalVariables() {
        assignCreatedQuestionComponents();
        assignCreatedSummaryComponents();
        assignCreatedWelcomeComponents();
    }

    private void assignCreatedWelcomeComponents() {
        welcomeText = (JTextArea) GUIComponentMap.getComponentByEnum(GUIComponent.WELCOME_TEXT);
        startButton = (JButton) GUIComponentMap.getComponentByEnum(GUIComponent.BUTTON_START);
    }

    private void assignCreatedSummaryComponents() {
        //Summary components
        this.quit = (JButton) GUIComponentMap.getComponentByEnum(GUIComponent.BUTTON_QUIT);
        this.newGame = (JButton) GUIComponentMap.getComponentByEnum(GUIComponent.BUTTON_PLAY_AGAIN);
        this.scoreText = (JTextArea) GUIComponentMap.getComponentByEnum(GUIComponent.LABEL_SUMMARY_TEXT);
        this.scoreInt = (JTextArea) GUIComponentMap.getComponentByEnum(GUIComponent.LABEL_SCORE_INT);
    }

    private void assignCreatedQuestionComponents() {
        assignCreatedAnswerButtons();
        questionText = (JTextArea) GUIComponentMap.getComponentByEnum(GUIComponent.LABEL_QUESTION_TEXT);
        timer = (QuestionStopwatch) GUIComponentMap.getComponentByEnum(GUIComponent.TIMER);
    }

    private void assignCreatedAnswerButtons() {
        answerButtons = new ArrayList<>();
        answerButtons.add((AnswerButton) GUIComponentMap.getComponentByEnum(GUIComponent.BUTTON_ANSWER_1));
        answerButtons.add((AnswerButton) GUIComponentMap.getComponentByEnum(GUIComponent.BUTTON_ANSWER_2));
        answerButtons.add((AnswerButton) GUIComponentMap.getComponentByEnum(GUIComponent.BUTTON_ANSWER_3));
        answerButtons.add((AnswerButton) GUIComponentMap.getComponentByEnum(GUIComponent.BUTTON_ANSWER_4));
    }

    public void addListenerToAnswerButton(ActionListener al, int index) {
        this.answerButtons.get(index).addActionListener(al);
    }

    public final void loadWelcomeView() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = 3;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.ipadx = 20;
        gbc.ipady = 20;
        container.add(welcomeText, gbc);
        gbc.gridwidth = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.SOUTH;
        gbc.ipadx = 20;
        gbc.ipady = 5;
        startButton.addActionListener((ActionEvent ae) -> {
            initialiseQuestionView();
        });
        container.add(startButton, gbc);
        frame.repaint();
    }

    private void initialiseQuestionView() {
        addQuestionComponents();
        updateQuestionText(currentQuestion.getEvent().getDescription());
        updateAnswerLabels(currentQuestion.getShuffled());
    }

    public final void updateGUIToShowNextQuestion(Question nextQuestion) {
        updateAnswerLabels(nextQuestion.getShuffled());
        updateQuestionText(nextQuestion.getEvent().getDescription());
        timer.reset();
        addQuestionComponents();
        showGUI();
    }

    private final void updateAnswerLabels(List<Answer> shuffledAnswers) {
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
        container.add(scoreText);
        container.add(scoreInt);
        container.add(quit);
        container.add(newGame);
        showGUI();
    }

    private void addQuestionComponents() {
        container.removeAll();
        //question
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0;
        gbc.ipady = 80;
        gbc.gridy = 1;
        container.add(questionText, gbc);
        gbc = new GridBagConstraints();
        //buttons
        gbc.gridwidth = 1;
        gbc.gridy = 2;
        gbc.weightx = 0.5;
        gbc.ipady = 25;
        gbc.ipadx = 50;
        for (AnswerButton answerButton : answerButtons) {
            container.add(answerButton, gbc);
        }
        //timer
        gbc = new GridBagConstraints();
        gbc.gridwidth = 1;
        gbc.gridy = 0;
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.PAGE_START;
        gbc.ipadx = 15;
        gbc.ipady = 15;
        container.add(timer, gbc);

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
