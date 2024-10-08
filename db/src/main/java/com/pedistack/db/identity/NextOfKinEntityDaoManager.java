package com.pedistack.db.identity;

import com.pedistack.common.db.BaseDaoManager;

import java.util.List;

public interface NextOfKinEntityDaoManager
    extends BaseDaoManager<NextOfKinEntity, NextOfKinEntityDao> {

  List<NextOfKinEntity> findByUserIdentifier(String userIdentifier);
}
