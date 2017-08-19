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
    private final Quiz quiz;
    private Integer index;
    private int score;

    public QuizMaster() {

        this.index = 0;
        this.quiz = fetchQuizObject();
        currentQuestion = quiz.getQuestions().get(index);
    }

    private Quiz fetchQuizObject() {
        QuizCreator qc = new QuizCreator();
        return qc.createQuiz();
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

        if (guicmd == GUICommand.CURRENT_QUESTION) {
            return currentQuestion;
        }
        if (guicmd == GUICommand.NEXT_QUESTION) {
            boolean validNext = forward(); //use this boolean to display some kind of popup.
            if (validNext) {

                currentQuestion = this.quiz.getQuestions().get(index);
                return currentQuestion;
            } else {
                System.exit(0);
            }

        }
        if (guicmd == GUICommand.PREVIOUS_QUESTION) {
            throw new UnsupportedOperationException("Not yet implemented.");
        }

        throw new IllegalStateException("GUICMD " + guicmd + " could not be understood");
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

}
