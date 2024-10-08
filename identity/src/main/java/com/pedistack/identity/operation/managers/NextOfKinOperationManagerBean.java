package com.pedistack.identity.operation.managers;

import com.pedistack.common.exception.PedistackException;
import com.pedistack.db.identity.NextOfKinEntity;
import com.pedistack.db.identity.NextOfKinEntityDaoManager;
import com.pedistack.db.oauth.UserEntityDaoManager;
import com.pedistack.identity.v1_0.common.NextOfKin;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class NextOfKinOperationManagerBean implements NextOfKinOperationManager {

  private final NextOfKinEntityDaoManager nextOfKinEntityDaoManager;
  private final UserEntityDaoManager userEntityDaoManager;

  public NextOfKinOperationManagerBean(
      NextOfKinEntityDaoManager nextOfKinEntityDaoManager,
      UserEntityDaoManager userEntityDaoManager) {
    this.nextOfKinEntityDaoManager = nextOfKinEntityDaoManager;
    this.userEntityDaoManager = userEntityDaoManager;
  }

  @Override
  public NextOfKinEntity addOrUpdateNextOfKinInformation(
      String tenant,
      String sessionUserIdentifier,
      String sessionReference,
      String nextOfKinIdentifier,
      NextOfKin nextOfKin)
      throws PedistackException {
    NextOfKinEntity nextOfKinEntity;
    if (nextOfKinIdentifier != null) {
      nextOfKinEntity = nextOfKinEntityDaoManager.findByIdentifier(nextOfKinIdentifier);
    } else {
      nextOfKinEntity = new NextOfKinEntity();
    }
    if (nextOfKin.getEmailAddress() != null) {
      nextOfKinEntity.setEmailAddress(nextOfKin.getEmailAddress());
    }
    if (nextOfKin.getAddress() != null) {
      nextOfKinEntity.setAddress(nextOfKin.getAddress());
    }
    if (nextOfKin.getFullName() != null) {
      nextOfKinEntity.setFullName(nextOfKin.getFullName());
    }
    if (nextOfKin.getMsisdn() != null) {
      nextOfKinEntity.setMsisdn(nextOfKin.getMsisdn());
    }
    if (nextOfKin.getRelationship() != null) {
      nextOfKinEntity.setRelationship(nextOfKin.getRelationship());
    }
    nextOfKinEntity.setUser(userEntityDaoManager.findByIdentifier(sessionUserIdentifier));
    return nextOfKinEntityDaoManager.save(nextOfKinEntity);
  }

  @Override
  public NextOfKin nextOfKinInformation(
      String tenant,
      String sessionUserIdentifier,
      String sessionReference,
      String nextOfKinIdentifier)
      throws PedistackException {
    return createNextOfKinResponse(nextOfKinEntityDaoManager.findByIdentifier(nextOfKinIdentifier));
  }

  @Override
  public List<NextOfKin> nextOfKins(
      String tenant, String sessionUserIdentifier, String sessionReference) {
    return nextOfKinEntityDaoManager.findByUserIdentifier(sessionUserIdentifier).stream()
        .map(this::createNextOfKinResponse)
        .toList();
  }

  @Override
  public void deleteNextOfKin(
      String tenant,
      String sessionUserIdentifier,
      String sessionReference,
      String nextOfKinIdentifier)
      throws PedistackException {
    nextOfKinEntityDaoManager.delete(
        nextOfKinEntityDaoManager.findByIdentifier(nextOfKinIdentifier));
  }

  private NextOfKin createNextOfKinResponse(NextOfKinEntity nextOfKinEntity) {
    final NextOfKin nextOfKin = new NextOfKin();
    BeanUtils.copyProperties(nextOfKinEntity, nextOfKin);
    return nextOfKin;
  }
}
