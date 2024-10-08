package com.pedistack.identity.operation.managers;

import com.pedistack.common.exception.PedistackException;
import com.pedistack.db.identity.NextOfKinEntity;
import com.pedistack.identity.v1_0.common.NextOfKin;

import java.util.List;

public interface NextOfKinOperationManager {

  NextOfKinEntity addOrUpdateNextOfKinInformation(
      String tenant,
      String sessionUserIdentifier,
      String sessionReference,
      String nextOfKinIdentifier,
      NextOfKin nextOfKin)
      throws PedistackException;

  NextOfKin nextOfKinInformation(
      String tenant,
      String sessionUserIdentifier,
      String sessionReference,
      String nextOfKinIdentifier)
      throws PedistackException;

  List<NextOfKin> nextOfKins(String tenant, String sessionUserIdentifier, String sessionReference);

  void deleteNextOfKin(
      String tenant,
      String sessionUserIdentifier,
      String sessionReference,
      String nextOfKinIdentifier)
      throws PedistackException;
}
