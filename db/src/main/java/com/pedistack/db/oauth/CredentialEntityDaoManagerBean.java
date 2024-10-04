package com.pedistack.db.oauth;

import com.pedistack.common.authorization.CredentialType;
import com.pedistack.common.exception.PedistackErrorDescriptions;
import com.pedistack.common.exception.PedistackException;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class CredentialEntityDaoManagerBean implements CredentialEntityDaoManager {

  private final CredentialEntityDao credentialEntityDao;

  public CredentialEntityDaoManagerBean(CredentialEntityDao credentialEntityDao) {
    this.credentialEntityDao = credentialEntityDao;
  }

  @Override
  public CredentialEntity save(CredentialEntity credentialEntity) throws PedistackException {
    return credentialEntityDao.save(credentialEntity);
  }

  @Override
  public List<CredentialEntity> saveAll(List<CredentialEntity> credentialEntities) {
    return credentialEntityDao.saveAll(credentialEntities);
  }

  @Override
  public void delete(CredentialEntity credentialEntity) throws PedistackException {
    credentialEntityDao.delete(credentialEntity);
  }

  @Override
  public CredentialEntity findByIdentifier(String identifier) throws PedistackException {
    return credentialEntityDao
        .findById(identifier)
        .orElseThrow(
            () ->
                PedistackException.createInternalErrorException(
                    PedistackErrorDescriptions.CREDENTIAL_NOT_FOUND_ERROR_DESCRIPTION));
  }

  @Override
  public Page<CredentialEntity> findAll(Pageable pageable) throws PedistackException {
    return credentialEntityDao.findAll(pageable);
  }

  @Override
  public Optional<CredentialEntity> findByUserIdentifierAndCredentialTypeReturnOptional(
      String userIdentifier, CredentialType credentialType) throws PedistackException {
    return credentialEntityDao.findByUser_IdAndType(userIdentifier, credentialType.name());
  }

  @Override
  public CredentialEntity findByUserIdentifierAndCredentialType(
      String userIdentifier,
      CredentialType credentialType,
      Supplier<? extends PedistackException> supplier)
      throws PedistackException {
    return credentialEntityDao
        .findByUser_IdAndType(userIdentifier, credentialType.name())
        .orElseThrow(supplier);
  }

  @Override
  public CredentialEntity findByUserIdentifierAndCredentialType(
      String userIdentifier, CredentialType credentialType) throws PedistackException {
    return credentialEntityDao
        .findByUser_IdAndType(userIdentifier, credentialType.name())
        .orElseThrow(
            () ->
                PedistackException.createInternalErrorException(
                    PedistackErrorDescriptions.CREDENTIAL_NOT_FOUND_ERROR_DESCRIPTION));
  }

  @Override
  public Optional<CredentialEntity> findByCredentialAndCredentialTypeReturnOptional(
      String credential, CredentialType credentialType) throws PedistackException {
    return credentialEntityDao.findByCredentialAndType(credential, credentialType.name());
  }

  @Override
  public CredentialEntity findByCredentialAndCredentialType(
      String credential,
      CredentialType credentialType,
      Supplier<? extends PedistackException> supplier)
      throws PedistackException {
    return credentialEntityDao
        .findByCredentialAndType(credential, credentialType.name())
        .orElseThrow(supplier);
  }

  @Override
  public CredentialEntity findByCredentialAndCredentialType(
      String credential, CredentialType credentialType) throws PedistackException {
    return credentialEntityDao
        .findByCredentialAndType(credential, credentialType.name())
        .orElseThrow(
            () ->
                PedistackException.createInternalErrorException(
                    PedistackErrorDescriptions.CREDENTIAL_NOT_FOUND_ERROR_DESCRIPTION));
  }
}
