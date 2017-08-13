package mtd.data.command;

import java.util.List;
import mtd.data.create.QuestionCreator;
import mtd.data.create.QuizCreator;
import mtd.data.models.Question;
import mtd.data.models.Quiz;
import mtd.gui.GUICommand;

/**
 *
 * @author sjack
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

    public final int getNumberOfQuestions() {
        return this.quiz.getQuestions().size();
    }

    /**
     * Returns a Question object based on the command given (current, prev,
     * next)
     *
     * @param guicmd GUICommand which specifies the question to return. At the
     * point of return, the question returned is now QuizMaster.currentQuestion
     *
     * @return The specified question.
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

    private final boolean back() {

        if (index >= 1) {
            index--;
            try {
                Question previousQuestion = quiz.getQuestions().get(index);
                boolean isSuccessful = validateQuestion(currentQuestion, previousQuestion);
                if (isSuccessful) {
                    currentQuestion = previousQuestion;
                }
                return isSuccessful;
            } catch (IndexOutOfBoundsException e) {
                return false;
            }
        } else {
            return false;
        }
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

    public void addPointsToScore(int points) {
        this.score += points;
    }

    public int getScore() {
        return this.score;
    }

}
