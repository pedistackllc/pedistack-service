package com.pedistack.db.oauth;

import com.pedistack.common.exception.PedistackErrorDescriptions;
import com.pedistack.common.exception.PedistackException;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class UserEntityDaoManagerBean implements UserEntityDaoManager {

  private final UserEntityDao userEntityDao;

  public UserEntityDaoManagerBean(UserEntityDao userEntityDao) {
    this.userEntityDao = userEntityDao;
  }

  @Override
  public Optional<UserEntity> findByEmailAddressReturnOptional(String emailAddress)
      throws PedistackException {
    return userEntityDao.findByEmailAddress(emailAddress);
  }

  @Override
  public UserEntity findByEmailAddress(
      String emailAddress, Supplier<? extends PedistackException> supplier)
      throws PedistackException {
    return userEntityDao.findByEmailAddress(emailAddress).orElseThrow(supplier);
  }

  @Override
  public UserEntity findByEmailAddress(String emailAddress) throws PedistackException {
    return userEntityDao
        .findByEmailAddress(emailAddress)
        .orElseThrow(
            () ->
                PedistackException.createInternalErrorException(
                    PedistackErrorDescriptions.USER_NOT_FOUND));
  }

  @Override
  public Optional<UserEntity> findByMobileNumberReturnOptional(String mobileNumber)
      throws PedistackException {
    return userEntityDao.findByMobileNumber(mobileNumber);
  }

  @Override
  public UserEntity findByMobileNumber(
      String mobileNumber, Supplier<? extends PedistackException> supplier)
      throws PedistackException {
    return userEntityDao.findByMobileNumber(mobileNumber).orElseThrow(supplier);
  }

  @Override
  public UserEntity findByMobileNumber(String mobileNumber) throws PedistackException {
    return userEntityDao
        .findByMobileNumber(mobileNumber)
        .orElseThrow(
            () ->
                PedistackException.createInternalErrorException(
                    PedistackErrorDescriptions.USER_NOT_FOUND));
  }

  @Override
  public Optional<UserEntity> findByUsernameReturnOptional(String username)
      throws PedistackException {
    return userEntityDao.findByUsername(username);
  }

  @Override
  public UserEntity findByUsername(String username, Supplier<? extends PedistackException> supplier)
      throws PedistackException {
    return userEntityDao.findByUsername(username).orElseThrow(supplier);
  }

  @Override
  public UserEntity findByUsername(String username) throws PedistackException {
    return userEntityDao
        .findByUsername(username)
        .orElseThrow(
            () ->
                PedistackException.createInternalErrorException(
                    PedistackErrorDescriptions.USER_NOT_FOUND));
  }

  @Override
  public Optional<UserEntity> findByClientIdReturnOptional(String clientId)
      throws PedistackException {
    return userEntityDao.findByClientId(clientId);
  }

  @Override
  public UserEntity findByClientId(String clientId, Supplier<? extends PedistackException> supplier)
      throws PedistackException {
    return userEntityDao.findByClientId(clientId).orElseThrow(supplier);
  }

  @Override
  public UserEntity findByClientId(String clientId) throws PedistackException {
    return userEntityDao
        .findByClientId(clientId)
        .orElseThrow(
            () ->
                PedistackException.createInternalErrorException(
                    PedistackErrorDescriptions.USER_NOT_FOUND));
  }

  @Override
  public void checkExistingUsername(String username) throws PedistackException {
    if (userEntityDao.findByUsername(username).isPresent()) {
      throw PedistackException.createInternalErrorException(
          PedistackErrorDescriptions.USER_WITH_USERNAME_REGISTERED);
    }
  }

  @Override
  public void checkExistingEmailAddress(String emailAddress) throws PedistackException {
    if (userEntityDao.findByEmailAddress(emailAddress).isPresent()) {
      throw PedistackException.createInternalErrorException(
          PedistackErrorDescriptions.USER_WITH_EMAIL_ADDRESS_REGISTERED);
    }
  }

  @Override
  public void checkExistingMobileNumber(String mobileNumber) throws PedistackException {
    if (userEntityDao.findByMobileNumber(mobileNumber).isPresent()) {
      throw PedistackException.createInternalErrorException(
          PedistackErrorDescriptions.USER_WITH_MOBILE_NUMBER_REGISTERED);
    }
  }

  @Override
  public UserEntity save(UserEntity userEntity) throws PedistackException {
    return userEntityDao.save(userEntity);
  }

  @Override
  public List<UserEntity> saveAll(List<UserEntity> userEntities) {
    return userEntityDao.saveAll(userEntities);
  }

  @Override
  public void delete(UserEntity userEntity) throws PedistackException {
    userEntityDao.delete(userEntity);
  }

  @Override
  public UserEntity findByIdentifier(String identifier) throws PedistackException {
    return userEntityDao
        .findById(identifier)
        .orElseThrow(
            () ->
                PedistackException.createInternalErrorException(
                    PedistackErrorDescriptions.USER_NOT_FOUND));
  }

  @Override
  public Page<UserEntity> findAll(Pageable pageable) throws PedistackException {
    return userEntityDao.findAll(pageable);
  }
}
