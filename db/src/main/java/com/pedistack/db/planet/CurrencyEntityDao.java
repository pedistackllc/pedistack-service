package com.pedistack.db.planet;

import com.pedistack.common.db.BaseDao;
import com.pedistack.common.exception.PedistackException;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyEntityDao extends BaseDao<CurrencyEntity> {

  Optional<CurrencyEntity> findByCode(String code) throws PedistackException;

  Optional<CurrencyEntity> findByName(String name) throws PedistackException;

  Optional<CurrencyEntity> findBySymbol(String symbol) throws PedistackException;
}
