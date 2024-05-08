package de.dhbw.softwareengineering.contactddd.adapters.representations.mappers;

import de.dhbw.softwareengineering.contactddd.adapters.representations.dto.SocialMediaAccountDTO;
import de.dhbw.softwareengineering.contactddd.domain.values.SocialMediaAccount;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class SocialMediaAccountDTOSetToSocialMediaAccountEntitySetMapper {

    public Set<SocialMediaAccount> apply(Set<SocialMediaAccountDTO> socialMediaAccountDTOs) {
        return map(socialMediaAccountDTOs);
    }

    public Set<SocialMediaAccount> map(Set<SocialMediaAccountDTO> socialMediaAccountDTOs) {
        if (socialMediaAccountDTOs == null) {
            return null;
        }
        return socialMediaAccountDTOs.stream().map(new SocialMediaAccountDTOToSocialMediaAccountEntityMapper()).collect(Collectors.toSet());
    }
}