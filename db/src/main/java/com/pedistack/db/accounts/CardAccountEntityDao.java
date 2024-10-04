package com.pedistack.db.accounts;

import com.pedistack.common.db.BaseDao;
import com.pedistack.common.exception.PedistackException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CardAccountEntityDao extends BaseDao<CardAccountEntity> {

    Optional<CardAccountEntity> findByLast4(String last4) throws PedistackException;
    List<CardAccountEntity> findByUser_Id(String userIdentifier);

}
