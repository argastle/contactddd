package de.dhbw.softwareengineering.contactddd.domain.values;

import java.util.Date;

public class SpecialDate {

    private Date date;
    private String description;

    public SpecialDate(Date date, String description) {
        this.date = validateDate(date);
        this.description = validateDescription(description);
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = validateDate(date);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = validateDescription(description);
    }

    private Date validateDate(Date date) {
        if (date == null) {
            throw new IllegalArgumentException("Date cannot be null.");
        }
        return date;
    }

    private String validateDescription(String description) {
        if (description == null || description.isEmpty()) {
            throw new IllegalArgumentException("Description cannot be null or empty.");
        }
        return description;
    }
}
