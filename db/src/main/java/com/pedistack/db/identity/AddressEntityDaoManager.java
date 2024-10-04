package com.pedistack.db.identity;

import com.pedistack.common.db.BaseDaoManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AddressEntityDaoManager extends BaseDaoManager<AddressEntity, AddressEntityDao> {

  Page<AddressEntity> findByUserIdentifier(String userIdentifier, Pageable pageable);

  Page<AddressEntity> findByUserClientId(String clientId, Pageable pageable);

  Page<AddressEntity> findByUserMobileNumber(String mobileNumber, Pageable pageable);

  Page<AddressEntity> findByUserUsername(String username, Pageable pageable);

  Page<AddressEntity> findByUserEmailAddress(String emailAddress, Pageable pageable);
}
