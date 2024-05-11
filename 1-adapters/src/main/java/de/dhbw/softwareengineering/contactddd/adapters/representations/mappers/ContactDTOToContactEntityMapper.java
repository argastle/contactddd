package de.dhbw.softwareengineering.contactddd.adapters.representations.mappers;

import de.dhbw.softwareengineering.contactddd.adapters.representations.dto.ContactDTO;
import de.dhbw.softwareengineering.contactddd.domain.entities.Contact;
import de.dhbw.softwareengineering.contactddd.domain.values.ContactId;
import de.dhbw.softwareengineering.contactddd.domain.values.SocialMediaAccount;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class ContactDTOToContactEntityMapper implements Function<ContactDTO, Contact> {

    @Override
    public Contact apply(ContactDTO contactDTO) {
        return map(contactDTO);
    }

    public Contact map(ContactDTO contactDTO) {
        Set<SocialMediaAccount> socialMediaAccounts = contactDTO.socialMediaAccounts()
                .stream()
                .map(new SocialMediaAccountDTOToSocialMediaAccountEntityMapper()::map)
                .collect(Collectors.toSet());
        return new Contact(new ContactId(contactDTO.id()), contactDTO.name(), contactDTO.email(), contactDTO.phoneNumber(), socialMediaAccounts);
    }
}