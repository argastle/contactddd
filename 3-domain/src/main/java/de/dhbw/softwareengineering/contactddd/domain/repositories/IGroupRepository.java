package de.dhbw.softwareengineering.contactddd.domain.repositories;

import de.dhbw.softwareengineering.contactddd.domain.entities.Group;

import java.util.List;
import java.util.Optional;

public interface IGroupRepository {

    Optional<Group> findById(String Name);

    List<Group> getAllGroups();

    Group save(Group group);

    void deleteById(String name);

}
