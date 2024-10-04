package com.pedistack.accounts.operation.managers;

import com.pedistack.accounts.v1_0.BankAccountRequest;
import com.pedistack.accounts.v1_0.common.BankAccount;
import com.pedistack.common.managers.CrudOperationManager;

public interface BankAccountOperationManager
    extends CrudOperationManager<BankAccountRequest, BankAccount> {}
