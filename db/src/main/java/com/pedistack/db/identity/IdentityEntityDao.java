package com.pedistack.db.identity;

import com.pedistack.common.db.BaseDao;
import com.pedistack.common.exception.PedistackException;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IdentityEntityDao extends BaseDao<IdentityEntity> {

  Optional<IdentityEntity> findByUser_Id(String userIdentifier) throws PedistackException;

  Optional<IdentityEntity> findByInternalIdentity(String internalIdentity)
      throws PedistackException;

  Optional<IdentityEntity> findByUser_MobileNumber(String mobileNumber) throws PedistackException;

  Optional<IdentityEntity> findByUser_EmailAddress(String emailAddress) throws PedistackException;

  Optional<IdentityEntity> findByUser_Username(String username) throws PedistackException;

  Optional<IdentityEntity> findByUser_ClientId(String clientId) throws PedistackException;
}
