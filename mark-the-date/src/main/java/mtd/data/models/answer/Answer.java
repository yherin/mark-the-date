/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mtd.data.models.answer;

/**
 *
 * @author sjack
 */
public abstract class Answer implements Comparable<Answer> {

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

    @Override
    public int compareTo(Answer other) {
        return other.getYear() - this.year;
    }

}
