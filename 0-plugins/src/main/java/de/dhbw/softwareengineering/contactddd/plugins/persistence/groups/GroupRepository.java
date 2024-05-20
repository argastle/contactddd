package de.dhbw.softwareengineering.contactddd.plugins.persistence.groups;

import de.dhbw.softwareengineering.contactddd.domain.entities.Group;
import de.dhbw.softwareengineering.contactddd.domain.repositories.IGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class GroupRepository implements IGroupRepository {

    DataGroupRepository dataGroupRepository;

    @Autowired
    public GroupRepository(DataGroupRepository dataGroupRepository) {
        this.dataGroupRepository = dataGroupRepository;
    }

    @Override
    public Optional<Group> findById(String name) {
        return dataGroupRepository.findById(name);
    }

    @Override
    public List<Group> getAllGroups() {
        return dataGroupRepository.findAll();
    }

    @Override
    public Group save(Group group) {
        return dataGroupRepository.save(group);
    }

    @Override
    public void deleteById(String name) {
        dataGroupRepository.deleteById(name);
    }
}
