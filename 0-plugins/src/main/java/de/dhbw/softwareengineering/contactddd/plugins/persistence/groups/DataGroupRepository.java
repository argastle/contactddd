package de.dhbw.softwareengineering.contactddd.plugins.persistence.groups;

import de.dhbw.softwareengineering.contactddd.domain.entities.Contact;
import de.dhbw.softwareengineering.contactddd.domain.entities.Group;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface DataGroupRepository extends MongoRepository<Group,String> {

    @Query("{'groups': {$in: ?0}}")
    List<Contact> findByAnyGroup(List<String> group);

    @Query("{'groups': {$all: ?0}}")
    List<Contact> findByAllGroups(List<String> groups);
}
