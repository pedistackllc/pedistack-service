package com.pedistack.db.identity;

import com.pedistack.common.db.BaseDaoManager;
import com.pedistack.common.exception.PedistackException;
import java.util.Optional;
import java.util.function.Supplier;

public interface SocialMediaAccountEntityDaoManager
    extends BaseDaoManager<SocialMediaAccountEntity, SocialMediaAccountEntityDao> {

  Optional<SocialMediaAccountEntity> findByUserIdentifierReturnOptional(String userIdentifier)
      throws PedistackException;

  SocialMediaAccountEntity findByUserIdentifier(
      String userIdentifier, Supplier<? extends PedistackException> supplier)
      throws PedistackException;

  SocialMediaAccountEntity findByUserIdentifier(String userIdentifier) throws PedistackException;

  Optional<SocialMediaAccountEntity> findByEmailAddressReturnOptional(String emailAddress)
      throws PedistackException;

  SocialMediaAccountEntity findByEmailAddress(
      String emailAddress, Supplier<? extends PedistackException> supplier)
      throws PedistackException;

  SocialMediaAccountEntity findByEmailAddress(String emailAddress) throws PedistackException;

  Optional<SocialMediaAccountEntity> findByMobileNumberReturnOptional(String mobileNumber)
      throws PedistackException;

  SocialMediaAccountEntity findByMobileNumber(
      String mobileNumber, Supplier<? extends PedistackException> supplier)
      throws PedistackException;

  SocialMediaAccountEntity findByMobileNumber(String mobileNumber) throws PedistackException;

  Optional<SocialMediaAccountEntity> findByUsernameReturnOptional(String username)
      throws PedistackException;

  SocialMediaAccountEntity findByUsername(
      String username, Supplier<? extends PedistackException> supplier) throws PedistackException;

  SocialMediaAccountEntity findByUsername(String username) throws PedistackException;

  Optional<SocialMediaAccountEntity> findByClientIdReturnOptional(String clientId)
      throws PedistackException;

  SocialMediaAccountEntity findByClientId(
      String clientId, Supplier<? extends PedistackException> supplier) throws PedistackException;

  SocialMediaAccountEntity findByClientId(String clientId) throws PedistackException;

  void checkExistingFacebookUsername(String facebookUsername) throws PedistackException;

  void checkExistingTwitterUsername(String twitterUsername) throws PedistackException;

  void checkExistingInstagramUsername(String instagramUsername) throws PedistackException;

  void checkExistingMediumAccountUsername(String mediumAccountUsername) throws PedistackException;

  void checkExistingLinkedInAccountUrl(String linkedInAccountUrl) throws PedistackException;
}
