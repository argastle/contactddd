package de.dhbw.softwareengineering.contactddd.plugins.rest;

import de.dhbw.softwareengineering.contactddd.adapters.representations.contact.ContactDTO;
import de.dhbw.softwareengineering.contactddd.adapters.representations.contact.ContactEntityToContactDTOMapper;
import de.dhbw.softwareengineering.contactddd.adapters.representations.group.GroupDTO;
import de.dhbw.softwareengineering.contactddd.adapters.representations.group.GroupEntityToGroupDTOMapper;
import de.dhbw.softwareengineering.contactddd.application.services.GroupService;
import de.dhbw.softwareengineering.contactddd.domain.entities.Contact;
import de.dhbw.softwareengineering.contactddd.domain.entities.Group;
import de.dhbw.softwareengineering.contactddd.domain.exceptions.GroupNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class GroupControllerTest {

    @Mock
    private GroupService groupService;

    @Mock
    private ContactEntityToContactDTOMapper contactToDTO;

    @Mock
    private GroupEntityToGroupDTOMapper groupToDTO;

    @InjectMocks
    private GroupController groupController;

    private Group group;
    private GroupDTO groupDTO;
    private Contact contact;
    private ContactDTO contactDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        group = new Group("Friends");
        groupDTO = new GroupDTO("Friends", Set.of());
        contact = new Contact("Max Mustermann", "max.mustermann@example.com", "+123456789", Set.of(), Set.of());
        contactDTO = new ContactDTO("1", "Max Mustermann", "max.mustermann@example.com", "+123456789", Set.of(), Set.of(), Set.of(), null, null);

        when(groupToDTO.apply(group)).thenReturn(groupDTO);
        when(contactToDTO.apply(contact)).thenReturn(contactDTO);
    }

    @Test
    void createGroup() {
        when(groupService.createGroup("Friends")).thenReturn(group);

        ResponseEntity<GroupDTO> response = groupController.createGroup("Friends");

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(groupDTO, response.getBody());
    }

    @Test
    void deleteGroup() {
        doNothing().when(groupService).deleteGroup("Friends");

        ResponseEntity<Void> response = groupController.deleteGroup("Friends");

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(groupService, times(1)).deleteGroup("Friends");
    }

    @Test
    void deleteGroup_NotFound() {
        doThrow(new GroupNotFoundException("Group not found with name: Friends")).when(groupService).deleteGroup("Friends");

        ResponseEntity<Void> response = groupController.deleteGroup("Friends");

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(groupService, times(1)).deleteGroup("Friends");
    }

    @Test
    void addContactToGroup() {
        doNothing().when(groupService).addContactToGroup("1", "Friends");

        ResponseEntity<Void> response = groupController.addContactToGroup("1", "Friends");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(groupService, times(1)).addContactToGroup("1", "Friends");
    }

    @Test
    void removeContactFromGroup() {
        doNothing().when(groupService).removeContactFromGroup("1", "Friends");

        ResponseEntity<Void> response = groupController.removeContactFromGroup("1", "Friends");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(groupService, times(1)).removeContactFromGroup("1", "Friends");
    }

    @Test
    void removeContactFromGroup_NotFound() {
        doThrow(new GroupNotFoundException("Group not found with name: Friends")).when(groupService).removeContactFromGroup("1", "Friends");

        ResponseEntity<Void> response = groupController.removeContactFromGroup("1", "Friends");

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(groupService, times(1)).removeContactFromGroup("1", "Friends");
    }

    @Test
    void getAllGroups() {
        when(groupService.findAllGroups()).thenReturn(List.of(group));

        ResponseEntity<List<GroupDTO>> response = groupController.getAllGroups();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals(groupDTO, response.getBody().get(0));
    }

    @Test
    void getContactsInGroup() {
        when(groupService.getContactsInGroup("Friends")).thenReturn(List.of(contact));

        ResponseEntity<List<ContactDTO>> response = groupController.getContactsInGroup("Friends");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals(contactDTO, response.getBody().get(0));
    }

    @Test
    void getContactsInGroup_NotFound() {
        doThrow(new GroupNotFoundException("Group not found with name: Friends")).when(groupService).getContactsInGroup("Friends");

        ResponseEntity<List<ContactDTO>> response = groupController.getContactsInGroup("Friends");

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(groupService, times(1)).getContactsInGroup("Friends");
    }
}
