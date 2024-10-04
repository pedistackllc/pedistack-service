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
      String tenant,
      String sessionUserIdentifier,
      String sessionReference,
      String emailAddress,
      String mobileNumber,
      String username)
      throws PedistackException;
}
