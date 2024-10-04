package com.pedistack.db.identity;

import com.pedistack.common.db.BaseDaoManager;
import com.pedistack.common.exception.PedistackException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BusinessEntityDaoManager
    extends BaseDaoManager<BusinessEntity, BusinessEntityDao> {

  Page<BusinessEntity> findByUserIdentifier(String userIdentifier, Pageable pageable);

  Page<BusinessEntity> findByMobileNumber(String mobileNumber, Pageable pageable);

  Page<BusinessEntity> findByEmailAddress(String emailAddress, Pageable pageable);

  Page<BusinessEntity> findByUsername(String username, Pageable pageable);

  Page<BusinessEntity> findByClientId(String clientId, Pageable pageable);

  void checkExistingBusinessInformation(String name,String tradingName,String registrationNumber) throws PedistackException;

}
