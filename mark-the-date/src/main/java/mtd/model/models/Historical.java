
package mtd.model.models;

import java.util.Objects;

/**
 * abstract class representing any event or other historical phenemenom that
 * may form part of a quiz question.
 * @author Jack Sheridan
 */
public abstract class Historical {

    /**
     * Year in which historical occured.
     */
    private final Integer date;
    /**
     * Description of historical.
     */
    private final String description;

    /**
     * Create a new Historical.
     * @param  Integer date          date that the historical occured.
     * @param  String  description   description of the historical.
     */
    public Historical(Integer date, String description) {
        this.date = date;
        this.description = description;
    }

    public Integer getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "" + getDate() + ": " + getDescription();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + Objects.hashCode(this.date);
        hash = 19 * hash + description.hashCode();
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Historical other = (Historical) obj;
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        return true;
    }

}
