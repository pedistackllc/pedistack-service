package com.pedistack.identity.operation.managers;

import com.pedistack.common.exception.PedistackErrorDescriptions;
import com.pedistack.common.exception.PedistackException;
import com.pedistack.db.identity.DeveloperInformationEntity;
import com.pedistack.db.identity.DeveloperInformationEntityDaoManager;
import com.pedistack.db.oauth.UserEntityDaoManager;
import com.pedistack.identity.v1_0.common.Developer;
import java.util.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class DeveloperInformationOperationManagerBean
    implements DeveloperInformationOperationManager {

  private final DeveloperInformationEntityDaoManager developerInformationEntityDaoManager;
  private final UserEntityDaoManager userEntityDaoManager;

  public DeveloperInformationOperationManagerBean(
      DeveloperInformationEntityDaoManager developerInformationEntityDaoManager,
      UserEntityDaoManager userEntityDaoManager) {
    this.developerInformationEntityDaoManager = developerInformationEntityDaoManager;
    this.userEntityDaoManager = userEntityDaoManager;
  }

  @Override
  public DeveloperInformationEntity addOrUpdateDeveloperInformation(
      String tenant,
      String sessionUserIdentifier,
      String sessionReference,
      String userIdentifier,
      String developerInformationIdentifier,
      Developer developer)
      throws PedistackException {
    final DeveloperInformationEntity developerInformationEntity =
        Optional.ofNullable(
                developerInformationEntityDaoManager.findByIdentifier(
                    developerInformationIdentifier))
            .orElse(new DeveloperInformationEntity());
    if (developerInformationEntity.getUser() != null
        && !developerInformationEntity.getUser().getId().equals(sessionUserIdentifier)) {
      throw PedistackException.createInternalErrorException(
          PedistackErrorDescriptions.UNAUTHORIZED_ACCESS_ERROR_DESCRIPTION);
    }
    if (developer.getApiUrl() != null) {
      developerInformationEntity.setApiUrl(developer.getApiUrl());
    }
    if (developer.getCallbackUrl() != null) {
      developerInformationEntity.setCallbackUrl(developer.getCallbackUrl());
    }
    if (developer.getWebhookUrl() != null) {
      developerInformationEntity.setWebhookUrl(developer.getWebhookUrl());
    }
    developerInformationEntity.setUser(userEntityDaoManager.findByIdentifier(userIdentifier));
    return developerInformationEntityDaoManager.save(developerInformationEntity);
  }

  @Override
  public Developer developerInformationWithUsername(
      String tenant, String sessionUserIdentifier, String sessionReference, String username)
      throws PedistackException {
    final DeveloperInformationEntity developerInformationEntity =
        developerInformationEntityDaoManager.findByUserUsername(username);
    if (developerInformationEntity.getUser() != null
        && !developerInformationEntity.getUser().getId().equals(sessionUserIdentifier)) {
      throw PedistackException.createInternalErrorException(
          PedistackErrorDescriptions.UNAUTHORIZED_ACCESS_ERROR_DESCRIPTION);
    }
    return createDeveloperResponse(developerInformationEntity);
  }

  @Override
  public Developer developerInformationWithClientId(
      String tenant, String sessionUserIdentifier, String sessionReference, String clientId)
      throws PedistackException {
    final DeveloperInformationEntity developerInformationEntity =
        developerInformationEntityDaoManager.findByUserClientId(clientId);
    if (developerInformationEntity.getUser() != null
        && !developerInformationEntity.getUser().getId().equals(sessionUserIdentifier)) {
      throw PedistackException.createInternalErrorException(
          PedistackErrorDescriptions.UNAUTHORIZED_ACCESS_ERROR_DESCRIPTION);
    }
    return createDeveloperResponse(developerInformationEntity);
  }

  @Override
  public Developer developerInformationWithEmailAddress(
      String tenant, String sessionUserIdentifier, String sessionReference, String emailAddress)
      throws PedistackException {
    final DeveloperInformationEntity developerInformationEntity =
        developerInformationEntityDaoManager.findByUserEmailAddress(emailAddress);
    if (developerInformationEntity.getUser() != null
        && !developerInformationEntity.getUser().getId().equals(sessionUserIdentifier)) {
      throw PedistackException.createInternalErrorException(
          PedistackErrorDescriptions.UNAUTHORIZED_ACCESS_ERROR_DESCRIPTION);
    }
    return createDeveloperResponse(developerInformationEntity);
  }

  @Override
  public Developer developerInformationWithMobileNumber(
      String tenant, String sessionUserIdentifier, String sessionReference, String mobileNumber)
      throws PedistackException {
    final DeveloperInformationEntity developerInformationEntity =
        developerInformationEntityDaoManager.findByUserMobileNumber(mobileNumber);
    if (developerInformationEntity.getUser() != null
        && !developerInformationEntity.getUser().getId().equals(sessionUserIdentifier)) {
      throw PedistackException.createInternalErrorException(
          PedistackErrorDescriptions.UNAUTHORIZED_ACCESS_ERROR_DESCRIPTION);
    }
    return createDeveloperResponse(developerInformationEntity);
  }

  private Developer createDeveloperResponse(DeveloperInformationEntity developerInformationEntity) {
    final Developer developer = new Developer();
    BeanUtils.copyProperties(developerInformationEntity, developer);
    return developer;
  }
}
