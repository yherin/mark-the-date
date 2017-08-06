/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mtd.logic.main;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import mtd.data.create.EventCreator;
import mtd.data.create.QuestionCreator;
import mtd.data.create.RandomAnswerCreator;
import mtd.data.models.Event;
import mtd.data.models.Question;
import mtd.data.models.answer.Answer;
import mtd.logic.score.ScoreAssigner;
import mtd.logic.score.ScoreCalculator;

/**
 *
 * @author sjack
 */
public class GamePlayer {

    private final int GAME_LENGTH;
    private final int numberOfEvents;

    private EventCreator ec;
    private List<Event> listOfEvents;

    private QuestionCreator qc;
    private List<Question> questionList;
    private Random simulatedUserInput = new Random();

    public GamePlayer(int numberOfEvents) {
        this.numberOfEvents = numberOfEvents;
        this.GAME_LENGTH = this.numberOfEvents - 1;
        ec = new EventCreator(numberOfEvents);
        listOfEvents = ec.createEvents();
        qc = new QuestionCreator(listOfEvents, new RandomAnswerCreator());
        questionList = qc.getListOfQuestions(numberOfEvents);
        ScoreAssigner.assignScoresToQuestionList(questionList);

    }

    public final int play() {
        int totalScore = 0;
        for (Question q : questionList) {
            System.out.println(q);
            int guess = getInput();
            Answer chosen = q.getShuffled().get(guess);
            System.out.println("choice: " + chosen.getYear());
            int score = chosen.getScore();
            System.out.println("score: " + score);
            totalScore += score;
        }
        return totalScore;
    }

    private int getInput() {
        return simulatedUserInput.nextInt(3);
    }

}
