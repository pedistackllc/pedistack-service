package com.pedistack.db.identity;

import com.pedistack.common.db.BaseDao;
import com.pedistack.common.exception.PedistackException;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public interface DeveloperInformationEntityDao extends BaseDao<DeveloperInformationEntity> {

  Optional<DeveloperInformationEntity> findByUser_Id(String userIdentifier)
      throws PedistackException;

  Optional<DeveloperInformationEntity> findByUser_EmailAddress(String emailAddress)
      throws PedistackException;

  Optional<DeveloperInformationEntity> findByUser_MobileNumber(String mobileNumber)
      throws PedistackException;

  Optional<DeveloperInformationEntity> findByUser_Username(String username)
      throws PedistackException;

  Optional<DeveloperInformationEntity> findByUser_ClientId(String clientId)
          throws PedistackException;

}
