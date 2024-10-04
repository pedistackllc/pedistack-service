package com.pedistack.db.accounts;

import com.pedistack.common.db.BaseDaoManager;
import com.pedistack.common.exception.PedistackException;

import java.util.List;

public interface FinancialAccountProviderEntityDaoManager
    extends BaseDaoManager<FinancialAccountProviderEntity, FinancialAccountProviderEntityDao> {

  FinancialAccountProviderEntity findByAccountProviderCodeAndCountryAlpha2Code(
      String accountProviderCode, String countryAlpha2Code) throws PedistackException;

  FinancialAccountProviderEntity findByAccountProviderCode(
          String accountProviderCode) throws PedistackException;

  List<FinancialAccountProviderEntity> findByAccountProviderTypeAndCountryAlpha2Code(
      String accountProviderType, String countryAlpha2Code);

  List<FinancialAccountProviderEntity> findByCountryAlpha2Code(String countryAlpha2Code);

  void checkExistingAccountProviderCodeAndCountryAlpha2Code(
      String accountProviderCode, String countryAlpha2Code) throws PedistackException;
}
