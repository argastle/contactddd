package de.dhbw.softwareengineering.contactddd.adapters.representations.specialdate;

import java.util.Date;

public class SpecialDateDTO {
    private Date date;
    private String description;

    public SpecialDateDTO(Date date, String description) {
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
