package de.dhbw.softwareengineering.contactddd.application.services;

import de.dhbw.softwareengineering.contactddd.domain.entities.Contact;
import de.dhbw.softwareengineering.contactddd.domain.entities.Group;
import de.dhbw.softwareengineering.contactddd.domain.exceptions.ContactNotFoundException;
import de.dhbw.softwareengineering.contactddd.domain.exceptions.GroupNotFoundException;
import de.dhbw.softwareengineering.contactddd.domain.repositories.IContactRepository;
import de.dhbw.softwareengineering.contactddd.domain.repositories.IGroupRepository;
import de.dhbw.softwareengineering.contactddd.domain.values.ContactId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GroupServiceTest {

    @Mock
    private IContactRepository contactRepository;

    @Mock
    private IGroupRepository groupRepository;

    @InjectMocks
    private GroupService groupService;

    private Group group;
    private Contact contact;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        group = new Group("Friends");
        contact = new Contact("Max Mustermann", "max.mustermann@example.com", "+123456789", new HashSet<>(), new HashSet<>());
        group.addContact(contact.getContactId());
    }

    @Test
    void createGroup() {
        when(groupRepository.save(any(Group.class))).thenReturn(group);

        Group createdGroup = groupService.createGroup("Friends");

        assertNotNull(createdGroup);
        assertEquals("Friends", createdGroup.getName());
        verify(groupRepository, times(1)).save(any(Group.class));
    }

    @Test
    void deleteGroup() {
        when(groupRepository.findById("Friends")).thenReturn(Optional.of(group));
        when(contactRepository.findById(any(ContactId.class))).thenReturn(Optional.of(contact));
        doNothing().when(groupRepository).deleteById("Friends");

        groupService.deleteGroup("Friends");

        verify(groupRepository, times(1)).findById("Friends");
        verify(contactRepository, times(1)).findById(any(ContactId.class));
        verify(contactRepository, times(1)).save(any(Contact.class));
        verify(groupRepository, times(1)).deleteById("Friends");
    }

    @Test
    void deleteGroup_NotFound() {
        when(groupRepository.findById("Friends")).thenReturn(Optional.empty());

        assertThrows(GroupNotFoundException.class, () -> groupService.deleteGroup("Friends"));

        verify(groupRepository, times(1)).findById("Friends");
        verify(groupRepository, times(0)).deleteById("Friends");
    }

    @Test
    void addContactToGroup() {
        when(contactRepository.findById(any(ContactId.class))).thenReturn(Optional.of(contact));
        when(contactRepository.save(any(Contact.class))).thenReturn(contact);
        when(groupRepository.findById("Friends")).thenReturn(Optional.of(group));
        when(groupRepository.save(any(Group.class))).thenReturn(group);

        groupService.addContactToGroup("1", "Friends");

        verify(contactRepository, times(1)).findById(any(ContactId.class));
        verify(contactRepository, times(1)).save(any(Contact.class));
        verify(groupRepository, times(1)).findById("Friends");
        verify(groupRepository, times(1)).save(any(Group.class));
    }

    @Test
    void addContactToGroup_GroupNotFound() {
        when(contactRepository.findById(any(ContactId.class))).thenReturn(Optional.of(contact));
        when(groupRepository.findById("Friends")).thenReturn(Optional.empty());

        groupService.addContactToGroup("1", "Friends");

        verify(contactRepository, times(1)).findById(any(ContactId.class));
        verify(contactRepository, times(1)).save(any(Contact.class));
        verify(groupRepository, times(1)).findById("Friends");
        verify(groupRepository, times(1)).save(any(Group.class));
    }

    @Test
    void removeContactFromGroup() {
        when(contactRepository.findById(any(ContactId.class))).thenReturn(Optional.of(contact));
        when(contactRepository.save(any(Contact.class))).thenReturn(contact);
        when(groupRepository.findById("Friends")).thenReturn(Optional.of(group));
        when(groupRepository.save(any(Group.class))).thenReturn(group);

        groupService.removeContactFromGroup("1", "Friends");

        verify(contactRepository, times(1)).findById(any(ContactId.class));
        verify(contactRepository, times(1)).save(any(Contact.class));
        verify(groupRepository, times(1)).findById("Friends");

        if (group.getContactIds().isEmpty()) {
            verify(groupRepository, times(1)).deleteById("Friends");
        } else {
            verify(groupRepository, times(1)).save(any(Group.class));
        }
    }


    @Test
    void removeContactFromGroup_GroupNotFound() {
        when(contactRepository.findById(any(ContactId.class))).thenReturn(Optional.of(contact));
        when(groupRepository.findById("Friends")).thenReturn(Optional.empty());

        assertThrows(GroupNotFoundException.class, () -> groupService.removeContactFromGroup("1", "Friends"));

        verify(contactRepository, times(1)).findById(any(ContactId.class));
        verify(contactRepository, times(1)).save(any(Contact.class));
        verify(groupRepository, times(1)).findById("Friends");
        verify(groupRepository, times(0)).save(any(Group.class));
    }

    @Test
    void findAllGroups() {
        when(groupRepository.getAllGroups()).thenReturn(List.of(group));

        List<Group> groups = groupService.findAllGroups();

        assertNotNull(groups);
        assertEquals(1, groups.size());
        verify(groupRepository, times(1)).getAllGroups();
    }

    @Test
    void getContactsInGroup() {
        when(groupRepository.findById("Friends")).thenReturn(Optional.of(group));
        when(contactRepository.findById(any(ContactId.class))).thenReturn(Optional.of(contact));

        List<Contact> contacts = groupService.getContactsInGroup("Friends");

        assertNotNull(contacts);
        assertEquals(1, contacts.size());
        verify(groupRepository, times(1)).findById("Friends");
        verify(contactRepository, times(1)).findById(any(ContactId.class));
    }

    @Test
    void getContactsInGroup_GroupNotFound() {
        when(groupRepository.findById("Friends")).thenReturn(Optional.empty());

        assertThrows(GroupNotFoundException.class, () -> groupService.getContactsInGroup("Friends"));

        verify(groupRepository, times(1)).findById("Friends");
        verify(contactRepository, times(0)).findById(any(ContactId.class));
    }
}
