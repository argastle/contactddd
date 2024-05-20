package de.dhbw.softwareengineering.contactddd.application.services;

import java.util.Set;

public final class CreateGroupCommand {
    private String name;
    private Set<String> contactIdInfos;

    public CreateGroupCommand(String name, Set<String> contactIdInfos) {
        this.name = name;
        this.contactIdInfos = contactIdInfos;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<String> getContactIdInfos() {
        return contactIdInfos;
    }

    public void setContactIdInfos(Set<String> contactIdInfos) {
        this.contactIdInfos = contactIdInfos;
    }
}
