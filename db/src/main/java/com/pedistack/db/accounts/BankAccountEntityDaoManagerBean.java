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
public class BankAccountEntityDaoManagerBean implements BankAccountEntityDaoManager {

  private final BankAccountEntityDao bankAccountEntityDao;

  public BankAccountEntityDaoManagerBean(BankAccountEntityDao bankAccountEntityDao) {
    this.bankAccountEntityDao = bankAccountEntityDao;
  }

  @Override
  public BankAccountEntity save(BankAccountEntity bankAccountEntity) throws PedistackException {
    return bankAccountEntityDao.save(bankAccountEntity);
  }

  @Override
  public List<BankAccountEntity> saveAll(List<BankAccountEntity> bankAccountEntities) {
    return bankAccountEntityDao.saveAll(bankAccountEntities);
  }

  @Override
  public void delete(BankAccountEntity bankAccountEntity) throws PedistackException {
    bankAccountEntityDao.delete(bankAccountEntity);
  }

  @Override
  public BankAccountEntity findByIdentifier(String identifier) throws PedistackException {
    return bankAccountEntityDao
        .findById(identifier)
        .orElseThrow(
            () ->
                PedistackException.createInternalErrorException(
                    PedistackErrorDescriptions.ACCOUNT_NOT_FOUND_ERROR_DESCRIPTION));
  }

  @Override
  public Page<BankAccountEntity> findAll(Pageable pageable) throws PedistackException {
    return bankAccountEntityDao.findAll(pageable);
  }

  @Override
  public List<BankAccountEntity> findByUserIdentifier(String userIdentifier) {
    return bankAccountEntityDao.findByUser_Id(userIdentifier);
  }

  @Override
  public Optional<BankAccountEntity>
      findByUserIdentifierAndBankAccountNumberAndAccountProviderCodeReturnOptional(
          String userIdentifier, String bankAccountNumber, String accountProviderCode)
          throws PedistackException {
    return bankAccountEntityDao.findByUser_IdAndAccountNumberAndAccountProvider_Code(
        userIdentifier, bankAccountNumber, accountProviderCode);
  }

  @Override
  public BankAccountEntity findByUserIdentifierAndBankAccountNumberAndAccountProviderCode(
      String userIdentifier,
      String bankAccountNumber,
      String accountProviderCode,
      Supplier<? extends PedistackException> supplier)
      throws PedistackException {
    return bankAccountEntityDao
        .findByUser_IdAndAccountNumberAndAccountProvider_Code(
            userIdentifier, bankAccountNumber, accountProviderCode)
        .orElseThrow(supplier);
  }

  @Override
  public BankAccountEntity findByUserIdentifierAndBankAccountNumberAndAccountProviderCode(
      String userIdentifier, String bankAccountNumber, String accountProviderCode)
      throws PedistackException {
    return bankAccountEntityDao
        .findByUser_IdAndAccountNumberAndAccountProvider_Code(
            userIdentifier, bankAccountNumber, accountProviderCode)
        .orElseThrow(
            () ->
                PedistackException.createInternalErrorException(
                    PedistackErrorDescriptions.ACCOUNT_NOT_FOUND_ERROR_DESCRIPTION));
  }

  @Override
  public void checkExistingBankAccount(
      String userIdentifier, String bankAccountNumber, String accountProviderCode)
      throws PedistackException {
    if (bankAccountEntityDao
        .findByUser_IdAndAccountNumberAndAccountProvider_Code(
            userIdentifier, bankAccountNumber, accountProviderCode)
        .isPresent()) {
      throw PedistackException.createInternalErrorException(
          PedistackErrorDescriptions.ACCOUNT_ALREADY_REGISTERED_ERROR_DESCRIPTION);
    }
  }
}
