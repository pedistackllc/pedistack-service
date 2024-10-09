package com.pedistack.events.accounts;

import com.pedistack.common.accounts.AccountStatus;
import com.pedistack.db.accounts.FinancialAccountEntity;
import com.pedistack.events.base.BaseApplicationEvent;

public final class AccountStatusUpdatedEvent extends BaseApplicationEvent {

  private final FinancialAccountEntity financialAccountEntity;
  private final AccountStatus accountStatus;

  public AccountStatusUpdatedEvent(
      Object source,
      String tenant,
      String sessionUserIdentifier,
      String sessionReference,
      FinancialAccountEntity financialAccountEntity,
      AccountStatus accountStatus) {
    super(source, tenant, sessionUserIdentifier, sessionReference);
    this.financialAccountEntity = financialAccountEntity;
    this.accountStatus = accountStatus;
  }

  public FinancialAccountEntity getFinancialAccountEntity() {
    return financialAccountEntity;
  }

  public AccountStatus getAccountStatus() {
    return accountStatus;
  }
}
