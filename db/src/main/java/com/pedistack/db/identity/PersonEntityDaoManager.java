package com.pedistack.db.identity;

import com.pedistack.common.db.BaseDaoManager;
import com.pedistack.common.exception.PedistackException;
import java.util.Optional;
import java.util.function.Supplier;

public interface PersonEntityDaoManager extends BaseDaoManager<PersonEntity, PersonEntityDao> {

  Optional<PersonEntity> findByMobileNumberReturnOptional(String mobileNumber)
      throws PedistackException;

  PersonEntity findByMobileNumber(
      String mobileNumber, Supplier<? extends PedistackException> supplier)
      throws PedistackException;

  PersonEntity findByMobileNumber(String mobileNumber) throws PedistackException;

  Optional<PersonEntity> findByEmailAddressReturnOptional(String emailAddress)
      throws PedistackException;

  PersonEntity findByEmailAddress(
      String emailAddress, Supplier<? extends PedistackException> supplier)
      throws PedistackException;

  PersonEntity findByEmailAddress(String emailAddress) throws PedistackException;

  Optional<PersonEntity> findByUsernameReturnOptional(String username) throws PedistackException;

  PersonEntity findByUsername(String username, Supplier<? extends PedistackException> supplier)
      throws PedistackException;

  PersonEntity findByUsername(String username) throws PedistackException;

  Optional<PersonEntity> findByClientIdReturnOptional(String clientId) throws PedistackException;

  PersonEntity findByClientId(String clientId, Supplier<? extends PedistackException> supplier)
      throws PedistackException;

  PersonEntity findByClientId(String clientId) throws PedistackException;

  Optional<PersonEntity> findByUserIdentifierReturnOptional(String userIdentifier)
      throws PedistackException;

  PersonEntity findByUserIdentifier(
      String userIdentifier, Supplier<? extends PedistackException> supplier)
      throws PedistackException;

  PersonEntity findByUserIdentifier(String userIdentifier) throws PedistackException;
}
