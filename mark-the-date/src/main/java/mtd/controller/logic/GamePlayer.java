package mtd.controller.logic;

import javax.swing.SwingUtilities;
import mtd.model.command.QuizMaster;
import mtd.model.models.Question;
import mtd.view.GUICommand;
import mtd.view.GameWindow;

/**
 * Initialises components needed to the run the game. Provides a method to start
 * the game.
 *
 * @author Jack Sheridan
 */
public class GamePlayer {

    /**
     * Holds all data needed to run the game. Represents model in MVC structure.
     */
    private final QuizMaster quizMaster;
    /**
     * Manages UI components and provides methods to create views. Represents
     * view in MVC structure.
     */
    private final GameWindow gui;
    /**
     * Listeners for user input. Sends commands to model and view. Represents
     * controller in MVC structure.
     */
    private final UserInputController listener;
    /**
     * The question that is initialised first at the start of the game.
     */
    private Question firstQuestion;

    private Thread errorHandlingWhileLoading;

    /**
     * Create an instance of GamePlayer, allowing you to run the game.
     */
    public GamePlayer() {
        startErrorHandlingThread();
        quizMaster = new QuizMaster();
        updateCurrentQuestion();
        gui = new GameWindow(quizMaster);
        this.listener = new UserInputController(gui, quizMaster);
    }

    private void startErrorHandlingThread() {
        errorHandlingWhileLoading = new Thread();
        SwingUtilities.invokeLater(errorHandlingWhileLoading);
    }

    private void updateCurrentQuestion() {
        this.firstQuestion = this.quizMaster.getSpecifiedQuestion(GUICommand.CURRENT_QUESTION);
    }

    /**
     * Invokes the GUI thread which starts the game for the user.
     *
     * @return the number of active threads.
     */
    public final int play() {
        errorHandlingWhileLoading.interrupt();
        SwingUtilities.invokeLater(gui);
        return Thread.activeCount();

    }
}
