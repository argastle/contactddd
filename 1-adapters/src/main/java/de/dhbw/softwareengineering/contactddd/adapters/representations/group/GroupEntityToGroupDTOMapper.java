package de.dhbw.softwareengineering.contactddd.adapters.representations.group;

import de.dhbw.softwareengineering.contactddd.adapters.representations.contactid.ContactIdDTO;
import de.dhbw.softwareengineering.contactddd.adapters.representations.contactid.ContactIdEntityToContactIdDTOMapper;
import de.dhbw.softwareengineering.contactddd.domain.entities.Group;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class GroupEntityToGroupDTOMapper implements Function<Group, GroupDTO> {

    @Override
    public GroupDTO apply(final Group group) {
        return map(group);
    }

    public GroupDTO map(Group group) {
        Set<ContactIdDTO> contactIds = group.getContactIds()
                .stream()
                .map(new ContactIdEntityToContactIdDTOMapper()::map)
                .collect(Collectors.toSet());
        return new GroupDTO(group.getName(), contactIds);
    }
}
