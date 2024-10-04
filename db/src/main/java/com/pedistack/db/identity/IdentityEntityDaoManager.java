package com.pedistack.db.identity;

import com.pedistack.common.db.BaseDaoManager;
import com.pedistack.common.exception.PedistackException;
import java.util.Optional;
import java.util.function.Supplier;

public interface IdentityEntityDaoManager
    extends BaseDaoManager<IdentityEntity, IdentityEntityDao> {

  Optional<IdentityEntity> findByMobileNumberReturnOptional(String mobileNumber)
      throws PedistackException;

  IdentityEntity findByMobileNumber(
      String mobileNumber, Supplier<? extends PedistackException> supplier)
      throws PedistackException;

  IdentityEntity findByMobileNumber(String mobileNumber) throws PedistackException;

  Optional<IdentityEntity> findByEmailAddressReturnOptional(String emailAddress)
      throws PedistackException;

  IdentityEntity findByEmailAddress(
      String emailAddress, Supplier<? extends PedistackException> supplier)
      throws PedistackException;

  IdentityEntity findByEmailAddress(String emailAddress) throws PedistackException;

  Optional<IdentityEntity> findByUsernameReturnOptional(String username) throws PedistackException;

  IdentityEntity findByUsername(String username, Supplier<? extends PedistackException> supplier)
      throws PedistackException;

  IdentityEntity findByUsername(String username) throws PedistackException;

  Optional<IdentityEntity> findByClientIdReturnOptional(String clientId) throws PedistackException;

  IdentityEntity findByClientId(String clientId, Supplier<? extends PedistackException> supplier)
      throws PedistackException;

  IdentityEntity findByClientId(String clientId) throws PedistackException;

  Optional<IdentityEntity> findByInternalIdentityReturnOptional(String internalIdentity)
      throws PedistackException;

  IdentityEntity findByInternalIdentity(
      String internalIdentity, Supplier<? extends PedistackException> supplier)
      throws PedistackException;

  IdentityEntity findByInternalIdentity(String internalIdentity) throws PedistackException;

  Optional<IdentityEntity> findByUserIdentifierReturnOptional(String userIdentifier) throws PedistackException;

  IdentityEntity findByUserIdentifier(String userIdentifier, Supplier<? extends PedistackException> supplier)
          throws PedistackException;

  IdentityEntity findByUserIdentifier(String userIdentifier) throws PedistackException;

}
