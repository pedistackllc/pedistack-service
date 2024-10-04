package com.pedistack.db.accounts;

import com.pedistack.common.db.BaseDaoManager;
import com.pedistack.common.exception.PedistackException;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

public interface FinancialAccountEntityDaoManager extends BaseDaoManager<FinancialAccountEntity, FinancialAccountEntityDao> {

  Optional<FinancialAccountEntity> findByAccountNumberReturnOptional(String accountNumber)
      throws PedistackException;

  FinancialAccountEntity findByAccountNumber(
      String accountNumber, Supplier<? extends PedistackException> supplier)
      throws PedistackException;

  FinancialAccountEntity findByAccountNumber(String accountNumber) throws PedistackException;

  List<FinancialAccountEntity> userAccounts(String userIdentifier);

  Optional<FinancialAccountEntity> findByUserIdentifierAndAccountTypeReturnOptional(
      String userIdentifier, String accountType) throws PedistackException;

  FinancialAccountEntity findByUserIdentifierAndAccountType(
      String userIdentifier, String accountType, Supplier<? extends PedistackException> supplier)
      throws PedistackException;

  FinancialAccountEntity findByUserIdentifierAndAccountType(String userIdentifier, String accountType)
      throws PedistackException;

  Optional<FinancialAccountEntity> findByUserIdentifierAndAccountTypeAndCurrencyCodeReturnOptional(
      String userIdentifier, String accountType, String currencyCode) throws PedistackException;

  FinancialAccountEntity findByUserIdentifierAndAccountTypeAndCurrencyCode(
      String userIdentifier,
      String accountType,
      String currencyCode,
      Supplier<? extends PedistackException> supplier)
      throws PedistackException;

  FinancialAccountEntity findByUserIdentifierAndAccountTypeAndCurrencyCode(
      String userIdentifier, String accountType, String currencyCode) throws PedistackException;
}
