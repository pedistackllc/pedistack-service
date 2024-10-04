package com.pedistack.db.accounts;

import com.pedistack.common.exception.PedistackErrorDescriptions;
import com.pedistack.common.exception.PedistackException;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class FinancialAccountEntityDaoManagerBean implements FinancialAccountEntityDaoManager {

  private final FinancialAccountEntityDao financialAccountEntityDao;

  public FinancialAccountEntityDaoManagerBean(FinancialAccountEntityDao financialAccountEntityDao) {
    this.financialAccountEntityDao = financialAccountEntityDao;
  }

  @Override
  public Optional<FinancialAccountEntity> findByAccountNumberReturnOptional(String accountNumber)
      throws PedistackException {
    return financialAccountEntityDao.findByAccountNumber(accountNumber);
  }

  @Override
  public FinancialAccountEntity findByAccountNumber(
      String accountNumber, Supplier<? extends PedistackException> supplier)
      throws PedistackException {
    return financialAccountEntityDao.findByAccountNumber(accountNumber).orElseThrow(supplier);
  }

  @Override
  public FinancialAccountEntity findByAccountNumber(String accountNumber) throws PedistackException {
    return financialAccountEntityDao
        .findByAccountNumber(accountNumber)
        .orElseThrow(
            () ->
                PedistackException.createInternalErrorException(
                    PedistackErrorDescriptions.ACCOUNT_NOT_FOUND_ERROR_DESCRIPTION));
  }

  @Override
  public List<FinancialAccountEntity> userAccounts(String userIdentifier) {
    return financialAccountEntityDao.findByUser_Id(userIdentifier);
  }

  @Override
  public Optional<FinancialAccountEntity> findByUserIdentifierAndAccountTypeReturnOptional(
      String userIdentifier, String accountType) throws PedistackException {
    return financialAccountEntityDao.findByUser_IdAndType(userIdentifier, accountType);
  }

  @Override
  public FinancialAccountEntity findByUserIdentifierAndAccountType(
      String userIdentifier, String accountType, Supplier<? extends PedistackException> supplier)
      throws PedistackException {
    return financialAccountEntityDao.findByUser_IdAndType(userIdentifier, accountType).orElseThrow(supplier);
  }

  @Override
  public FinancialAccountEntity findByUserIdentifierAndAccountType(String userIdentifier, String accountType)
      throws PedistackException {
    return financialAccountEntityDao
        .findByUser_IdAndType(userIdentifier, accountType)
        .orElseThrow(
            () ->
                PedistackException.createInternalErrorException(
                    PedistackErrorDescriptions.ACCOUNT_NOT_FOUND_ERROR_DESCRIPTION));
  }

  @Override
  public Optional<FinancialAccountEntity> findByUserIdentifierAndAccountTypeAndCurrencyCodeReturnOptional(
      String userIdentifier, String accountType, String currencyCode) throws PedistackException {
    return financialAccountEntityDao.findByUser_IdAndTypeAndCurrency_Code(
        userIdentifier, accountType, currencyCode);
  }

  @Override
  public FinancialAccountEntity findByUserIdentifierAndAccountTypeAndCurrencyCode(
      String userIdentifier,
      String accountType,
      String currencyCode,
      Supplier<? extends PedistackException> supplier)
      throws PedistackException {
    return financialAccountEntityDao
        .findByUser_IdAndTypeAndCurrency_Code(userIdentifier, accountType, currencyCode)
        .orElseThrow(supplier);
  }

  @Override
  public FinancialAccountEntity findByUserIdentifierAndAccountTypeAndCurrencyCode(
      String userIdentifier, String accountType, String currencyCode) throws PedistackException {
    return financialAccountEntityDao
        .findByUser_IdAndTypeAndCurrency_Code(userIdentifier, accountType, currencyCode)
        .orElseThrow(
            () ->
                PedistackException.createInternalErrorException(
                    PedistackErrorDescriptions.ACCOUNT_NOT_FOUND_ERROR_DESCRIPTION));
  }

  @Override
  public FinancialAccountEntity save(FinancialAccountEntity financialAccountEntity) throws PedistackException {
    return financialAccountEntityDao.save(financialAccountEntity);
  }

  @Override
  public List<FinancialAccountEntity> saveAll(List<FinancialAccountEntity> accountEntities) {
    return financialAccountEntityDao.saveAll(accountEntities);
  }

  @Override
  public void delete(FinancialAccountEntity financialAccountEntity) throws PedistackException {
    financialAccountEntityDao.delete(financialAccountEntity);
  }

  @Override
  public FinancialAccountEntity findByIdentifier(String identifier) throws PedistackException {
    return financialAccountEntityDao
        .findById(identifier)
        .orElseThrow(
            () ->
                PedistackException.createInternalErrorException(
                    PedistackErrorDescriptions.ACCOUNT_NOT_FOUND_ERROR_DESCRIPTION));
  }

  @Override
  public Page<FinancialAccountEntity> findAll(Pageable pageable) throws PedistackException {
    return financialAccountEntityDao.findAll(pageable);
  }
}
