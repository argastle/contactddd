package de.dhbw.softwareengineering.contactddd.adapters.representations.mappers;

import de.dhbw.softwareengineering.contactddd.adapters.representations.dto.SocialMediaAccountDTO;
import de.dhbw.softwareengineering.contactddd.domain.values.SocialMediaAccount;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class SocialMediaAccountEntitySetToSocialMediaAccountDTOSetMapper {

    public Set<SocialMediaAccountDTO> apply(Set<SocialMediaAccount> socialMediaAccounts) {
        return map(socialMediaAccounts);
    }

    public Set<SocialMediaAccountDTO> map(Set<SocialMediaAccount> socialMediaAccounts) {
        if (socialMediaAccounts == null) {
            return null;
        }
        return socialMediaAccounts.stream().map(new SocialMediaAccountEntityToSocialMediaAccountDTOMapper()).collect(Collectors.toSet());
    }
}