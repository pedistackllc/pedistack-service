package com.pedistack.db.identity;

import com.pedistack.common.db.BaseDao;
import com.pedistack.common.exception.PedistackException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IdentificationEntityDao extends BaseDao<IdentificationEntity> {

  Optional<IdentificationEntity> findByIdentificationNumberAndTypeAndIssuingCountryCode(
      String identificationNumber, String identificationType, String issuingCountryCode)
      throws PedistackException;

  Page<IdentificationEntity> findByUser_Id(String userIdentifier, Pageable pageable);

  Page<IdentificationEntity> findByUser_MobileNumber(String mobileNumber, Pageable pageable);

  Page<IdentificationEntity> findByUser_EmailAddress(String emailAddress, Pageable pageable);

  Page<IdentificationEntity> findByUser_Username(String username, Pageable pageable);

  Page<IdentificationEntity> findByUser_ClientId(String clientId, Pageable pageable);
}
