package com.pedistack.accounts.operation.managers;

import com.pedistack.accounts.v1_0.common.FinancialAccount;
import com.pedistack.common.accounts.AccountType;
import com.pedistack.common.exception.PedistackException;

import java.util.List;

public interface FinancialAccountOperationManager {

  void createUserAccounts(
      String tenant,
      String sessionUserIdentifier,
      String sessionReference,
      String userIdentifier,
      String currencyCode,
      AccountType... accountTypes)
      throws PedistackException;

  List<FinancialAccount> userAccounts(
      String tenant, String sessionUserIdentifier, String sessionReference)
      throws PedistackException;

  List<FinancialAccount> addUserAccount(
      String tenant,
      String sessionUserIdentifier,
      String sessionReference,
      List<String> currencyCodes)
      throws PedistackException;

  void blockFinancialAccount(
      String tenant,
      String sessionUserIdentifier,
      String sessionReference,
      String accountIdentifier)
      throws PedistackException;

  void unblockFinancialAccount(
      String tenant,
      String sessionUserIdentifier,
      String sessionReference,
      String accountIdentifier)
      throws PedistackException;

  void closeFinancialAccount(
      String tenant,
      String sessionUserIdentifier,
      String sessionReference,
      String accountIdentifier)
      throws PedistackException;
}
