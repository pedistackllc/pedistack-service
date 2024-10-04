package com.pedistack.db.oauth;

import com.pedistack.common.authorization.CredentialType;
import com.pedistack.common.db.BaseDaoManager;
import com.pedistack.common.exception.PedistackException;
import java.util.Optional;
import java.util.function.Supplier;

public interface CredentialEntityDaoManager
    extends BaseDaoManager<CredentialEntity, CredentialEntityDao> {

  Optional<CredentialEntity> findByUserIdentifierAndCredentialTypeReturnOptional(
      String userIdentifier, CredentialType credentialType) throws PedistackException;

  CredentialEntity findByUserIdentifierAndCredentialType(
      String userIdentifier,
      CredentialType credentialType,
      Supplier<? extends PedistackException> supplier)
      throws PedistackException;

  CredentialEntity findByUserIdentifierAndCredentialType(
      String userIdentifier, CredentialType credentialType) throws PedistackException;

  Optional<CredentialEntity> findByCredentialAndCredentialTypeReturnOptional(
      String credential, CredentialType credentialType) throws PedistackException;

  CredentialEntity findByCredentialAndCredentialType(
      String credential,
      CredentialType credentialType,
      Supplier<? extends PedistackException> supplier)
      throws PedistackException;

  CredentialEntity findByCredentialAndCredentialType(
      String credential, CredentialType credentialType) throws PedistackException;
}
