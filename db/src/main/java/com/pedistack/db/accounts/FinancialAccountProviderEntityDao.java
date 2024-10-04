package com.pedistack.db.accounts;

import com.pedistack.common.db.BaseDao;
import com.pedistack.common.exception.PedistackException;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public interface FinancialAccountProviderEntityDao extends BaseDao<FinancialAccountProviderEntity> {

  Optional<FinancialAccountProviderEntity> findByCodeAndCountry_Alpha2Code(String code,String countryAlpha2Code) throws PedistackException;
  Optional<FinancialAccountProviderEntity> findByCode(String code) throws PedistackException;

  List<FinancialAccountProviderEntity> findByTypeAndCountry_Alpha2Code(String type,String countryAlpha2Code);
  List<FinancialAccountProviderEntity> findByCountry_Alpha2Code(String countryAlpha2Code);
}
