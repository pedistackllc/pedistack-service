package com.pedistack.identity.operation.managers;

import com.pedistack.common.exception.PedistackException;
import com.pedistack.db.identity.SocialMediaAccountEntity;
import com.pedistack.identity.v1_0.common.SocialMedia;

public interface SocialMediaInformationOperationManager {

  SocialMediaAccountEntity addOrUpdateSocialMediaAccountInformation(
      String tenant,
      String sessionUserIdentifier,
      String sessionReference,
      String userIdentifier,
      String socialMediaAccountIdentifier,
      SocialMedia socialMedia)
      throws PedistackException;

  SocialMedia socialMediaInformationWithUsername(
      String tenant, String sessionUserIdentifier, String sessionReference, String username)
      throws PedistackException;

  SocialMedia socialMediaInformationWithClientId(
      String tenant, String sessionUserIdentifier, String sessionReference, String clientId)
      throws PedistackException;

  SocialMedia socialMediaInformationWithEmailAddress(
      String tenant, String sessionUserIdentifier, String sessionReference, String emailAddress)
      throws PedistackException;

  SocialMedia socialMediaInformationWithMobileNumber(
      String tenant, String sessionUserIdentifier, String sessionReference, String mobileNumber)
      throws PedistackException;
}
