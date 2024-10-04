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
public class IdentityEntityDaoManagerBean implements IdentityEntityDaoManager {

  private final IdentityEntityDao identityEntityDao;

  public IdentityEntityDaoManagerBean(IdentityEntityDao identityEntityDao) {
    this.identityEntityDao = identityEntityDao;
  }

  @Override
  public Optional<IdentityEntity> findByMobileNumberReturnOptional(String mobileNumber)
      throws PedistackException {
    return identityEntityDao.findByUser_MobileNumber(mobileNumber);
  }

  @Override
  public IdentityEntity findByMobileNumber(
      String mobileNumber, Supplier<? extends PedistackException> supplier)
      throws PedistackException {
    return identityEntityDao.findByUser_MobileNumber(mobileNumber).orElseThrow(supplier);
  }

  @Override
  public IdentityEntity findByMobileNumber(String mobileNumber) throws PedistackException {
    return identityEntityDao
        .findByUser_MobileNumber(mobileNumber)
        .orElseThrow(
            () ->
                PedistackException.createInternalErrorException(
                    PedistackErrorDescriptions.IDENTITY_INFORMATION_NOT_FOUND_ERROR_DESCRIPTION));
  }

  @Override
  public Optional<IdentityEntity> findByEmailAddressReturnOptional(String emailAddress)
      throws PedistackException {
    return identityEntityDao.findByUser_EmailAddress(emailAddress);
  }

  @Override
  public IdentityEntity findByEmailAddress(
      String emailAddress, Supplier<? extends PedistackException> supplier)
      throws PedistackException {
    return identityEntityDao.findByUser_EmailAddress(emailAddress).orElseThrow(supplier);
  }

  @Override
  public IdentityEntity findByEmailAddress(String emailAddress) throws PedistackException {
    return identityEntityDao
        .findByUser_EmailAddress(emailAddress)
        .orElseThrow(
            () ->
                PedistackException.createInternalErrorException(
                    PedistackErrorDescriptions.IDENTITY_INFORMATION_NOT_FOUND_ERROR_DESCRIPTION));
  }

  @Override
  public Optional<IdentityEntity> findByUsernameReturnOptional(String username)
      throws PedistackException {
    return identityEntityDao.findByUser_Username(username);
  }

  @Override
  public IdentityEntity findByUsername(
      String username, Supplier<? extends PedistackException> supplier) throws PedistackException {
    return identityEntityDao.findByUser_Username(username).orElseThrow(supplier);
  }

  @Override
  public IdentityEntity findByUsername(String username) throws PedistackException {
    return identityEntityDao
        .findByUser_Username(username)
        .orElseThrow(
            () ->
                PedistackException.createInternalErrorException(
                    PedistackErrorDescriptions.IDENTITY_INFORMATION_NOT_FOUND_ERROR_DESCRIPTION));
  }

  @Override
  public Optional<IdentityEntity> findByClientIdReturnOptional(String clientId)
      throws PedistackException {
    return identityEntityDao.findByUser_ClientId(clientId);
  }

  @Override
  public IdentityEntity findByClientId(
      String clientId, Supplier<? extends PedistackException> supplier) throws PedistackException {
    return identityEntityDao.findByUser_ClientId(clientId).orElseThrow(supplier);
  }

  @Override
  public IdentityEntity findByClientId(String clientId) throws PedistackException {
    return identityEntityDao
        .findByUser_ClientId(clientId)
        .orElseThrow(
            () ->
                PedistackException.createInternalErrorException(
                    PedistackErrorDescriptions.IDENTITY_INFORMATION_NOT_FOUND_ERROR_DESCRIPTION));
  }

  @Override
  public Optional<IdentityEntity> findByInternalIdentityReturnOptional(String internalIdentity)
      throws PedistackException {
    return identityEntityDao.findByInternalIdentity(internalIdentity);
  }

  @Override
  public IdentityEntity findByInternalIdentity(
      String internalIdentity, Supplier<? extends PedistackException> supplier)
      throws PedistackException {
    return identityEntityDao.findByInternalIdentity(internalIdentity).orElseThrow(supplier);
  }

  @Override
  public IdentityEntity findByInternalIdentity(String internalIdentity) throws PedistackException {
    return identityEntityDao
        .findByInternalIdentity(internalIdentity)
        .orElseThrow(
            () ->
                PedistackException.createInternalErrorException(
                    PedistackErrorDescriptions.IDENTITY_INFORMATION_NOT_FOUND_ERROR_DESCRIPTION));
  }

  @Override
  public Optional<IdentityEntity> findByUserIdentifierReturnOptional(String userIdentifier)
      throws PedistackException {
    return identityEntityDao.findByUser_Id(userIdentifier);
  }

  @Override
  public IdentityEntity findByUserIdentifier(
      String userIdentifier, Supplier<? extends PedistackException> supplier)
      throws PedistackException {
    return identityEntityDao.findByUser_Id(userIdentifier).orElseThrow(supplier);
  }

  @Override
  public IdentityEntity findByUserIdentifier(String userIdentifier) throws PedistackException {
    return identityEntityDao
        .findByUser_Id(userIdentifier)
        .orElseThrow(
            () ->
                PedistackException.createInternalErrorException(
                    PedistackErrorDescriptions.IDENTITY_INFORMATION_NOT_FOUND_ERROR_DESCRIPTION));
  }

  @Override
  public IdentityEntity save(IdentityEntity identityEntity) throws PedistackException {
    return identityEntityDao.save(identityEntity);
  }

  @Override
  public List<IdentityEntity> saveAll(List<IdentityEntity> identityEntityList) {
    return identityEntityDao.saveAll(identityEntityList);
  }

  @Override
  public void delete(IdentityEntity identityEntity) throws PedistackException {
    identityEntityDao.delete(identityEntity);
  }

  @Override
  public IdentityEntity findByIdentifier(String identifier) throws PedistackException {
    return identityEntityDao
        .findById(identifier)
        .orElseThrow(
            () ->
                PedistackException.createInternalErrorException(
                    PedistackErrorDescriptions.IDENTITY_INFORMATION_NOT_FOUND_ERROR_DESCRIPTION));
  }

  @Override
  public Page<IdentityEntity> findAll(Pageable pageable) throws PedistackException {
    return identityEntityDao.findAll(pageable);
  }
}
