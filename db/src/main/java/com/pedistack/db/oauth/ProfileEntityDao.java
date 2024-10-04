package com.pedistack.db.oauth;

import com.pedistack.common.db.BaseDao;
import com.pedistack.common.exception.PedistackException;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfileEntityDao extends BaseDao<ProfileEntity> {

  Optional<ProfileEntity> findByName(String name) throws PedistackException;
}
