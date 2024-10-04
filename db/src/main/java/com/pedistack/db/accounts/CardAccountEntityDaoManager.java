package com.pedistack.db.accounts;

import com.pedistack.common.db.BaseDaoManager;
import com.pedistack.common.exception.PedistackException;
import java.util.List;

public interface CardAccountEntityDaoManager
    extends BaseDaoManager<CardAccountEntity, CardAccountEntityDao> {

  List<CardAccountEntity> findByUserIdentifier(String userIdentifier);

  void checkExistingCard(String last4) throws PedistackException;
}
