package de.dhbw.softwareengineering.contactddd.plugins.rest;

import de.dhbw.softwareengineering.contactddd.adapters.representations.contact.ContactDTO;
import de.dhbw.softwareengineering.contactddd.adapters.representations.group.GroupDTO;
import de.dhbw.softwareengineering.contactddd.adapters.representations.contact.ContactEntityToContactDTOMapper;
import de.dhbw.softwareengineering.contactddd.adapters.representations.group.GroupEntityToGroupDTOMapper;
import de.dhbw.softwareengineering.contactddd.application.services.CreateGroupCommand;
import de.dhbw.softwareengineering.contactddd.application.services.GroupService;
import de.dhbw.softwareengineering.contactddd.domain.entities.Contact;
import de.dhbw.softwareengineering.contactddd.domain.entities.Group;
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
    public ResponseEntity<GroupDTO> createGroup(@RequestBody CreateGroupCommand command) {
        Group createdGroup = groupService.createGroup(command);
        return new ResponseEntity<>(groupToDTO.apply(createdGroup), HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteGroup(@RequestParam String groupName) {
        groupService.deleteGroup(groupName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/{contactId}/addGroup")
    public ResponseEntity<Void> addContactToGroup(@PathVariable String contactId, @RequestParam String groupName) {
        groupService.addContactToGroup(contactId, groupName);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/{contactId}/removeGroup")
    public ResponseEntity<Void> removeContactFromGroup(@PathVariable String contactId, @RequestParam String groupName) {
        groupService.removeContactFromGroup(contactId, groupName);
        return new ResponseEntity<>(HttpStatus.OK);
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
        List<Contact> contacts = groupService.getContactsInGroup(groupName);
        List<ContactDTO> contactDTOs = contacts.stream()
                .map(contactToDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(contactDTOs, HttpStatus.OK);
    }
}

