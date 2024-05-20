package de.dhbw.softwareengineering.contactddd.adapters.representations.group;

import de.dhbw.softwareengineering.contactddd.adapters.representations.contactid.ContactIdDTO;

import java.util.Set;

public class GroupDTO {
    private String name;
    private Set<ContactIdDTO> contactIds;

    public GroupDTO(String name, Set<ContactIdDTO> contactIds) {
        this.name = name;
        this.contactIds = contactIds;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<ContactIdDTO> getContacts() {
        return contactIds;
    }

    public void setContacts(Set<ContactIdDTO> contactIds) {
        this.contactIds = contactIds;
    }
}
