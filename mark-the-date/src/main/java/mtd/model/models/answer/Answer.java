/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mtd.model.models.answer;

/**
 *
 * @author Jack Sheridan
 */
public abstract class Answer {

    protected Integer year;
    protected Integer score;

    public Answer() {

    }

    public Integer getYear() {
        return year;
    }

    @Override
    public String toString() {
        return this.year.toString();
    }

    public Integer getScore() {
        return this.score;
    }

}
