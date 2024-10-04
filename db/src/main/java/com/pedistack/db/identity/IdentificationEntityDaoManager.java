package com.pedistack.db.identity;

import com.pedistack.common.db.BaseDaoManager;
import com.pedistack.common.exception.PedistackException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IdentificationEntityDaoManager
    extends BaseDaoManager<IdentificationEntity, IdentificationEntityDao> {

  Page<IdentificationEntity> findByUserIdentifier(String userIdentifier, Pageable pageable);

  Page<IdentificationEntity> findByUserMobileNumber(String mobileNumber, Pageable pageable);

  Page<IdentificationEntity> findByUserEmailAddress(String emailAddress, Pageable pageable);

  Page<IdentificationEntity> findByUserUsername(String username, Pageable pageable);

  Page<IdentificationEntity> findByUserClientId(String clientId, Pageable pageable);

  Optional<IdentificationEntity>
      findByIdentificationNumberAndIdentificationTypeAndIssuingCountryCode(
          String identificationNumber, String identificationType, String issuingCountryCode)
          throws PedistackException;

  void checkExistingIdentification(
      String identificationNumber, String identificationType, String issuingCountryCode)
      throws PedistackException;
}
