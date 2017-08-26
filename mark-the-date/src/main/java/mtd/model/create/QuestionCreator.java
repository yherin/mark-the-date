package mtd.model.create;

import java.util.ArrayList;
import java.util.List;
import mtd.model.load.SettingsLoader;
import mtd.model.models.Event;
import mtd.model.models.Question;
import mtd.model.models.answer.CorrectAnswer;
import mtd.model.models.answer.RandomAnswer;

/**
 * Handles the creation of Question objects from Event objects.
 * @see Question
 * @author Jack Sheridan
 */
public class QuestionCreator {

    /**
     * List of Event objects, from which Question objects are created.
     */
    private final List<Event> events;
    /**
     * Used to create RandomAnswer objects.
     */
    private final RandomAnswerCreator rac;

    public QuestionCreator(List<Event> events, RandomAnswerCreator rac) {
        this.events = events;
        this.rac = rac;
    }

    /**
     * Creates a returns a list of questions. The size of the list is determined
     * by configuration settings.json.
     * @see Question
     * @return List<Question> list of Question objects.
     */
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
