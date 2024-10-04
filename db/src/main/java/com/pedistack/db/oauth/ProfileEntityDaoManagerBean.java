package com.pedistack.db.oauth;

import com.pedistack.common.exception.PedistackErrorDescriptions;
import com.pedistack.common.exception.PedistackException;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class ProfileEntityDaoManagerBean implements ProfileEntityDaoManager {

  private final ProfileEntityDao profileEntityDao;

  public ProfileEntityDaoManagerBean(ProfileEntityDao profileEntityDao) {
    this.profileEntityDao = profileEntityDao;
  }

  @Override
  public Optional<ProfileEntity> findByNameReturnOptional(String name) throws PedistackException {
    return profileEntityDao.findByName(name);
  }

  @Override
  public ProfileEntity findByName(String name, Supplier<? extends PedistackException> supplier)
      throws PedistackException {
    return profileEntityDao.findByName(name).orElseThrow(supplier);
  }

  @Override
  public ProfileEntity findByName(String name) throws PedistackException {
    return profileEntityDao
        .findByName(name)
        .orElseThrow(
            () ->
                PedistackException.createInternalErrorException(
                    PedistackErrorDescriptions.PROFILE_NOT_FOUND_ERROR_DESCRIPTION));
  }

  @Override
  public void checkExistingName(String name) throws PedistackException {
    if (profileEntityDao.findByName(name).isPresent()) {
      throw PedistackException.createInternalErrorException(
          PedistackErrorDescriptions.PROFILE_ALREADY_REGISTERED);
    }
  }

  @Override
  public ProfileEntity save(ProfileEntity profileEntity) throws PedistackException {
    return profileEntityDao.save(profileEntity);
  }

  @Override
  public List<ProfileEntity> saveAll(List<ProfileEntity> profileEntities) {
    return profileEntityDao.saveAll(profileEntities);
  }

  @Override
  public void delete(ProfileEntity profileEntity) throws PedistackException {
    profileEntityDao.delete(profileEntity);
  }

  @Override
  public ProfileEntity findByIdentifier(String identifier) throws PedistackException {
    return profileEntityDao
        .findById(identifier)
        .orElseThrow(
            () ->
                PedistackException.createInternalErrorException(
                    PedistackErrorDescriptions.PROFILE_NOT_FOUND_ERROR_DESCRIPTION));
  }

  @Override
  public Page<ProfileEntity> findAll(Pageable pageable) throws PedistackException {
    return profileEntityDao.findAll(pageable);
  }
}
