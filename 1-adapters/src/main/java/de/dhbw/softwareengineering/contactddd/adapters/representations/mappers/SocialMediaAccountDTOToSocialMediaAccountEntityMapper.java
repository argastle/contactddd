package de.dhbw.softwareengineering.contactddd.adapters.representations.mappers;

import de.dhbw.softwareengineering.contactddd.adapters.representations.dto.ContactDTO;
import de.dhbw.softwareengineering.contactddd.adapters.representations.dto.SocialMediaAccountDTO;
import de.dhbw.softwareengineering.contactddd.domain.entities.Contact;
import de.dhbw.softwareengineering.contactddd.domain.values.SocialMediaAccount;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class SocialMediaAccountDTOToSocialMediaAccountEntityMapper implements Function<SocialMediaAccountDTO, SocialMediaAccount> {

    @Override
    public SocialMediaAccount apply(SocialMediaAccountDTO socialMediaAccountDTO) {
        return map(socialMediaAccountDTO);
    }

    public SocialMediaAccount map(SocialMediaAccountDTO socialMediaAccountDTO) {
        if (socialMediaAccountDTO == null) {
            return null;
        }
        return new SocialMediaAccount(
                socialMediaAccountDTO.getAccountName(),
                socialMediaAccountDTO.getPlatform()
        );
    }
}