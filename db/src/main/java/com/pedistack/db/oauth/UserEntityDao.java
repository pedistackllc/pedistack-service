package com.pedistack.db.oauth;

import com.pedistack.common.db.BaseDao;
import com.pedistack.common.exception.PedistackException;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public interface UserEntityDao extends BaseDao<UserEntity> {

  Optional<UserEntity> findByUsername(String username) throws PedistackException;

  Optional<UserEntity> findByMobileNumber(String mobileNumber) throws PedistackException;

  Optional<UserEntity> findByEmailAddress(String emailAddress) throws PedistackException;

  Optional<UserEntity> findByClientId(String clientId) throws PedistackException;
}
