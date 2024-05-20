package de.dhbw.softwareengineering.contactddd.domain.entities;

import de.dhbw.softwareengineering.contactddd.domain.values.ContactId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

@Document(collection = "groups")
public class Group {
    @Id
    private String name;
    private Set<ContactId> contactIds;

    public Group(String name, Set<ContactId> contactIds) {
        this.name = name;
        this.contactIds = contactIds != null ? new HashSet<>(contactIds) : new HashSet<>();
    }

    public Group(){
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<ContactId> getContactIds() {
        return contactIds;
    }

    public void setContactIds(Set<ContactId> contactIds) {
        this.contactIds = contactIds;
    }

    public void addContact(ContactId contactId) {
        this.contactIds.add(contactId);
    }

    public void removeContact(ContactId contactId) {
        this.contactIds.remove(contactId);
    }
}
