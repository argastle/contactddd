package de.dhbw.softwareengineering.contactddd.plugins.rest;

import de.dhbw.softwareengineering.contactddd.adapters.representations.contact.ContactDTO;
import de.dhbw.softwareengineering.contactddd.adapters.representations.group.GroupDTO;
import de.dhbw.softwareengineering.contactddd.adapters.representations.contact.ContactEntityToContactDTOMapper;
import de.dhbw.softwareengineering.contactddd.adapters.representations.group.GroupEntityToGroupDTOMapper;
import de.dhbw.softwareengineering.contactddd.application.services.GroupService;
import de.dhbw.softwareengineering.contactddd.domain.entities.Contact;
import de.dhbw.softwareengineering.contactddd.domain.entities.Group;
import de.dhbw.softwareengineering.contactddd.domain.exceptions.ContactNotFoundException;
import de.dhbw.softwareengineering.contactddd.domain.exceptions.GroupNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/groups")
public class GroupController {

    private final GroupService groupService;

    private final ContactEntityToContactDTOMapper contactToDTO;

    private final GroupEntityToGroupDTOMapper groupToDTO;

    @Autowired
    public GroupController(final GroupService groupService, ContactEntityToContactDTOMapper contactToDTO, GroupEntityToGroupDTOMapper groupToDTO) {
        this.groupService = groupService;
        this.contactToDTO = contactToDTO;
        this.groupToDTO = groupToDTO;
    }

    @PostMapping
    public ResponseEntity<GroupDTO> createGroup(@RequestParam String groupName) {
        Group createdGroup = groupService.createGroup(groupName);
        return new ResponseEntity<>(groupToDTO.apply(createdGroup), HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteGroup(@RequestParam String groupName) {
        try {
            groupService.deleteGroup(groupName);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (GroupNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/{contactId}/addGroup")
    public ResponseEntity<Void> addContactToGroup(@PathVariable String contactId, @RequestParam String groupName) {
        groupService.addContactToGroup(contactId, groupName);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/{contactId}/removeGroup")
    public ResponseEntity<Void> removeContactFromGroup(@PathVariable String contactId, @RequestParam String groupName) {
        try {
            groupService.removeContactFromGroup(contactId, groupName);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (GroupNotFoundException | ContactNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<GroupDTO>> getAllGroups(){
        List<GroupDTO> groups = groupService.findAllGroups()
                .stream()
                .map(groupToDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(groups, HttpStatus.OK);
    }

    @GetMapping("/{groupName}/contacts")
    public ResponseEntity<List<ContactDTO>> getContactsInGroup(@PathVariable String groupName) {
        try {
            List<Contact> contacts = groupService.getContactsInGroup(groupName);
            List<ContactDTO> contactDTOs = contacts.stream()
                    .map(contactToDTO)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(contactDTOs, HttpStatus.OK);
        } catch (GroupNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

