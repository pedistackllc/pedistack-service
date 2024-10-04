package com.pedistack.db.oauth;

import com.pedistack.common.db.BaseDaoManager;
import com.pedistack.common.exception.PedistackException;
import java.util.Optional;
import java.util.function.Supplier;

public interface ProfileEntityDaoManager extends BaseDaoManager<ProfileEntity, ProfileEntityDao> {

  Optional<ProfileEntity> findByNameReturnOptional(String name) throws PedistackException;

  ProfileEntity findByName(String name, Supplier<? extends PedistackException> supplier)
      throws PedistackException;

  ProfileEntity findByName(String name) throws PedistackException;

  void checkExistingName(String name) throws PedistackException;
}
