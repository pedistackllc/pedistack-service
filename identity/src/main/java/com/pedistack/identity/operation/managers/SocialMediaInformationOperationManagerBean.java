package com.pedistack.identity.operation.managers;

import com.pedistack.common.exception.PedistackException;
import com.pedistack.db.identity.SocialMediaAccountEntity;
import com.pedistack.db.identity.SocialMediaAccountEntityDaoManager;
import com.pedistack.db.oauth.UserEntityDaoManager;
import com.pedistack.identity.v1_0.common.SocialMedia;
import java.util.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class SocialMediaInformationOperationManagerBean
    implements SocialMediaInformationOperationManager {

  private final SocialMediaAccountEntityDaoManager socialMediaAccountEntityDaoManager;
  private final UserEntityDaoManager userEntityDaoManager;

  public SocialMediaInformationOperationManagerBean(
      SocialMediaAccountEntityDaoManager socialMediaAccountEntityDaoManager,
      UserEntityDaoManager userEntityDaoManager) {
    this.socialMediaAccountEntityDaoManager = socialMediaAccountEntityDaoManager;
    this.userEntityDaoManager = userEntityDaoManager;
  }

  @Override
  public SocialMediaAccountEntity addOrUpdateSocialMediaAccountInformation(
      String tenant,
      String sessionUserIdentifier,
      String sessionReference,
      String userIdentifier,
      String socialMediaAccountIdentifier,
      SocialMedia socialMedia)
      throws PedistackException {
    final SocialMediaAccountEntity socialMediaAccountEntity =
        Optional.ofNullable(socialMediaAccountEntityDaoManager.findByUserIdentifier(userIdentifier))
            .orElse(new SocialMediaAccountEntity());
    if (userIdentifier != null) {
      socialMediaAccountEntity.setUser(userEntityDaoManager.findByIdentifier(userIdentifier));
    }
    if (socialMedia.getFacebookAccountUsername() != null) {
      socialMediaAccountEntity.setFacebookAccountUsername(socialMedia.getFacebookAccountUsername());
    }
    if (socialMedia.getInstagramAccountUsername() != null) {
      socialMediaAccountEntity.setInstagramAccountUsername(
          socialMedia.getInstagramAccountUsername());
    }
    if (socialMedia.getLinkedInAccountUrl() != null) {
      socialMediaAccountEntity.setLinkedInAccountUrl(socialMedia.getLinkedInAccountUrl());
    }
    if (socialMedia.getMediumAccountUsername() != null) {
      socialMediaAccountEntity.setMediumAccountUsername(socialMedia.getMediumAccountUsername());
    }
    if (socialMedia.getTwitterAccountUsername() != null) {
      socialMediaAccountEntity.setTwitterAccountUsername(socialMedia.getTwitterAccountUsername());
    }
    return socialMediaAccountEntityDaoManager.save(socialMediaAccountEntity);
  }

  @Override
  public SocialMedia socialMediaInformationWithUsername(
      String tenant, String sessionUserIdentifier, String sessionReference, String username)
      throws PedistackException {
    return createSocialMediaResponse(socialMediaAccountEntityDaoManager.findByUsername(username));
  }

  @Override
  public SocialMedia socialMediaInformationWithClientId(
      String tenant, String sessionUserIdentifier, String sessionReference, String clientId)
      throws PedistackException {
    return createSocialMediaResponse(socialMediaAccountEntityDaoManager.findByClientId(clientId));
  }

  @Override
  public SocialMedia socialMediaInformationWithEmailAddress(
      String tenant, String sessionUserIdentifier, String sessionReference, String emailAddress)
      throws PedistackException {
    return createSocialMediaResponse(socialMediaAccountEntityDaoManager.findByEmailAddress(emailAddress));
  }

  @Override
  public SocialMedia socialMediaInformationWithMobileNumber(
      String tenant, String sessionUserIdentifier, String sessionReference, String mobileNumber)
      throws PedistackException {
    return createSocialMediaResponse(socialMediaAccountEntityDaoManager.findByMobileNumber(mobileNumber));
  }

  private SocialMedia createSocialMediaResponse(SocialMediaAccountEntity socialMediaAccountEntity){
    final SocialMedia socialMedia = new SocialMedia();
    BeanUtils.copyProperties(socialMediaAccountEntity,socialMedia);
    return socialMedia;
  }

}
