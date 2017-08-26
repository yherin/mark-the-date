
package mtd.model.models;

import java.util.Objects;

/**
 *
 * @author Jack Sheridan
 */
public abstract class Historical {

    private final Integer date;
    private final String description;

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
