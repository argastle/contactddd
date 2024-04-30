package de.dhbw.softwareengineering.contactddd.plugins.persistence;
import de.dhbw.softwareengineering.contactddd.domain.entities.Contact;
import de.dhbw.softwareengineering.contactddd.domain.values.ContactId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import de.dhbw.softwareengineering.contactddd.domain.entities.EntityExample;
@Repository
public interface DataContactRepository extends MongoRepository<Contact, ContactId> {

}
