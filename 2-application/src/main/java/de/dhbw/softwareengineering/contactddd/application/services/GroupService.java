package de.dhbw.softwareengineering.contactddd.application.services;

import de.dhbw.softwareengineering.contactddd.domain.entities.Contact;
import de.dhbw.softwareengineering.contactddd.domain.entities.Group;
import de.dhbw.softwareengineering.contactddd.domain.exceptions.ContactNotFoundException;
import de.dhbw.softwareengineering.contactddd.domain.exceptions.GroupNotFoundException;
import de.dhbw.softwareengineering.contactddd.domain.repositories.IContactRepository;
import de.dhbw.softwareengineering.contactddd.domain.repositories.IGroupRepository;
import de.dhbw.softwareengineering.contactddd.domain.values.ContactId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class GroupService {

    private final IContactRepository contactRepository;
    private final IGroupRepository groupRepository;

    @Autowired
    public GroupService(IContactRepository contactRepository, IGroupRepository groupRepository) {
        this.contactRepository = contactRepository;
        this.groupRepository = groupRepository;
    }

    public Group createGroup(String groupName) {
        Group newGroup = new Group(groupName);
        return groupRepository.save(newGroup);
    }

    public void deleteGroup(String groupName) {
        Group group = getGroupOrThrow(groupName);
        Set<ContactId> contactIds = group.getContactIds();
        for (ContactId contactId : contactIds) {
            Contact contact = getContactOrThrow(contactId);
            contact.removeGroup(groupName);
            contactRepository.save(contact);
        }
        groupRepository.deleteById(groupName);
    }

    public void addContactToGroup(String contactId, String groupName) {
        Contact contact = getContactOrThrow(new ContactId(contactId));
        contact.addGroup(groupName);
        contactRepository.save(contact);

        Group group = groupRepository.findById(groupName).orElseGet(() -> new Group(groupName));
        group.addContact(contact.getContactId());
        groupRepository.save(group);
    }

    public void removeContactFromGroup(String contactId, String groupName) {
        Contact contact = getContactOrThrow(new ContactId(contactId));
        contact.removeGroup(groupName);
        contactRepository.save(contact);

        Group group = getGroupOrThrow(groupName);
        group.removeContact(contact.getContactId());
        if (group.getContactIds().isEmpty()) {
            groupRepository.deleteById(groupName);
        } else {
            groupRepository.save(group);
        }
    }

    public List<Group> findAllGroups() {
        return groupRepository.getAllGroups();
    }

    public List<Contact> getContactsInGroup(String groupName) {
        Group group = getGroupOrThrow(groupName);
        return group.getContactIds().stream()
                .map(this::getContactOrThrow)
                .collect(Collectors.toList());
    }

    private Contact getContactOrThrow(ContactId contactId) {
        return contactRepository.findById(contactId)
                .orElseThrow(() -> new ContactNotFoundException("Contact not found with ID: " + contactId));
    }

    private Group getGroupOrThrow(String groupName) {
        return groupRepository.findById(groupName)
                .orElseThrow(() -> new GroupNotFoundException("Group not found with Name: " + groupName));
    }
}
