package com.pedistack.events.accounts;

import com.pedistack.accounts.v1_0.common.CardAccount;
import com.pedistack.events.base.BaseApplicationEvent;

public final class CardAccountChargeEvent extends BaseApplicationEvent {

  private final CardAccount cardAccount;

  public CardAccountChargeEvent(
      Object source,
      String tenant,
      String sessionUserIdentifier,
      String sessionReference,
      CardAccount cardAccount) {
    super(source, tenant, sessionUserIdentifier, sessionReference);
    this.cardAccount = cardAccount;
  }

  public CardAccount getCardAccount() {
    return cardAccount;
  }
}
