/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mtd.data.create;

import java.util.List;
import mtd.data.models.Event;
import mtd.data.models.Question;
import mtd.data.models.Quiz;
import mtd.logic.score.ScoreAssigner;

/**
 *
 * @author sjack
 */
public class QuizCreator {

    private Integer numberOfEvents;

    public QuizCreator() {
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
        EventCreator ec = new EventCreator();
        return ec.createEvents();
    }
}
