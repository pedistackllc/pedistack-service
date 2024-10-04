package com.pedistack.db.oauth;

import com.pedistack.common.db.BaseDao;
import com.pedistack.common.exception.PedistackException;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public interface CredentialEntityDao extends BaseDao<CredentialEntity> {

  Optional<CredentialEntity> findByUser_IdAndType(String userIdentifier, String type)
      throws PedistackException;

  Optional<CredentialEntity> findByCredentialAndType(String credential, String type)
      throws PedistackException;
}
