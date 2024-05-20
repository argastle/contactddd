package de.dhbw.softwareengineering.contactddd.adapters.representations.socialmediaaccount;

import de.dhbw.softwareengineering.contactddd.domain.values.SocialMediaAccount;

import java.util.function.Function;

public class SocialMediaAccountEntityToSocialMediaAccountDTOMapper implements Function<SocialMediaAccount, SocialMediaAccountDTO> {

    @Override
    public SocialMediaAccountDTO apply(SocialMediaAccount socialMediaAccount) {
        return map(socialMediaAccount);
    }

    public SocialMediaAccountDTO map(SocialMediaAccount socialMediaAccount) {
        return new SocialMediaAccountDTO(
                socialMediaAccount.getAccountName(),
                socialMediaAccount.getPlatform(),
                socialMediaAccount.getUrl()
        );
    }
}