package com.pedistack.db.identity;

import com.pedistack.common.exception.PedistackErrorDescriptions;
import com.pedistack.common.exception.PedistackException;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class NextOfKinEntityDaoManagerBean implements NextOfKinEntityDaoManager {

  private final NextOfKinEntityDao nextOfKinEntityDao;

  public NextOfKinEntityDaoManagerBean(NextOfKinEntityDao nextOfKinEntityDao) {
    this.nextOfKinEntityDao = nextOfKinEntityDao;
  }

  @Override
  public List<NextOfKinEntity> findByUserIdentifier(String userIdentifier) {
    return nextOfKinEntityDao.findByUser_Id(userIdentifier);
  }

  @Override
  public NextOfKinEntity save(NextOfKinEntity nextOfKinEntity) throws PedistackException {
    return nextOfKinEntityDao.save(nextOfKinEntity);
  }

  @Override
  public List<NextOfKinEntity> saveAll(List<NextOfKinEntity> nextOfKinEntities) {
    return nextOfKinEntityDao.saveAll(nextOfKinEntities);
  }

  @Override
  public void delete(NextOfKinEntity nextOfKinEntity) throws PedistackException {
    nextOfKinEntityDao.delete(nextOfKinEntity);
  }

  @Override
  public NextOfKinEntity findByIdentifier(String identifier) throws PedistackException {
    return nextOfKinEntityDao
        .findById(identifier)
        .orElseThrow(
            () ->
                PedistackException.createInternalErrorException(
                    PedistackErrorDescriptions.NEXT_OF_KIN_NOT_FOUND_ERROR_DESCRIPTION));
  }

  @Override
  public Page<NextOfKinEntity> findAll(Pageable pageable) throws PedistackException {
    return nextOfKinEntityDao.findAll(pageable);
  }
}
