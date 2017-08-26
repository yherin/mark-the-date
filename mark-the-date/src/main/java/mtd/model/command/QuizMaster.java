package mtd.model.command;

import mtd.controller.score.ScoreTracker;
import mtd.model.create.QuizCreator;
import mtd.model.models.Question;
import mtd.model.models.Quiz;
import mtd.view.GUICommand;

/**
 * Acts as Model, within the MVC structure. Stores, gives, loads game data.
 * @see Quiz
 * @author Jack Sheridan
 */
public class QuizMaster {

    /**
     * The Question currently in use. Displayed in the GUI.
     */
    private Question currentQuestion;
    /**
     * Quiz object containing a list of all questions for this round.
     */
    private Quiz quiz;
    /**
     * The index of the current question in the list of questions.
     */
    private Integer index;
    /**
     * Current score for this round.
     */
    private int score;
    /**
     * QuizCreator object
     */
    private QuizCreator qc;
    private ScoreTracker scoreTracker;

    public QuizMaster() {
        qc = new QuizCreator();
        scoreTracker = new ScoreTracker();
        fetchQuizObject();
        currentQuestion = quiz.getQuestions().get(index);
    }

    private void fetchQuizObject() {

        this.index = 0;
        this.score = 0;
        this.quiz = qc.createQuiz();
    }

    /**
     * Returns number of questions that this quiz has.
     *
     * @return int number of questions
     */
    public final int getNumberOfQuestions() {
        return this.quiz.getQuestions().size();
    }

    /**
     * Returns a Question object based on the command given (current, next)
     *
     * @param guicmd GUICommand which specifies the question to return. At the
     * point of return, the question returned is now QuizMaster.currentQuestion
     * @see GUICommand
     * @return Question The specified question.
     */
    public Question getSpecifiedQuestion(GUICommand guicmd) {
        currentQuestion = quiz.getQuestions().get(index);
        if (guicmd == GUICommand.CURRENT_QUESTION) {
            return currentQuestion;
        }
        if (guicmd == GUICommand.NEXT_QUESTION) {
            boolean validNext = forward(); //use this boolean to display some kind of popup.
            if (validNext) {

                currentQuestion = this.quiz.getQuestions().get(index);
                return currentQuestion;
            } else {
                return currentQuestion;
            }

        }
        throw new UnsupportedOperationException("GUICMD " + guicmd + " could not be understood");
    }

    public void buildNewQuiz() {
        fetchQuizObject();
    }

    private boolean forward() {

        try {
            index++;
            Question previousQuestion = quiz.getQuestions().get(index);
            boolean isSuccessful = validateQuestion(currentQuestion,
                    previousQuestion);
            return isSuccessful;
        } catch (IndexOutOfBoundsException e) {
            return false;
        }

    }

    private boolean validateQuestion(Question currentQuestion,
            Question previousQuestion) {
        if (previousQuestion.getClass() != Question.class) {
            return false;
        } else if (previousQuestion == currentQuestion) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Adds the given number of points to the player's score.
     *
     * @param points non zero positive integer points
     */
    public void addPointsToScore(int points) {
        this.scoreTracker.addPointsToScore(points);
    }

    /**
     * Returns the player's current score.
     *
     * @return int the current score.
     */
    public int getScore() {
        return this.scoreTracker.getScore();
    }

    public Integer getIndex() {
        return index;
    }

    /**
     * Set the index
     * @param Integer index [description]
     */
    public void setIndex(Integer index) {
        this.index = index;
    }

    /**
     * Reset the score for this round to 0.
     */
    public void resetScore() {
        this.scoreTracker.reset();
    }

    /**
     * FOR TESTING ONLY.
     *
     * @return Quiz quiz
     */
    public Quiz getQuiz() {
        return this.quiz;
    }

}
