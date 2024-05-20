package de.dhbw.softwareengineering.contactddd.adapters.representations.contactid;

public class ContactIdDTO {

    private String id;

    public ContactIdDTO(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
