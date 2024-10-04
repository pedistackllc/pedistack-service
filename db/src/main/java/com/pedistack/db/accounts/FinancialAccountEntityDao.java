package com.pedistack.db.accounts;

import com.pedistack.common.db.BaseDao;
import com.pedistack.common.exception.PedistackException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FinancialAccountEntityDao extends BaseDao<FinancialAccountEntity> {

  Optional<FinancialAccountEntity> findByAccountNumber(String accountNumber) throws PedistackException;

  List<FinancialAccountEntity> findByUser_Id(String userIdentifier);

  Optional<FinancialAccountEntity> findByUser_IdAndType(String userIdentifier, String accountType)
      throws PedistackException;

  Optional<FinancialAccountEntity> findByUser_IdAndTypeAndCurrency_Code(
      String userIdentifier, String accountType, String currencyCode) throws PedistackException;
}
