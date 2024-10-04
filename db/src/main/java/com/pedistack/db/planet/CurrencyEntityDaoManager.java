package com.pedistack.db.planet;

import com.pedistack.common.db.BaseDaoManager;
import com.pedistack.common.exception.PedistackException;
import java.util.Optional;
import java.util.function.Supplier;

public interface CurrencyEntityDaoManager
    extends BaseDaoManager<CurrencyEntity, CurrencyEntityDao> {

  Optional<CurrencyEntity> findByNameReturnOptional(String name) throws PedistackException;

  CurrencyEntity findByName(String name, Supplier<? extends PedistackException> supplier)
      throws PedistackException;

  CurrencyEntity findByName(String name) throws PedistackException;

  Optional<CurrencyEntity> findByCodeReturnOptional(String code) throws PedistackException;

  CurrencyEntity findByCode(String code, Supplier<? extends PedistackException> supplier)
      throws PedistackException;

  CurrencyEntity findByCode(String code) throws PedistackException;

  Optional<CurrencyEntity> findBySymbolReturnOptional(String symbol) throws PedistackException;

  CurrencyEntity findBySymbol(String symbol, Supplier<? extends PedistackException> supplier)
      throws PedistackException;

  CurrencyEntity findBySymbol(String symbol) throws PedistackException;

  void checkExistingName(String name) throws PedistackException;

  void checkExistingCode(String code) throws PedistackException;
}
