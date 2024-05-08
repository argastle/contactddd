package de.dhbw.softwareengineering.contactddd.adapters.representations.mappers;

import de.dhbw.softwareengineering.contactddd.adapters.representations.dto.SocialMediaAccountDTO;
import de.dhbw.softwareengineering.contactddd.domain.values.SocialMediaAccount;

import java.util.function.Function;

public class SocialMediaAccountEntityToSocialMediaAccountDTOMapper implements Function<SocialMediaAccount, SocialMediaAccountDTO> {

    @Override
    public SocialMediaAccountDTO apply(SocialMediaAccount socialMediaAccount) {
        return map(socialMediaAccount);
    }

    public SocialMediaAccountDTO map(SocialMediaAccount socialMediaAccount) {
        if (socialMediaAccount == null) {
            return null;
        }
        return new SocialMediaAccountDTO(
                socialMediaAccount.accountName(),
                socialMediaAccount.platform(), socialMediaAccount.getUrl()
        );
    }
}