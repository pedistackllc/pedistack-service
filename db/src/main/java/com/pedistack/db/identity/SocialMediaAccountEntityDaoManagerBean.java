package com.pedistack.db.identity;

import com.pedistack.common.exception.PedistackErrorDescriptions;
import com.pedistack.common.exception.PedistackException;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class SocialMediaAccountEntityDaoManagerBean implements SocialMediaAccountEntityDaoManager {

  private final SocialMediaAccountEntityDao socialMediaAccountEntityDao;

  public SocialMediaAccountEntityDaoManagerBean(
      SocialMediaAccountEntityDao socialMediaAccountEntityDao) {
    this.socialMediaAccountEntityDao = socialMediaAccountEntityDao;
  }

  @Override
  public Optional<SocialMediaAccountEntity> findByUserIdentifierReturnOptional(
      String userIdentifier) throws PedistackException {
    return socialMediaAccountEntityDao.findByUser_Id(userIdentifier);
  }

  @Override
  public SocialMediaAccountEntity findByUserIdentifier(
      String userIdentifier, Supplier<? extends PedistackException> supplier)
      throws PedistackException {
    return socialMediaAccountEntityDao.findByUser_Id(userIdentifier).orElseThrow(supplier);
  }

  @Override
  public SocialMediaAccountEntity findByUserIdentifier(String userIdentifier)
      throws PedistackException {
    return socialMediaAccountEntityDao
        .findByUser_Id(userIdentifier)
        .orElseThrow(
            () ->
                PedistackException.createInternalErrorException(
                    PedistackErrorDescriptions
                        .SOCIAL_MEDIA_INFORMATION_NOT_FOUND_ERROR_DESCRIPTION));
  }

  @Override
  public Optional<SocialMediaAccountEntity> findByEmailAddressReturnOptional(String emailAddress)
      throws PedistackException {
    return socialMediaAccountEntityDao.findByUser_EmailAddress(emailAddress);
  }

  @Override
  public SocialMediaAccountEntity findByEmailAddress(
      String emailAddress, Supplier<? extends PedistackException> supplier)
      throws PedistackException {
    return socialMediaAccountEntityDao.findByUser_EmailAddress(emailAddress).orElseThrow(supplier);
  }

  @Override
  public SocialMediaAccountEntity findByEmailAddress(String emailAddress)
      throws PedistackException {
    return socialMediaAccountEntityDao
        .findByUser_EmailAddress(emailAddress)
        .orElseThrow(
            () ->
                PedistackException.createInternalErrorException(
                    PedistackErrorDescriptions
                        .SOCIAL_MEDIA_INFORMATION_NOT_FOUND_ERROR_DESCRIPTION));
  }

  @Override
  public Optional<SocialMediaAccountEntity> findByMobileNumberReturnOptional(String mobileNumber)
      throws PedistackException {
    return socialMediaAccountEntityDao.findByUser_MobileNumber(mobileNumber);
  }

  @Override
  public SocialMediaAccountEntity findByMobileNumber(
      String mobileNumber, Supplier<? extends PedistackException> supplier)
      throws PedistackException {
    return socialMediaAccountEntityDao.findByUser_MobileNumber(mobileNumber).orElseThrow(supplier);
  }

  @Override
  public SocialMediaAccountEntity findByMobileNumber(String mobileNumber)
      throws PedistackException {
    return socialMediaAccountEntityDao
        .findByUser_MobileNumber(mobileNumber)
        .orElseThrow(
            () ->
                PedistackException.createInternalErrorException(
                    PedistackErrorDescriptions
                        .SOCIAL_MEDIA_INFORMATION_NOT_FOUND_ERROR_DESCRIPTION));
  }

  @Override
  public Optional<SocialMediaAccountEntity> findByUsernameReturnOptional(String username)
      throws PedistackException {
    return socialMediaAccountEntityDao.findByUser_Username(username);
  }

  @Override
  public SocialMediaAccountEntity findByUsername(
      String username, Supplier<? extends PedistackException> supplier) throws PedistackException {
    return socialMediaAccountEntityDao.findByUser_Username(username).orElseThrow(supplier);
  }

  @Override
  public SocialMediaAccountEntity findByUsername(String username) throws PedistackException {
    return socialMediaAccountEntityDao
        .findByUser_Username(username)
        .orElseThrow(
            () ->
                PedistackException.createInternalErrorException(
                    PedistackErrorDescriptions
                        .SOCIAL_MEDIA_INFORMATION_NOT_FOUND_ERROR_DESCRIPTION));
  }

  @Override
  public Optional<SocialMediaAccountEntity> findByClientIdReturnOptional(String clientId)
      throws PedistackException {
    return socialMediaAccountEntityDao.findByUser_ClientId(clientId);
  }

  @Override
  public SocialMediaAccountEntity findByClientId(
      String clientId, Supplier<? extends PedistackException> supplier) throws PedistackException {
    return socialMediaAccountEntityDao.findByUser_ClientId(clientId).orElseThrow(supplier);
  }

  @Override
  public SocialMediaAccountEntity findByClientId(String clientId) throws PedistackException {
    return socialMediaAccountEntityDao
        .findByUser_ClientId(clientId)
        .orElseThrow(
            () ->
                PedistackException.createInternalErrorException(
                    PedistackErrorDescriptions
                        .SOCIAL_MEDIA_INFORMATION_NOT_FOUND_ERROR_DESCRIPTION));
  }

  @Override
  public void checkExistingFacebookUsername(String facebookUsername) throws PedistackException {
    if (socialMediaAccountEntityDao.findByFacebookAccountUsername(facebookUsername).isPresent()) {
      throw PedistackException.createInternalErrorException(
          PedistackErrorDescriptions.FACEBOOK_USERNAME_ALREADY_REGISTERED_ERROR_DESCRIPTION);
    }
  }

  @Override
  public void checkExistingTwitterUsername(String twitterUsername) throws PedistackException {
    if (socialMediaAccountEntityDao.findByTwitterAccountUsername(twitterUsername).isPresent()) {
      throw PedistackException.createInternalErrorException(
          PedistackErrorDescriptions.TWITTER_USERNAME_ALREADY_REGISTERED_ERROR_DESCRIPTION);
    }
  }

  @Override
  public void checkExistingInstagramUsername(String instagramUsername) throws PedistackException {
    if (socialMediaAccountEntityDao.findByInstagramAccountUsername(instagramUsername).isPresent()) {
      throw PedistackException.createInternalErrorException(
          PedistackErrorDescriptions.IG_USERNAME_ALREADY_REGISTERED_ERROR_DESCRIPTION);
    }
  }

  @Override
  public void checkExistingMediumAccountUsername(String mediumAccountUsername)
      throws PedistackException {
    if (socialMediaAccountEntityDao
        .findByMediumAccountUsername(mediumAccountUsername)
        .isPresent()) {
      throw PedistackException.createInternalErrorException(
          PedistackErrorDescriptions.MEDIUM_USERNAME_ALREADY_REGISTERED_ERROR_DESCRIPTION);
    }
  }

  @Override
  public void checkExistingLinkedInAccountUrl(String linkedInAccountUrl) throws PedistackException {
    if (socialMediaAccountEntityDao.findByLinkedInAccountUrl(linkedInAccountUrl).isPresent()) {
      throw PedistackException.createInternalErrorException(
          PedistackErrorDescriptions.LINKEDIN_USERNAME_ALREADY_REGISTERED_ERROR_DESCRIPTION);
    }
  }

  @Override
  public SocialMediaAccountEntity save(SocialMediaAccountEntity socialMediaAccountEntity)
      throws PedistackException {
    return socialMediaAccountEntityDao.save(socialMediaAccountEntity);
  }

  @Override
  public List<SocialMediaAccountEntity> saveAll(
      List<SocialMediaAccountEntity> socialMediaAccountEntityList) {
    return socialMediaAccountEntityDao.saveAll(socialMediaAccountEntityList);
  }

  @Override
  public void delete(SocialMediaAccountEntity socialMediaAccountEntity) throws PedistackException {
    socialMediaAccountEntityDao.delete(socialMediaAccountEntity);
  }

  @Override
  public SocialMediaAccountEntity findByIdentifier(String identifier) throws PedistackException {
    return socialMediaAccountEntityDao
        .findById(identifier)
        .orElseThrow(
            () ->
                PedistackException.createInternalErrorException(
                    PedistackErrorDescriptions
                        .SOCIAL_MEDIA_INFORMATION_NOT_FOUND_ERROR_DESCRIPTION));
  }

  @Override
  public Page<SocialMediaAccountEntity> findAll(Pageable pageable) throws PedistackException {
    return socialMediaAccountEntityDao.findAll(pageable);
  }
}
