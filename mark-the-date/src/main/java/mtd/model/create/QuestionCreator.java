/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mtd.model.create;

import java.util.ArrayList;
import java.util.List;
import mtd.model.load.SettingsLoader;
import mtd.model.models.Event;
import mtd.model.models.Question;
import mtd.model.models.answer.CorrectAnswer;
import mtd.model.models.answer.RandomAnswer;

/**
 *
 * @author Jack Sheridan
 */
public class QuestionCreator {

    private final List<Event> events;
    private final RandomAnswerCreator rac;

    public QuestionCreator(List<Event> events, RandomAnswerCreator rac) {
        this.events = events;
        this.rac = rac;
    }

    public ArrayList<Question> getListOfQuestions() {
        ArrayList<Question> questionList = new ArrayList<>();
        int quantity = new SettingsLoader().getSetting("number_of_events");
        for (int i = 0; i < quantity; i++) {
            Question q = createQuestionFromEventAndAnswers(this.events.get(i));
            questionList.add(q);
        }

        return questionList;

    }

    private Question createQuestionFromEventAndAnswers(Event e) {
        CorrectAnswer ca = createCorrectAnswerForEvent(e);
        List<RandomAnswer> ra = createRandomAnswers(ca);
        Question q = new Question(e, ca, ra);
        return q;
    }

    private CorrectAnswer createCorrectAnswerForEvent(Event e) {
        CorrectAnswer answer = new CorrectAnswer(e.getDate());
        return answer;
    }

    private List<RandomAnswer> createRandomAnswers(CorrectAnswer ca) {
        return rac.generateRandomAnswers(ca);
    }

}
