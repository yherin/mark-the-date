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

    protected Integer value;

    public Answer() {
    }

    public Integer getValue() {
        return value;
    }

    @Override
    public String toString() {
        return this.value.toString();
    }

    @Override
    public int compareTo(Answer other) {
        return other.getValue() - this.value;
    }

}
