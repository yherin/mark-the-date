/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mtd.data.create;

import mtd.data.models.Event;
import mtd.data.models.Question;
import java.util.ArrayList;
import java.util.List;
import mtd.data.load.EventLoader;
import mtd.data.models.answer.CorrectAnswer;
import mtd.data.models.answer.RandomAnswer;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author sjack
 */
public class QuestionCreator {

    private final List<Event> events;
    private final RandomAnswerCreator rac;

    public QuestionCreator(List<Event> events, RandomAnswerCreator rac) {
        this.events = events;
        this.rac = rac;
    }

    public ArrayList<Question> getListOfQuestions(Integer quantity) {
        ArrayList<Question> questionList = new ArrayList<>();

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
