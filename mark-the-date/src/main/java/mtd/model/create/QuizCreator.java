/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mtd.model.create;

import java.util.List;
import mtd.model.score.ScoreAssigner;
import mtd.model.models.Event;
import mtd.model.models.Question;
import mtd.model.models.Quiz;

/**
 * Creates Quiz objects which store all Question objects for one round of the game.
 * @see Quiz
 * @author Jack Sheridan
 */
public class QuizCreator {

    /**
     * Number of events each quiz holds.
     */
    private Integer numberOfEvents;
    /**
     * Quiz object storing Question objects for this round.
     */
    private Quiz quiz;
    /**
     * EventCreator used for creating events.
     */
    private EventCreator ec;


    public QuizCreator() {
        ec = new EventCreator();
        List<Question> questions = createQuestions();
        quiz = new Quiz(questions);
    }

    /**
     * Builds and returns a Quiz object containing a new set of questions.
     * @return The created Quiz object.
     */
    public Quiz createQuiz() {
        List<Question> questions = createQuestions();
        Quiz quiz = new Quiz(questions);
        return quiz;
    }

    private List<Question> createQuestions() {

        List<Event> events = createEvents();
        QuestionCreator qc = new QuestionCreator(events, new RandomAnswerCreator());
        List<Question> ql = qc.getListOfQuestions();
        assignScores(ql);
        return ql;
    }

    private void assignScores(List<Question> ql) {
        ScoreAssigner.assignScoresToQuestionList(ql);
    }

    private List<Event> createEvents() {

        return ec.createEvents();
    }
}
