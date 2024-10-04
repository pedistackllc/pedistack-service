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
public class DeveloperInformationEntityDaoManagerBean
    implements DeveloperInformationEntityDaoManager {

  private final DeveloperInformationEntityDao developerInformationEntityDao;

  public DeveloperInformationEntityDaoManagerBean(
      DeveloperInformationEntityDao developerInformationEntityDao) {
    this.developerInformationEntityDao = developerInformationEntityDao;
  }

  @Override
  public Optional<DeveloperInformationEntity> findByUserIdentifierReturnOptional(
      String userIdentifier) throws PedistackException {
    return developerInformationEntityDao.findByUser_Id(userIdentifier);
  }

  @Override
  public DeveloperInformationEntity findByUserIdentifier(
      String userIdentifier, Supplier<? extends PedistackException> supplier)
      throws PedistackException {
    return developerInformationEntityDao.findByUser_Id(userIdentifier).orElseThrow(supplier);
  }

  @Override
  public DeveloperInformationEntity findByUserIdentifier(String userIdentifier)
      throws PedistackException {
    return developerInformationEntityDao
        .findByUser_Id(userIdentifier)
        .orElseThrow(
            () ->
                PedistackException.createInternalErrorException(
                    PedistackErrorDescriptions.DEVELOPER_INFORMATION_NOT_FOUND_ERROR_DESCRIPTION));
  }

  @Override
  public Optional<DeveloperInformationEntity> findByUserMobileNumberReturnOptional(
      String mobileNumber) throws PedistackException {
    return developerInformationEntityDao.findByUser_MobileNumber(mobileNumber);
  }

  @Override
  public DeveloperInformationEntity findByUserMobileNumber(
      String mobileNumber, Supplier<? extends PedistackException> supplier)
      throws PedistackException {
    return developerInformationEntityDao
        .findByUser_MobileNumber(mobileNumber)
        .orElseThrow(supplier);
  }

  @Override
  public DeveloperInformationEntity findByUserMobileNumber(String mobileNumber)
      throws PedistackException {
    return developerInformationEntityDao
        .findByUser_MobileNumber(mobileNumber)
        .orElseThrow(
            () ->
                PedistackException.createInternalErrorException(
                    PedistackErrorDescriptions.DEVELOPER_INFORMATION_NOT_FOUND_ERROR_DESCRIPTION));
  }

  @Override
  public Optional<DeveloperInformationEntity> findByUserEmailAddressReturnOptional(
      String emailAddress) throws PedistackException {
    return developerInformationEntityDao.findByUser_EmailAddress(emailAddress);
  }

  @Override
  public DeveloperInformationEntity findByUserEmailAddress(
      String emailAddress, Supplier<? extends PedistackException> supplier)
      throws PedistackException {
    return developerInformationEntityDao
        .findByUser_EmailAddress(emailAddress)
        .orElseThrow(supplier);
  }

  @Override
  public DeveloperInformationEntity findByUserEmailAddress(String emailAddress)
      throws PedistackException {
    return developerInformationEntityDao
        .findByUser_EmailAddress(emailAddress)
        .orElseThrow(
            () ->
                PedistackException.createInternalErrorException(
                    PedistackErrorDescriptions.DEVELOPER_INFORMATION_NOT_FOUND_ERROR_DESCRIPTION));
  }

  @Override
  public Optional<DeveloperInformationEntity> findByUserUsernameReturnOptional(String username)
      throws PedistackException {
    return developerInformationEntityDao.findByUser_Username(username);
  }

  @Override
  public DeveloperInformationEntity findByUserUsername(
      String username, Supplier<? extends PedistackException> supplier) throws PedistackException {
    return developerInformationEntityDao.findByUser_Username(username).orElseThrow(supplier);
  }

  @Override
  public DeveloperInformationEntity findByUserUsername(String username) throws PedistackException {
    return developerInformationEntityDao
        .findByUser_Username(username)
        .orElseThrow(
            () ->
                PedistackException.createInternalErrorException(
                    PedistackErrorDescriptions.DEVELOPER_INFORMATION_NOT_FOUND_ERROR_DESCRIPTION));
  }

  @Override
  public Optional<DeveloperInformationEntity> findByUserClientIdReturnOptional(String clientId)
      throws PedistackException {
    return developerInformationEntityDao.findByUser_ClientId(clientId);
  }

  @Override
  public DeveloperInformationEntity findByUserClientId(
      String clientId, Supplier<? extends PedistackException> supplier) throws PedistackException {
    return developerInformationEntityDao.findByUser_ClientId(clientId).orElseThrow(supplier);
  }

  @Override
  public DeveloperInformationEntity findByUserClientId(String clientId) throws PedistackException {
    return developerInformationEntityDao
        .findByUser_ClientId(clientId)
        .orElseThrow(
            () ->
                PedistackException.createInternalErrorException(
                    PedistackErrorDescriptions.DEVELOPER_INFORMATION_NOT_FOUND_ERROR_DESCRIPTION));
  }

  @Override
  public DeveloperInformationEntity save(DeveloperInformationEntity developerInformationEntity)
      throws PedistackException {
    return developerInformationEntityDao.save(developerInformationEntity);
  }

  @Override
  public List<DeveloperInformationEntity> saveAll(
      List<DeveloperInformationEntity> developerInformationEntityList) {
    return developerInformationEntityDao.saveAll(developerInformationEntityList);
  }

  @Override
  public void delete(DeveloperInformationEntity developerInformationEntity)
      throws PedistackException {
    developerInformationEntityDao.delete(developerInformationEntity);
  }

  @Override
  public DeveloperInformationEntity findByIdentifier(String identifier) throws PedistackException {
    return developerInformationEntityDao
        .findById(identifier)
        .orElseThrow(
            () ->
                PedistackException.createInternalErrorException(
                    PedistackErrorDescriptions.DEVELOPER_INFORMATION_NOT_FOUND_ERROR_DESCRIPTION));
  }

  @Override
  public Page<DeveloperInformationEntity> findAll(Pageable pageable) throws PedistackException {
    return developerInformationEntityDao.findAll(pageable);
  }
}
