package de.dhbw.softwareengineering.contactddd.domain.values;

import java.util.Date;

public class SpecialDate {
    private Date date;
    private String description;

    public SpecialDate(Date date, String description) {
        this.date = date;
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
