package com.pedistack.identity.operation.managers;

import com.pedistack.common.exception.PedistackErrorDescriptions;
import com.pedistack.common.exception.PedistackException;
import com.pedistack.common.identity.IdentificationVerificationStatus;
import com.pedistack.common.io.PageResponse;
import com.pedistack.db.identity.IdentificationEntity;
import com.pedistack.db.identity.IdentificationEntityDaoManager;
import com.pedistack.db.oauth.UserEntity;
import com.pedistack.db.oauth.UserEntityDaoManager;
import com.pedistack.identity.v1_0.common.Identification;
import com.pedistack.identity.v1_0.common.IdentificationType;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

@Component
public class IdentificationInformationOperationManagerBean
    implements IdentificationInformationOperationManager {

  private final IdentificationEntityDaoManager identificationEntityDaoManager;
  private final UserEntityDaoManager userEntityDaoManager;

  public IdentificationInformationOperationManagerBean(
      IdentificationEntityDaoManager identificationEntityDaoManager,
      UserEntityDaoManager userEntityDaoManager) {
    this.identificationEntityDaoManager = identificationEntityDaoManager;
    this.userEntityDaoManager = userEntityDaoManager;
  }

  @Override
  public IdentificationEntity addOrUpdateIdentificationInformation(
      String tenant,
      String sessionUserIdentifier,
      String sessionUserReference,
      String userIdentifier,
      String identificationIdentifier,
      Identification identification)
      throws PedistackException {
    identificationEntityDaoManager.checkExistingIdentification(
        identification.getIdentificationNumber(),
        identification.getType().name(),
        identification.getIssuingCountryCode().name());
    IdentificationEntity identificationEntity;
    if (identificationIdentifier != null) {
      identificationEntity =
          identificationEntityDaoManager.findByIdentifier(identificationIdentifier);
    } else {
      identificationEntity = new IdentificationEntity();
    }
    if (identification.getIssuingCountryCode() != null) {
      identificationEntity.setIssuingCountryCode(identification.getIssuingCountryCode().name());
    }
    if (identification.getIdentificationNumber() != null) {
      identificationEntity.setIdentificationNumber(identification.getIdentificationNumber());
    }
    if (identification.getType() != null) {
      identificationEntity.setType(identification.getType().name());
    }
    if (identification.getIssueDate() != null && identification.getExpiryDate() != null) {
      if (identification
          .getIssueDate()
          .toInstant()
          .isAfter(identification.getExpiryDate().toInstant())) {
        throw PedistackException.createInternalErrorException(
            PedistackErrorDescriptions.ISSUE_DATE_AFTER_EXPIRY_DATE_ERROR_DESCRIPTION);
      }
    }
    if (identification.getIssueDate() != null) {
      identificationEntity.setIssueDate(identification.getIssueDate());
    }
    if (identification.getExpiryDate() != null) {
      identificationEntity.setExpiryDate(identification.getExpiryDate());
    }
    if (identification.getIssuer() != null) {
      identificationEntity.setIssuer(identification.getIssuer());
    }
    identificationEntity.setVerificationStatus(IdentificationVerificationStatus.REGISTERED.name());
    identificationEntity.setUser(userEntityDaoManager.findByIdentifier(userIdentifier));
    return identificationEntityDaoManager.save(identificationEntity);
  }

  @Override
  public PageResponse<Identification> identificationInformationWithUsername(
      String tenant,
      String sessionUserIdentifier,
      String sessionUserReference,
      String username,
      int page,
      int size)
      throws PedistackException {
    final UserEntity userEntity = userEntityDaoManager.findByUsername(username);
    if (!userEntity.getId().equals(sessionUserIdentifier)) {
      throw PedistackException.createInternalErrorException(
          PedistackErrorDescriptions.UNAUTHORIZED_ACCESS_ERROR_DESCRIPTION);
    }
    final Page<IdentificationEntity> identificationEntityPage =
        identificationEntityDaoManager.findByUserUsername(username, PageRequest.of(page, size));
    return PageResponse.create(
        identificationEntityPage.stream().map(this::createIdentificationResponse).toList(),
        page,
        size,
        identificationEntityPage.getTotalElements(),
        identificationEntityPage.getTotalPages());
  }

  @Override
  public PageResponse<Identification> identificationInformationWithEmailAddress(
      String tenant,
      String sessionUserIdentifier,
      String sessionUserReference,
      String emailAddress,
      int page,
      int size)
      throws PedistackException {
    final UserEntity userEntity = userEntityDaoManager.findByEmailAddress(emailAddress);
    if (!userEntity.getId().equals(sessionUserIdentifier)) {
      throw PedistackException.createInternalErrorException(
          PedistackErrorDescriptions.UNAUTHORIZED_ACCESS_ERROR_DESCRIPTION);
    }
    final Page<IdentificationEntity> identificationEntityPage =
        identificationEntityDaoManager.findByUserEmailAddress(
            emailAddress, PageRequest.of(page, size));
    return PageResponse.create(
        identificationEntityPage.stream().map(this::createIdentificationResponse).toList(),
        page,
        size,
        identificationEntityPage.getTotalElements(),
        identificationEntityPage.getTotalPages());
  }

  @Override
  public PageResponse<Identification> identificationInformationWithMobileNumber(
      String tenant,
      String sessionUserIdentifier,
      String sessionUserReference,
      String mobileNumber,
      int page,
      int size)
      throws PedistackException {
    final UserEntity userEntity = userEntityDaoManager.findByMobileNumber(mobileNumber);
    if (!userEntity.getId().equals(sessionUserIdentifier)) {
      throw PedistackException.createInternalErrorException(
          PedistackErrorDescriptions.UNAUTHORIZED_ACCESS_ERROR_DESCRIPTION);
    }
    final Page<IdentificationEntity> identificationEntityPage =
        identificationEntityDaoManager.findByUserMobileNumber(
            mobileNumber, PageRequest.of(page, size));
    return PageResponse.create(
        identificationEntityPage.stream().map(this::createIdentificationResponse).toList(),
        page,
        size,
        identificationEntityPage.getTotalElements(),
        identificationEntityPage.getTotalPages());
  }

  @Override
  public PageResponse<Identification> identificationInformationWithClientId(
      String tenant,
      String sessionUserIdentifier,
      String sessionUserReference,
      String clientId,
      int page,
      int size)
      throws PedistackException {
    final UserEntity userEntity = userEntityDaoManager.findByClientId(clientId);
    if (!userEntity.getId().equals(sessionUserIdentifier)) {
      throw PedistackException.createInternalErrorException(
          PedistackErrorDescriptions.UNAUTHORIZED_ACCESS_ERROR_DESCRIPTION);
    }
    final Page<IdentificationEntity> identificationEntityPage =
        identificationEntityDaoManager.findByUserClientId(clientId, PageRequest.of(page, size));
    return PageResponse.create(
        identificationEntityPage.stream().map(this::createIdentificationResponse).toList(),
        page,
        size,
        identificationEntityPage.getTotalElements(),
        identificationEntityPage.getTotalPages());
  }

  @Override
  public IdentificationEntity identificationInformation(
      String tenant,
      String sessionUserIdentifier,
      String sessionUserReference,
      String identificationIdentifier)
      throws PedistackException {
    return identificationEntityDaoManager.findByIdentifier(identificationIdentifier);
  }

  @Override
  public void removeIdentification(
      String tenant,
      String sessionUserIdentifier,
      String sessionUserReference,
      String identificationIdentifier)
      throws PedistackException {
    identificationEntityDaoManager.delete(
        identificationEntityDaoManager.findByIdentifier(identificationIdentifier));
  }

  private Identification createIdentificationResponse(IdentificationEntity identificationEntity) {
    final Identification identification = new Identification();
    BeanUtils.copyProperties(identificationEntity, identification);
    identification.setType(IdentificationType.valueOf(identificationEntity.getType()));
    return identification;
  }
}
