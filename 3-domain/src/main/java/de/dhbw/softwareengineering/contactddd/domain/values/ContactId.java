package de.dhbw.softwareengineering.contactddd.domain.values;

import java.util.UUID;

public class ContactId {
    private String id;

    public ContactId(String id) {
        this.id = id;
    }

    public ContactId() {
        this(UUID.randomUUID().toString());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
