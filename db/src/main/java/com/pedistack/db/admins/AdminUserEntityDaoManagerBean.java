package com.pedistack.db.admins;

import com.pedistack.common.exception.PedistackErrorDescriptions;
import com.pedistack.common.exception.PedistackException;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class AdminUserEntityDaoManagerBean implements AdminUserEntityDaoManager {

  private final AdminUserEntityDao adminUserEntityDao;

  public AdminUserEntityDaoManagerBean(AdminUserEntityDao adminUserEntityDao) {
    this.adminUserEntityDao = adminUserEntityDao;
  }

  @Override
  public AdminUserEntity save(AdminUserEntity adminUserEntity) throws PedistackException {
    return adminUserEntityDao.save(adminUserEntity);
  }

  @Override
  public List<AdminUserEntity> saveAll(List<AdminUserEntity> adminUserEntities) {
    return adminUserEntityDao.saveAll(adminUserEntities);
  }

  @Override
  public void delete(AdminUserEntity adminUserEntity) throws PedistackException {
    adminUserEntityDao.delete(adminUserEntity);
  }

  @Override
  public AdminUserEntity findByIdentifier(String identifier) throws PedistackException {
    return adminUserEntityDao
        .findById(identifier)
        .orElseThrow(
            () ->
                PedistackException.createInternalErrorException(
                    PedistackErrorDescriptions.ADMIN_USER_NOT_FOUND_ERROR_DESCRIPTION));
  }

  @Override
  public Page<AdminUserEntity> findAll(Pageable pageable) throws PedistackException {
    return adminUserEntityDao.findAll(pageable);
  }
}
