package com.pedistack.db.identity;

import com.pedistack.common.db.BaseDaoManager;
import com.pedistack.common.exception.PedistackException;
import java.util.Optional;
import java.util.function.Supplier;

public interface DeveloperInformationEntityDaoManager
    extends BaseDaoManager<DeveloperInformationEntity, DeveloperInformationEntityDao> {

  Optional<DeveloperInformationEntity> findByUserIdentifierReturnOptional(String userIdentifier)
      throws PedistackException;

  DeveloperInformationEntity findByUserIdentifier(
      String userIdentifier, Supplier<? extends PedistackException> supplier)
      throws PedistackException;

  DeveloperInformationEntity findByUserIdentifier(String userIdentifier) throws PedistackException;

  Optional<DeveloperInformationEntity> findByUserMobileNumberReturnOptional(String mobileNumber)
      throws PedistackException;

  DeveloperInformationEntity findByUserMobileNumber(
      String mobileNumber, Supplier<? extends PedistackException> supplier)
      throws PedistackException;

  DeveloperInformationEntity findByUserMobileNumber(String mobileNumber) throws PedistackException;

  Optional<DeveloperInformationEntity> findByUserEmailAddressReturnOptional(String emailAddress)
      throws PedistackException;

  DeveloperInformationEntity findByUserEmailAddress(
      String emailAddress, Supplier<? extends PedistackException> supplier)
      throws PedistackException;

  DeveloperInformationEntity findByUserEmailAddress(String emailAddress) throws PedistackException;

  Optional<DeveloperInformationEntity> findByUserUsernameReturnOptional(String username)
      throws PedistackException;

  DeveloperInformationEntity findByUserUsername(
      String username, Supplier<? extends PedistackException> supplier) throws PedistackException;

  DeveloperInformationEntity findByUserUsername(String username) throws PedistackException;

  Optional<DeveloperInformationEntity> findByUserClientIdReturnOptional(String clientId)
      throws PedistackException;

  DeveloperInformationEntity findByUserClientId(
      String clientId, Supplier<? extends PedistackException> supplier) throws PedistackException;

  DeveloperInformationEntity findByUserClientId(String clientId) throws PedistackException;
}
