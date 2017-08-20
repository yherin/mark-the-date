/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mtd.model.create;

import java.util.List;
import mtd.controller.score.ScoreAssigner;
import mtd.model.models.Event;
import mtd.model.models.Question;
import mtd.model.models.Quiz;

/**
 *
 * @author Jack Sheridan
 */
public class QuizCreator {

    private Integer numberOfEvents;
    private Quiz quiz;
    private EventCreator ec;

    public QuizCreator() {
        ec = new EventCreator();
        List<Question> questions = createQuestions();
        quiz = new Quiz(questions);
    }

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
