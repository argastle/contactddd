package de.dhbw.softwareengineering.contactddd.plugins.persistence;

import de.dhbw.softwareengineering.contactddd.domain.entities.Contact;
import de.dhbw.softwareengineering.contactddd.domain.values.ContactId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DataContactRepository extends MongoRepository<Contact, ContactId> {

    Optional<List<Contact>> findByName(String name);

    @Query("{'groups': {$in: ?0}}")
    List<Contact> findByAnyGroup(List<String> group);

    @Query("{'groups': {$all: ?0}}")
    List<Contact> findByAllGroups(List<String> groups);
}
