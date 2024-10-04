package com.pedistack.db.accounts;

import com.pedistack.common.db.BaseDaoManager;
import com.pedistack.common.exception.PedistackException;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

public interface BankAccountEntityDaoManager
    extends BaseDaoManager<BankAccountEntity, BankAccountEntityDao> {

  List<BankAccountEntity> findByUserIdentifier(String userIdentifier);

  Optional<BankAccountEntity>
      findByUserIdentifierAndBankAccountNumberAndAccountProviderCodeReturnOptional(
          String userIdentifier, String bankAccountNumber, String accountProviderCode)
          throws PedistackException;

  BankAccountEntity findByUserIdentifierAndBankAccountNumberAndAccountProviderCode(
      String userIdentifier,
      String bankAccountNumber,
      String accountProviderCode,
      Supplier<? extends PedistackException> supplier)
      throws PedistackException;

  BankAccountEntity findByUserIdentifierAndBankAccountNumberAndAccountProviderCode(
      String userIdentifier, String bankAccountNumber, String accountProviderCode)
      throws PedistackException;

  void checkExistingBankAccount(String userIdentifier, String bankAccountNumber, String accountProviderCode) throws PedistackException;

}
