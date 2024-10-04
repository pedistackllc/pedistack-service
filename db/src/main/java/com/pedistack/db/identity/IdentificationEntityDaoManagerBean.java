package com.pedistack.db.identity;

import com.pedistack.common.exception.PedistackErrorDescriptions;
import com.pedistack.common.exception.PedistackException;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class IdentificationEntityDaoManagerBean implements IdentificationEntityDaoManager {

  private final IdentificationEntityDao identificationEntityDao;

  public IdentificationEntityDaoManagerBean(IdentificationEntityDao identificationEntityDao) {
    this.identificationEntityDao = identificationEntityDao;
  }

  @Override
  public IdentificationEntity save(IdentificationEntity identificationEntity)
      throws PedistackException {
    return identificationEntityDao.save(identificationEntity);
  }

  @Override
  public List<IdentificationEntity> saveAll(List<IdentificationEntity> identificationEntityList) {
    return identificationEntityDao.saveAll(identificationEntityList);
  }

  @Override
  public void delete(IdentificationEntity identificationEntity) throws PedistackException {
    identificationEntityDao.delete(identificationEntity);
  }

  @Override
  public IdentificationEntity findByIdentifier(String identifier) throws PedistackException {
    return identificationEntityDao
        .findById(identifier)
        .orElseThrow(
            () ->
                PedistackException.createInternalErrorException(
                    PedistackErrorDescriptions.IDENTIFICATION_NOT_FOUND));
  }

  @Override
  public Page<IdentificationEntity> findAll(Pageable pageable) throws PedistackException {
    return identificationEntityDao.findAll(pageable);
  }

  @Override
  public Page<IdentificationEntity> findByUserIdentifier(String userIdentifier, Pageable pageable) {
    return identificationEntityDao.findByUser_Id(userIdentifier, pageable);
  }

  @Override
  public Page<IdentificationEntity> findByUserMobileNumber(String mobileNumber, Pageable pageable) {
    return identificationEntityDao.findByUser_MobileNumber(mobileNumber, pageable);
  }

  @Override
  public Page<IdentificationEntity> findByUserEmailAddress(String emailAddress, Pageable pageable) {
    return identificationEntityDao.findByUser_EmailAddress(emailAddress, pageable);
  }

  @Override
  public Page<IdentificationEntity> findByUserUsername(String username, Pageable pageable) {
    return identificationEntityDao.findByUser_Username(username, pageable);
  }

  @Override
  public Page<IdentificationEntity> findByUserClientId(String clientId, Pageable pageable) {
    return identificationEntityDao.findByUser_ClientId(clientId, pageable);
  }

  @Override
  public Optional<IdentificationEntity>
      findByIdentificationNumberAndIdentificationTypeAndIssuingCountryCode(
          String identificationNumber, String identificationType, String issuingCountryCode)
          throws PedistackException {
    return identificationEntityDao.findByIdentificationNumberAndTypeAndIssuingCountryCode(
        identificationNumber, identificationType, issuingCountryCode);
  }

  @Override
  public void checkExistingIdentification(
      String identificationNumber, String identificationType, String issuingCountryCode)
      throws PedistackException {
    if (findByIdentificationNumberAndIdentificationTypeAndIssuingCountryCode(
            identificationNumber, identificationType, issuingCountryCode)
        .isPresent()) {
      throw PedistackException.createInternalErrorException(
          PedistackErrorDescriptions.IDENTIFICATION_NOT_FOUND);
    }
  }
}
