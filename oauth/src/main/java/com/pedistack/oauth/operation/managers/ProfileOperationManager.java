package com.pedistack.oauth.operation.managers;

import com.pedistack.common.exception.PedistackException;
import com.pedistack.common.managers.CrudOperationManager;
import com.pedistack.oauth.v1_0.ProfileRequest;
import com.pedistack.oauth.v1_0.common.Profile;

import java.util.List;
import java.util.Map;

public interface ProfileOperationManager extends CrudOperationManager<ProfileRequest, Profile> {

  Profile updateProfilePermissions(
      String sessionUserIdentifier,
      String sessionReference,
      String profileIdentifier,
      List<String> permissions,
      Boolean updateStatus)
      throws PedistackException;

  Profile addProfileAdditionalInformation(
      String sessionUserIdentifier,
      String sessionReference,
      String profileIdentifier,
      Map<String, String> additionalInformation)
      throws PedistackException;

  Profile removeProfileAdditionalInformation(
          String sessionUserIdentifier,
          String sessionReference,
          String profileIdentifier,
          List<String> additionalInformationKeys)
          throws PedistackException;

}
