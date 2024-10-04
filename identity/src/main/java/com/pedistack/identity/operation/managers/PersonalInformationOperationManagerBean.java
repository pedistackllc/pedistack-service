package com.pedistack.identity.operation.managers;

import com.pedistack.common.exception.PedistackErrorDescriptions;
import com.pedistack.common.exception.PedistackException;
import com.pedistack.db.identity.PersonEntity;
import com.pedistack.db.identity.PersonEntityDaoManager;
import com.pedistack.db.oauth.UserEntity;
import com.pedistack.db.oauth.UserEntityDaoManager;
import com.pedistack.identity.v1_0.common.GenderCode;
import com.pedistack.identity.v1_0.common.MaritalStatus;
import com.pedistack.identity.v1_0.common.Person;
import com.pedistack.v1_0.common.CountryCode;
import com.pedistack.v1_0.common.LanguageCode;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class PersonalInformationOperationManagerBean
    implements PersonalInformationOperationManager {

  private final PersonEntityDaoManager personEntityDaoManager;
  private final UserEntityDaoManager userEntityDaoManager;

  public PersonalInformationOperationManagerBean(
      PersonEntityDaoManager personEntityDaoManager, UserEntityDaoManager userEntityDaoManager) {
    this.personEntityDaoManager = personEntityDaoManager;
    this.userEntityDaoManager = userEntityDaoManager;
  }

  @Override
  public PersonEntity addOrUpdatePersonalInformation(
      String tenant,
      String sessionUserIdentifier,
      String sessionReference,
      String userIdentifier,
      Person person)
      throws PedistackException {
    final UserEntity userEntity = userEntityDaoManager.findByIdentifier(userIdentifier);
    final PersonEntity personEntity =
        personEntityDaoManager
            .findByUserIdentifierReturnOptional(userIdentifier)
            .orElse(new PersonEntity());
    personEntity.setUser(userEntity);
    if (person.getMiddleName() != null) {
      personEntity.setMiddleName(person.getMiddleName());
    }
    if (person.getBirthDate() != null) {
      personEntity.setBirthDate(person.getBirthDate());
    }
    if (person.getProfession() != null) {
      personEntity.setProfession(person.getProfession());
    }
    if (person.getSuffix() != null) {
      personEntity.setSuffix(person.getSuffix());
    }
    if (person.getLanguageCode() != null) {
      personEntity.setLanguageCode(person.getLanguageCode().name());
    }
    if (person.getGender() != null) {
      personEntity.setGender(person.getGender().name());
    }
    if (person.getMaidenName() != null) {
      personEntity.setMaidenName(person.getMaidenName());
    }
    if (person.getFirstName() != null) {
      personEntity.setFirstName(person.getFirstName());
    }
    if (person.getLastName() != null) {
      personEntity.setLastName(person.getLastName());
    }
    if (person.getEmployingCompany() != null) {
      personEntity.setEmployingCompany(person.getEmployingCompany());
    }
    if (person.getBirthProvince() != null) {
      personEntity.setBirthProvince(person.getBirthProvince());
    }
    if (person.getOtherNames() != null) {
      personEntity.setOtherNames(person.getOtherNames());
    }
    if (person.getTitle() != null) {
      personEntity.setTitle(person.getTitle());
    }
    if (person.getBirthCountryCode() != null) {
      personEntity.setBirthCountryCode(person.getBirthCountryCode().name());
    }
    if (person.getBusinessFunction() != null) {
      personEntity.setBusinessFunction(person.getBusinessFunction());
    }
    if (person.getTaxationCountryCode() != null) {
      personEntity.setTaxationCountryCode(person.getTaxationCountryCode().name());
    }
    return personEntityDaoManager.save(personEntity);
  }

  @Override
  public Person personalInformation(
      String tenant, String sessionUserIdentifier, String sessionReference, String userIdentifier)
      throws PedistackException {
    final PersonEntity personEntity = personEntityDaoManager.findByUserIdentifier(userIdentifier);
    if (!personEntity.getUser().getId().equals(sessionUserIdentifier)) {
      throw PedistackException.createInternalErrorException(
          PedistackErrorDescriptions.UNAUTHORIZED_ACCESS_ERROR_DESCRIPTION);
    }
    return createPersonResponse(personEntity);
  }

  @Override
  public Person personalInformationWithMsisdn(
      String tenant, String sessionUserIdentifier, String sessionReference, String userMsisdn)
      throws PedistackException {
    final PersonEntity personEntity = personEntityDaoManager.findByMobileNumber(userMsisdn);
    if (!personEntity.getUser().getId().equals(sessionUserIdentifier)) {
      throw PedistackException.createInternalErrorException(
          PedistackErrorDescriptions.UNAUTHORIZED_ACCESS_ERROR_DESCRIPTION);
    }
    return createPersonResponse(personEntity);
  }

  @Override
  public Person personalInformationWithEmailAddress(
      String tenant, String sessionUserIdentifier, String sessionReference, String userEmailAddress)
      throws PedistackException {
    final PersonEntity personEntity = personEntityDaoManager.findByEmailAddress(userEmailAddress);
    if (!personEntity.getUser().getId().equals(sessionUserIdentifier)) {
      throw PedistackException.createInternalErrorException(
          PedistackErrorDescriptions.UNAUTHORIZED_ACCESS_ERROR_DESCRIPTION);
    }
    return createPersonResponse(personEntity);
  }

  @Override
  public Person personalInformationWithUsername(
      String tenant, String sessionUserIdentifier, String sessionReference, String username)
      throws PedistackException {
    final PersonEntity personEntity = personEntityDaoManager.findByUsername(username);
    if (!personEntity.getUser().getId().equals(sessionUserIdentifier)) {
      throw PedistackException.createInternalErrorException(
          PedistackErrorDescriptions.UNAUTHORIZED_ACCESS_ERROR_DESCRIPTION);
    }
    return createPersonResponse(personEntity);
  }

  @Override
  public Person personalInformationWithClientId(
      String tenant, String sessionUserIdentifier, String sessionReference, String clientId)
      throws PedistackException {
    final PersonEntity personEntity = personEntityDaoManager.findByClientId(clientId);
    if (!personEntity.getUser().getId().equals(sessionUserIdentifier)) {
      throw PedistackException.createInternalErrorException(
              PedistackErrorDescriptions.UNAUTHORIZED_ACCESS_ERROR_DESCRIPTION);
    }
    return createPersonResponse(personEntity);
  }

  private Person createPersonResponse(final PersonEntity personEntity) {
    final Person person = new Person();
    BeanUtils.copyProperties(personEntity, person);
    if (personEntity.getMaritalStatus() != null) {
      person.setMaritalStatus(MaritalStatus.valueOf(personEntity.getMaritalStatus()));
    }
    if (personEntity.getLanguageCode() != null) {
      person.setLanguageCode(LanguageCode.valueOf(personEntity.getLanguageCode()));
    }
    if (personEntity.getGender() != null) {
      person.setGender(GenderCode.valueOf(personEntity.getGender()));
    }
    if (personEntity.getTaxationCountryCode() != null) {
      person.setTaxationCountryCode(CountryCode.valueOf(personEntity.getTaxationCountryCode()));
    }
    if (personEntity.getBirthCountryCode() != null) {
      person.setBirthCountryCode(CountryCode.valueOf(personEntity.getBirthCountryCode()));
    }
    return person;
  }
}
