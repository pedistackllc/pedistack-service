package com.pedistack.db.planet;

import com.pedistack.common.db.BaseDaoManager;
import com.pedistack.common.exception.PedistackException;
import java.util.Optional;
import java.util.function.Supplier;

public interface CountryEntityDaoManager extends BaseDaoManager<CountryEntity, CountryEntityDao> {

  Optional<CountryEntity> findByNameReturnOptional(String name) throws PedistackException;

  CountryEntity findByName(String name, Supplier<? extends PedistackException> supplier)
      throws PedistackException;

  CountryEntity findByName(String name) throws PedistackException;

  Optional<CountryEntity> findByAlpha2CodeReturnOptional(String alpha2Code)
      throws PedistackException;

  CountryEntity findByAlpha2Code(String alpha2Code, Supplier<? extends PedistackException> supplier)
      throws PedistackException;

  CountryEntity findByAlpha2Code(String alpha2Code) throws PedistackException;

  Optional<CountryEntity> findByAlpha3CodeReturnOptional(String alpha3Code)
      throws PedistackException;

  CountryEntity findByAlpha3Code(String alpha3Code, Supplier<? extends PedistackException> supplier)
      throws PedistackException;

  CountryEntity findByAlpha3Code(String alpha3Code) throws PedistackException;

  void checkExistingName(String name) throws PedistackException;

  void checkExistingAlpha2Code(String alpha2Code) throws PedistackException;

  void checkExistingAlpha3Code(String alpha3Code) throws PedistackException;
}
