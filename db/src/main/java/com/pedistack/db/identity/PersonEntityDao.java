package com.pedistack.db.identity;

import com.pedistack.common.db.BaseDao;
import com.pedistack.common.exception.PedistackException;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonEntityDao extends BaseDao<PersonEntity> {

  Optional<PersonEntity> findByUser_Id(String userIdentifier) throws PedistackException;

  Optional<PersonEntity> findByUser_MobileNumber(String mobileNumber) throws PedistackException;

  Optional<PersonEntity> findByUser_EmailAddress(String emailAddress) throws PedistackException;

  Optional<PersonEntity> findByUser_Username(String username) throws PedistackException;

  Optional<PersonEntity> findByUser_ClientId(String clientId) throws PedistackException;
}
