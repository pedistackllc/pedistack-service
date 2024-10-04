package com.pedistack.db.identity;

import com.pedistack.common.exception.PedistackErrorDescriptions;
import com.pedistack.common.exception.PedistackException;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class AddressEntityDaoManagerBean implements AddressEntityDaoManager {

  private final AddressEntityDao addressEntityDao;

  public AddressEntityDaoManagerBean(AddressEntityDao addressEntityDao) {
    this.addressEntityDao = addressEntityDao;
  }

  @Override
  public Page<AddressEntity> findByUserIdentifier(String userIdentifier, Pageable pageable) {
    return addressEntityDao.findByUser_Id(userIdentifier, pageable);
  }

  @Override
  public Page<AddressEntity> findByUserClientId(String clientId, Pageable pageable) {
    return addressEntityDao.findByUser_ClientId(clientId, pageable);
  }

  @Override
  public Page<AddressEntity> findByUserMobileNumber(String mobileNumber, Pageable pageable) {
    return addressEntityDao.findByUser_MobileNumber(mobileNumber, pageable);
  }

  @Override
  public Page<AddressEntity> findByUserUsername(String username, Pageable pageable) {
    return addressEntityDao.findByUser_Username(username, pageable);
  }

  @Override
  public Page<AddressEntity> findByUserEmailAddress(String emailAddress, Pageable pageable) {
    return addressEntityDao.findByUser_EmailAddress(emailAddress, pageable);
  }

  @Override
  public AddressEntity save(AddressEntity addressEntity) throws PedistackException {
    return addressEntityDao.save(addressEntity);
  }

  @Override
  public List<AddressEntity> saveAll(List<AddressEntity> addressEntityList) {
    return addressEntityDao.saveAll(addressEntityList);
  }

  @Override
  public void delete(AddressEntity addressEntity) throws PedistackException {
    addressEntityDao.delete(addressEntity);
  }

  @Override
  public AddressEntity findByIdentifier(String identifier) throws PedistackException {
    return addressEntityDao
        .findById(identifier)
        .orElseThrow(
            () ->
                PedistackException.createInternalErrorException(
                    PedistackErrorDescriptions.ADDRESS_INFORMATION_NOT_FOUND_ERROR_DESCRIPTION));
  }

  @Override
  public Page<AddressEntity> findAll(Pageable pageable) throws PedistackException {
    return addressEntityDao.findAll(pageable);
  }
}
