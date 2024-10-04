package com.pedistack.events.accounts;

import com.pedistack.accounts.v1_0.BankAccountRequest;
import com.pedistack.accounts.v1_0.common.BankAccount;
import com.pedistack.db.accounts.BankAccountEntity;
import com.pedistack.events.base.BaseApplicationEvent;

public final class BankAccountVerificationEvent extends BaseApplicationEvent {

  private final BankAccountRequest bankAccountRequest;
  private final BankAccount bankAccount;
  private final BankAccountEntity bankAccountEntity;

  public BankAccountVerificationEvent(
      Object source,
      String tenant,
      String sessionUserIdentifier,
      String sessionReference,
      BankAccountRequest bankAccountRequest,
      BankAccount bankAccount,
      BankAccountEntity bankAccountEntity) {
    super(source, tenant, sessionUserIdentifier, sessionReference);
    this.bankAccountRequest = bankAccountRequest;
    this.bankAccount = bankAccount;
    this.bankAccountEntity = bankAccountEntity;
  }

  public BankAccountRequest getBankAccountRequest() {
    return bankAccountRequest;
  }

  public BankAccount getBankAccount() {
    return bankAccount;
  }

  public BankAccountEntity getBankAccountEntity() {
    return bankAccountEntity;
  }
}
