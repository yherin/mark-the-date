package mtd.model.command;

import mtd.model.create.QuizCreator;
import mtd.model.models.Question;
import mtd.model.models.Quiz;
import mtd.view.GUICommand;

/**
 *
 * @author Jack Sheridan
 */
public class QuizMaster {

    private Question currentQuestion;
    private Quiz quiz;
    private Integer index;
    private int score;
    private QuizCreator qc;

    public QuizMaster() {
        qc = new QuizCreator();
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
     * Returns a Question object based on the command given (current, prev,
     * next)
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
        if (guicmd == GUICommand.PREVIOUS_QUESTION) {
            throw new UnsupportedOperationException("Not yet implemented.");
        }

        throw new IllegalStateException("GUICMD " + guicmd + " could not be understood");
    }

    public void buildNewQuiz() {
        fetchQuizObject();
    }

    private boolean forward() {
        if (index < quiz.getQuestions().size() - 1) {
            index++;
            try {
                Question previousQuestion = quiz.getQuestions().get(index);
                boolean isSuccessful = validateQuestion(currentQuestion,
                        previousQuestion);
                return isSuccessful;
            } catch (IndexOutOfBoundsException e) {
                return false;
            }
        } else {
            return false;
        }
    }

    private boolean validateQuestion(Question currentQuestion,
            Question previousQuestion) {
        if (previousQuestion.getClass() != Question.class) {
            System.out.println();
            return false;
        }
        if (previousQuestion == currentQuestion) {
            return false;
        }
        return true;
    }

    /**
     * Adds the given number of points to the player's score.
     *
     * @param int non zero positive integer points
     */
    public void addPointsToScore(int points) {
        if (points < 1) {
            return;
        }
        this.score += points;
    }

    /**
     * Returns the player's current score.
     *
     * @return int the current score.
     */
    public int getScore() {
        return this.score;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
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
