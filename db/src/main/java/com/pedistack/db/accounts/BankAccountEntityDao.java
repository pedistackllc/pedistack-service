package com.pedistack.db.accounts;

import com.pedistack.common.db.BaseDao;
import com.pedistack.common.exception.PedistackException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BankAccountEntityDao extends BaseDao<BankAccountEntity> {

  List<BankAccountEntity> findByUser_Id(String userIdentifier);

  Optional<BankAccountEntity> findByUser_IdAndAccountNumberAndAccountProvider_Code(
      String userIdentifier, String accountNumber, String accountProviderCode)
      throws PedistackException;
}
