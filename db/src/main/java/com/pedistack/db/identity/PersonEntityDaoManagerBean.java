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
public class PersonEntityDaoManagerBean implements PersonEntityDaoManager {

  private final PersonEntityDao personEntityDao;

  public PersonEntityDaoManagerBean(PersonEntityDao personEntityDao) {
    this.personEntityDao = personEntityDao;
  }

  @Override
  public PersonEntity save(PersonEntity personEntity) throws PedistackException {
    return personEntityDao.save(personEntity);
  }

  @Override
  public List<PersonEntity> saveAll(List<PersonEntity> personEntityList) {
    return personEntityDao.saveAll(personEntityList);
  }

  @Override
  public void delete(PersonEntity personEntity) throws PedistackException {
    personEntityDao.delete(personEntity);
  }

  @Override
  public PersonEntity findByIdentifier(String identifier) throws PedistackException {
    return personEntityDao
        .findById(identifier)
        .orElseThrow(
            () ->
                PedistackException.createInternalErrorException(
                    PedistackErrorDescriptions.PERSONAL_INFORMATION_NOT_FOUND_ERROR_DESCRIPTION));
  }

  @Override
  public Page<PersonEntity> findAll(Pageable pageable) throws PedistackException {
    return personEntityDao.findAll(pageable);
  }

  @Override
  public Optional<PersonEntity> findByMobileNumberReturnOptional(String mobileNumber)
      throws PedistackException {
    return personEntityDao.findByUser_MobileNumber(mobileNumber);
  }

  @Override
  public PersonEntity findByMobileNumber(
      String mobileNumber, Supplier<? extends PedistackException> supplier)
      throws PedistackException {
    return personEntityDao.findByUser_MobileNumber(mobileNumber).orElseThrow(supplier);
  }

  @Override
  public PersonEntity findByMobileNumber(String mobileNumber) throws PedistackException {
    return personEntityDao
        .findByUser_MobileNumber(mobileNumber)
        .orElseThrow(
            () ->
                PedistackException.createInternalErrorException(
                    PedistackErrorDescriptions.PERSONAL_INFORMATION_NOT_FOUND_ERROR_DESCRIPTION));
  }

  @Override
  public Optional<PersonEntity> findByEmailAddressReturnOptional(String emailAddress)
      throws PedistackException {
    return personEntityDao.findByUser_EmailAddress(emailAddress);
  }

  @Override
  public PersonEntity findByEmailAddress(
      String emailAddress, Supplier<? extends PedistackException> supplier)
      throws PedistackException {
    return personEntityDao.findByUser_EmailAddress(emailAddress).orElseThrow(supplier);
  }

  @Override
  public PersonEntity findByEmailAddress(String emailAddress) throws PedistackException {
    return personEntityDao
        .findByUser_EmailAddress(emailAddress)
        .orElseThrow(
            () ->
                PedistackException.createInternalErrorException(
                    PedistackErrorDescriptions.PERSONAL_INFORMATION_NOT_FOUND_ERROR_DESCRIPTION));
  }

  @Override
  public Optional<PersonEntity> findByUsernameReturnOptional(String username)
      throws PedistackException {
    return personEntityDao.findByUser_Username(username);
  }

  @Override
  public PersonEntity findByUsername(
      String username, Supplier<? extends PedistackException> supplier) throws PedistackException {
    return personEntityDao.findByUser_Username(username).orElseThrow(supplier);
  }

  @Override
  public PersonEntity findByUsername(String username) throws PedistackException {
    return personEntityDao
        .findByUser_Username(username)
        .orElseThrow(
            () ->
                PedistackException.createInternalErrorException(
                    PedistackErrorDescriptions.PERSONAL_INFORMATION_NOT_FOUND_ERROR_DESCRIPTION));
  }

  @Override
  public Optional<PersonEntity> findByClientIdReturnOptional(String clientId)
      throws PedistackException {
    return personEntityDao.findByUser_ClientId(clientId);
  }

  @Override
  public PersonEntity findByClientId(
      String clientId, Supplier<? extends PedistackException> supplier) throws PedistackException {
    return personEntityDao.findByUser_ClientId(clientId).orElseThrow(supplier);
  }

  @Override
  public PersonEntity findByClientId(String clientId) throws PedistackException {
    return personEntityDao
        .findByUser_ClientId(clientId)
        .orElseThrow(
            () ->
                PedistackException.createInternalErrorException(
                    PedistackErrorDescriptions.PERSONAL_INFORMATION_NOT_FOUND_ERROR_DESCRIPTION));
  }

  @Override
  public Optional<PersonEntity> findByUserIdentifierReturnOptional(String userIdentifier)
      throws PedistackException {
    return personEntityDao.findByUser_Id(userIdentifier);
  }

  @Override
  public PersonEntity findByUserIdentifier(
      String userIdentifier, Supplier<? extends PedistackException> supplier)
      throws PedistackException {
    return personEntityDao.findByUser_Id(userIdentifier).orElseThrow(supplier);
  }

  @Override
  public PersonEntity findByUserIdentifier(String userIdentifier) throws PedistackException {
    return personEntityDao
        .findByUser_Id(userIdentifier)
        .orElseThrow(
            () ->
                PedistackException.createInternalErrorException(
                    PedistackErrorDescriptions.PERSONAL_INFORMATION_NOT_FOUND_ERROR_DESCRIPTION));
  }
}
