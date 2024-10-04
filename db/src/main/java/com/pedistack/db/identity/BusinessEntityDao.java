package com.pedistack.db.identity;

import com.pedistack.common.db.BaseDao;
import com.pedistack.common.exception.PedistackException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BusinessEntityDao extends BaseDao<BusinessEntity> {

  Page<BusinessEntity> findByUser_Id(String userIdentifier, Pageable pageable);

  Page<BusinessEntity> findByUser_ClientId(String clientId, Pageable pageable);

  Page<BusinessEntity> findByUser_MobileNumber(String mobileNumber, Pageable pageable);

  Page<BusinessEntity> findByUser_EmailAddress(String emailAddress, Pageable pageable);

  Page<BusinessEntity> findByUser_Username(String username, Pageable pageable);

  Optional<BusinessEntity> findByName(String name) throws PedistackException;

  Optional<BusinessEntity> findByTradingName(String tradingName) throws PedistackException;

  Optional<BusinessEntity> findByRegistrationNumber(String registrationNumber) throws PedistackException;
}
