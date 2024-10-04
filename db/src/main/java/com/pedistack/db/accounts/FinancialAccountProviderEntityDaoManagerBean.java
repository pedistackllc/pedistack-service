package com.pedistack.db.accounts;

import com.pedistack.common.exception.PedistackErrorDescriptions;
import com.pedistack.common.exception.PedistackException;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class FinancialAccountProviderEntityDaoManagerBean
    implements FinancialAccountProviderEntityDaoManager {

  private final FinancialAccountProviderEntityDao financialAccountProviderEntityDao;

  public FinancialAccountProviderEntityDaoManagerBean(
      FinancialAccountProviderEntityDao financialAccountProviderEntityDao) {
    this.financialAccountProviderEntityDao = financialAccountProviderEntityDao;
  }

  @Override
  public FinancialAccountProviderEntity save(
      FinancialAccountProviderEntity financialAccountProviderEntity) throws PedistackException {
    return financialAccountProviderEntityDao.save(financialAccountProviderEntity);
  }

  @Override
  public List<FinancialAccountProviderEntity> saveAll(
      List<FinancialAccountProviderEntity> financialAccountProviderEntities) {
    return financialAccountProviderEntityDao.saveAll(financialAccountProviderEntities);
  }

  @Override
  public void delete(FinancialAccountProviderEntity financialAccountProviderEntity)
      throws PedistackException {
    financialAccountProviderEntityDao.delete(financialAccountProviderEntity);
  }

  @Override
  public FinancialAccountProviderEntity findByIdentifier(String identifier)
      throws PedistackException {
    return financialAccountProviderEntityDao
        .findById(identifier)
        .orElseThrow(
            () ->
                PedistackException.createInternalErrorException(
                    PedistackErrorDescriptions.ACCOUNT_PROVIDER_NOT_FOUND_ERROR_DESCRIPTION));
  }

  @Override
  public Page<FinancialAccountProviderEntity> findAll(Pageable pageable) throws PedistackException {
    return financialAccountProviderEntityDao.findAll(pageable);
  }

  @Override
  public FinancialAccountProviderEntity findByAccountProviderCodeAndCountryAlpha2Code(
      String accountProviderCode, String countryAlpha2Code) throws PedistackException {
    return financialAccountProviderEntityDao
        .findByCodeAndCountry_Alpha2Code(accountProviderCode, countryAlpha2Code)
        .orElseThrow(
            () ->
                PedistackException.createInternalErrorException(
                    PedistackErrorDescriptions.ACCOUNT_PROVIDER_NOT_FOUND_ERROR_DESCRIPTION));
  }

  @Override
  public FinancialAccountProviderEntity findByAccountProviderCode(String accountProviderCode)
      throws PedistackException {
    return financialAccountProviderEntityDao
        .findByCode(accountProviderCode)
        .orElseThrow(
            () ->
                PedistackException.createInternalErrorException(
                    PedistackErrorDescriptions.ACCOUNT_PROVIDER_NOT_FOUND_ERROR_DESCRIPTION));
  }

  @Override
  public List<FinancialAccountProviderEntity> findByAccountProviderTypeAndCountryAlpha2Code(
      String accountProviderType, String countryAlpha2Code) {
    return financialAccountProviderEntityDao.findByTypeAndCountry_Alpha2Code(
        accountProviderType, countryAlpha2Code);
  }

  @Override
  public List<FinancialAccountProviderEntity> findByCountryAlpha2Code(String countryAlpha2Code) {
    return financialAccountProviderEntityDao.findByCountry_Alpha2Code(countryAlpha2Code);
  }

  @Override
  public void checkExistingAccountProviderCodeAndCountryAlpha2Code(
      String accountProviderCode, String countryAlpha2Code) throws PedistackException {
    if (financialAccountProviderEntityDao
        .findByCodeAndCountry_Alpha2Code(accountProviderCode, countryAlpha2Code)
        .isPresent()) {
      throw PedistackException.createInternalErrorException(
          PedistackErrorDescriptions.ACCOUNT_PROVIDER_ALREADY_REGISTERED_ERROR_DESCRIPTION);
    }
  }
}
