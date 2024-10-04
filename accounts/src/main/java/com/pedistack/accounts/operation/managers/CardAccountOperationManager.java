package com.pedistack.accounts.operation.managers;

import com.pedistack.accounts.v1_0.CardAccountRequest;
import com.pedistack.accounts.v1_0.common.CardAccount;
import com.pedistack.common.managers.CrudOperationManager;

import java.util.List;

public interface CardAccountOperationManager
    extends CrudOperationManager<CardAccountRequest, CardAccount> {

  List<CardAccount> userCardAccounts(
      String tenant, String sessionUserIdentifier, String sessionReference);
}
