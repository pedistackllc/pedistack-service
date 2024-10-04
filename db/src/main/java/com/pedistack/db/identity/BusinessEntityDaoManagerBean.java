package com.pedistack.db.identity;

import com.pedistack.common.exception.PedistackErrorDescriptions;
import com.pedistack.common.exception.PedistackException;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class BusinessEntityDaoManagerBean implements BusinessEntityDaoManager {

  private final BusinessEntityDao businessEntityDao;

  public BusinessEntityDaoManagerBean(BusinessEntityDao businessEntityDao) {
    this.businessEntityDao = businessEntityDao;
  }

  @Override
  public BusinessEntity save(BusinessEntity businessEntity) throws PedistackException {
    return businessEntityDao.save(businessEntity);
  }

  @Override
  public List<BusinessEntity> saveAll(List<BusinessEntity> businessEntityList) {
    return businessEntityDao.saveAll(businessEntityList);
  }

  @Override
  public void delete(BusinessEntity businessEntity) throws PedistackException {
    businessEntityDao.delete(businessEntity);
  }

  @Override
  public BusinessEntity findByIdentifier(String identifier) throws PedistackException {
    return businessEntityDao
        .findById(identifier)
        .orElseThrow(
            () ->
                PedistackException.createInternalErrorException(
                    PedistackErrorDescriptions.BUSINESS_INFORMATION_NOT_FOUND_ERROR_DESCRIPTION));
  }

  @Override
  public Page<BusinessEntity> findAll(Pageable pageable) throws PedistackException {
    return businessEntityDao.findAll(pageable);
  }

  @Override
  public Page<BusinessEntity> findByUserIdentifier(String userIdentifier, Pageable pageable) {
    return businessEntityDao.findByUser_Id(userIdentifier, pageable);
  }

  @Override
  public Page<BusinessEntity> findByMobileNumber(String mobileNumber, Pageable pageable) {
    return businessEntityDao.findByUser_MobileNumber(mobileNumber, pageable);
  }

  @Override
  public Page<BusinessEntity> findByEmailAddress(String emailAddress, Pageable pageable) {
    return businessEntityDao.findByUser_EmailAddress(emailAddress, pageable);
  }

  @Override
  public Page<BusinessEntity> findByUsername(String username, Pageable pageable) {
    return businessEntityDao.findByUser_Username(username, pageable);
  }

  @Override
  public Page<BusinessEntity> findByClientId(String clientId, Pageable pageable) {
    return businessEntityDao.findByUser_ClientId(clientId, pageable);
  }

  @Override
  public void checkExistingBusinessInformation(
      String name, String tradingName, String registrationNumber) throws PedistackException {
    if (name != null) {
      if (businessEntityDao.findByName(name).isPresent()) {
        throw PedistackException.createInternalErrorException(
            PedistackErrorDescriptions.BUSINESS_INFORMATION_ALREADY_REGISTERED_ERROR_DESCRIPTION);
      }
    }
    if (tradingName != null) {
      if (businessEntityDao.findByTradingName(tradingName).isPresent()) {
        throw PedistackException.createInternalErrorException(
            PedistackErrorDescriptions.BUSINESS_INFORMATION_ALREADY_REGISTERED_ERROR_DESCRIPTION);
      }
    }
    if (registrationNumber != null) {
      if (businessEntityDao.findByRegistrationNumber(registrationNumber).isPresent()) {
        throw PedistackException.createInternalErrorException(
            PedistackErrorDescriptions.BUSINESS_INFORMATION_ALREADY_REGISTERED_ERROR_DESCRIPTION);
      }
    }
  }
}
