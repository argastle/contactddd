package de.dhbw.softwareengineering.contactddd.plugins.persistence;

import de.dhbw.softwareengineering.contactddd.domain.entities.Contact;
import de.dhbw.softwareengineering.contactddd.domain.repositories.IContactRepository;
import de.dhbw.softwareengineering.contactddd.domain.values.ContactId;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


//Muss das jeweilige Domain Repository implementieren
@Repository
public class ContactRepository implements IContactRepository {

	@Autowired
	DataContactRepository dataContactRepository;
  @Autowired
  public ContactRepository(DataContactRepository dataContactRepository) {
    this.dataContactRepository = dataContactRepository;
  }

	@Override
	public Optional<Contact> findById(ContactId id) {
		return Optional.empty();
	}

	@Override
	public Optional<Contact> findByName(String name) {
		return Optional.empty();
	}


/**
	 * In dem package landen die ganzen Bridges welche benutzt werden zur kommunikation
	 * zwischen den schichten.
	 */
}