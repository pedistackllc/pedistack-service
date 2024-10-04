package com.pedistack.oauth.operation.managers;

import com.pedistack.common.exception.PedistackException;
import com.pedistack.common.io.PageResponse;
import com.pedistack.db.oauth.ProfileEntity;
import com.pedistack.db.oauth.ProfileEntityDaoManager;
import com.pedistack.oauth.v1_0.ProfileRequest;
import com.pedistack.oauth.v1_0.common.Profile;
import com.pedistack.oauth.v1_0.common.ProfileType;
import java.util.List;
import java.util.Map;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class ProfileOperationManagerBean implements ProfileOperationManager {

  private final ProfileEntityDaoManager profileEntityDaoManager;

  public ProfileOperationManagerBean(ProfileEntityDaoManager profileEntityDaoManager) {
    this.profileEntityDaoManager = profileEntityDaoManager;
  }

  @Override
  @Transactional
  public Profile updateProfilePermissions(
      String sessionUserIdentifier,
      String sessionReference,
      String profileIdentifier,
      List<String> permissions,
      Boolean updateStatus)
      throws PedistackException {
    final ProfileEntity persistedProfileEntity =
        profileEntityDaoManager.findByIdentifier(profileIdentifier);
    if (updateStatus) {
      persistedProfileEntity
          .getPermissions()
          .addAll(
              permissions.stream()
                  .filter(
                      permission -> !persistedProfileEntity.getPermissions().contains(permission))
                  .toList());
    } else {
      persistedProfileEntity
          .getPermissions()
          .removeAll(
              permissions.stream()
                  .filter(
                      permission -> persistedProfileEntity.getPermissions().contains(permission))
                  .toList());
    }
    profileEntityDaoManager.save(persistedProfileEntity);
    return createProfileResponse(persistedProfileEntity);
  }

  @Override
  @Transactional
  public Profile addProfileAdditionalInformation(
      String sessionUserIdentifier,
      String sessionReference,
      String profileIdentifier,
      Map<String, String> additionalInformation)
      throws PedistackException {
    final ProfileEntity persistedProfileEntity =
        profileEntityDaoManager.findByIdentifier(profileIdentifier);
    persistedProfileEntity.getAdditionalInformation().putAll(additionalInformation);
    return createProfileResponse(persistedProfileEntity);
  }

  @Override
  @Transactional
  public Profile removeProfileAdditionalInformation(
      String sessionUserIdentifier,
      String sessionReference,
      String profileIdentifier,
      List<String> additionalInformationKeys)
      throws PedistackException {
    final ProfileEntity persistedProfileEntity =
        profileEntityDaoManager.findByIdentifier(profileIdentifier);
    additionalInformationKeys.forEach(
        key -> persistedProfileEntity.getAdditionalInformation().remove(key));
    return createProfileResponse(persistedProfileEntity);
  }

  @Override
  @Transactional
  public Profile create(String tenant,
      String sessionUserIdentifier, String sessionReference, ProfileRequest profileRequest)
      throws PedistackException {
    profileEntityDaoManager.checkExistingName(profileRequest.getName());
    final ProfileEntity profileEntity = new ProfileEntity();
    profileEntity.setPermissions(profileRequest.getPermissions());
    profileEntity.setDescription(profileRequest.getDescription());
    profileEntity.setType(profileRequest.getType().name());
    profileEntity.setName(profileRequest.getName());
    profileEntity.setAdditionalInformation(profileRequest.getAdditionalInformation());
    profileEntityDaoManager.save(profileEntity);
    return createProfileResponse(profileEntity);
  }

  @Override
  public Profile get(String tenant,String sessionUserIdentifier, String sessionReference, String identifier)
      throws PedistackException {
    final ProfileEntity persistedProfileEntity =
        profileEntityDaoManager.findByIdentifier(identifier);
    return createProfileResponse(persistedProfileEntity);
  }

  @Override
  public PageResponse<Profile> list(String tenant,
      String sessionUserIdentifier, String sessionReference, Integer page, Integer size)
      throws PedistackException {
    final org.springframework.data.domain.Page<ProfileEntity> profileEntityPage =
        profileEntityDaoManager.findAll(PageRequest.of(page, size));
    return PageResponse.create(
        profileEntityPage.stream().map(this::createProfileResponse).toList(),
        page,
        size,
        profileEntityPage.getTotalElements(),
        profileEntityPage.getTotalPages());
  }

  @Override
  @Transactional
  public Profile update(String tenant,
      String sessionUserIdentifier,
      String sessionReference,
      String identifier,
      ProfileRequest profileRequest)
      throws PedistackException {
    final ProfileEntity persistedProfileEntity =
        profileEntityDaoManager.findByIdentifier(identifier);
    if (profileRequest.getName() != null) {
      profileEntityDaoManager.checkExistingName(profileRequest.getName());
      persistedProfileEntity.setName(profileRequest.getName());
    }
    if (profileRequest.getDescription() != null) {
      persistedProfileEntity.setDescription(profileRequest.getDescription());
    }
    if (profileRequest.getType() != null) {
      persistedProfileEntity.setType(profileRequest.getType().name());
    }
    return createProfileResponse(profileEntityDaoManager.save(persistedProfileEntity));
  }

  @Override
  @Transactional
  public void delete(String tenant,String sessionUserIdentifier, String sessionReference, String identifier)
      throws PedistackException {
    profileEntityDaoManager.delete(profileEntityDaoManager.findByIdentifier(identifier));
  }

  private Profile createProfileResponse(final ProfileEntity profileEntity) {
    final Profile profile = new Profile();
    profile.setId(profileEntity.getId());
    profile.setDescription(profileEntity.getDescription());
    profile.setName(profileEntity.getName());
    profile.setPermissions(profileEntity.getPermissions());
    profile.setAdditionalInformation(profileEntity.getAdditionalInformation());
    profile.setType(ProfileType.valueOf(profileEntity.getType()));
    return profile;
  }
}
