package com.pedistack.db.planet;

import com.pedistack.common.db.BaseDao;
import com.pedistack.common.exception.PedistackException;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CountryEntityDao extends BaseDao<CountryEntity> {

  Optional<CountryEntity> findByName(String name) throws PedistackException;

  Optional<CountryEntity> findByAlpha2Code(String alpha2Code) throws PedistackException;

  Optional<CountryEntity> findByAlpha3Code(String alpha3Code) throws PedistackException;
}
