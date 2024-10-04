package com.pedistack.db.identity;

import com.pedistack.common.db.BaseDao;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AddressEntityDao extends BaseDao<AddressEntity> {

  Page<AddressEntity> findByUser_Id(String userIdentifier, Pageable pageable);

  Page<AddressEntity> findByUser_ClientId(String clientId, Pageable pageable);

  Page<AddressEntity> findByUser_MobileNumber(String mobileNumber, Pageable pageable);

  Page<AddressEntity> findByUser_Username(String username, Pageable pageable);

  Page<AddressEntity> findByUser_EmailAddress(String emailAddress, Pageable pageable);
}
