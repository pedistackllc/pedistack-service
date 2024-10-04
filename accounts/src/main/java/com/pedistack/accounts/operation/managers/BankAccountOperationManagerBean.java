package com.pedistack.accounts.operation.managers;

import com.pedistack.accounts.v1_0.BankAccountRequest;
import com.pedistack.accounts.v1_0.common.BankAccount;
import com.pedistack.common.accounts.BankAccountStatus;
import com.pedistack.common.exception.PedistackException;
import com.pedistack.common.io.PageResponse;
import com.pedistack.db.accounts.BankAccountEntity;
import com.pedistack.db.accounts.BankAccountEntityDaoManager;
import com.pedistack.db.accounts.FinancialAccountProviderEntityDaoManager;
import com.pedistack.db.oauth.UserEntityDaoManager;
import com.pedistack.events.accounts.BankAccountVerificationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class BankAccountOperationManagerBean implements BankAccountOperationManager {

  private final BankAccountEntityDaoManager bankAccountEntityDaoManager;
  private final UserEntityDaoManager userEntityDaoManager;
  private final FinancialAccountProviderEntityDaoManager financialAccountProviderEntityDaoManager;
  private final ApplicationEventPublisher applicationEventPublisher;

  public BankAccountOperationManagerBean(
      BankAccountEntityDaoManager bankAccountEntityDaoManager,
      UserEntityDaoManager userEntityDaoManager,
      ApplicationEventPublisher applicationEventPublisher,
      FinancialAccountProviderEntityDaoManager financialAccountProviderEntityDaoManager) {
    this.bankAccountEntityDaoManager = bankAccountEntityDaoManager;
    this.userEntityDaoManager = userEntityDaoManager;
    this.applicationEventPublisher = applicationEventPublisher;
    this.financialAccountProviderEntityDaoManager = financialAccountProviderEntityDaoManager;
  }

  @Override
  @Transactional
  public BankAccount create(String tenant,
      String sessionUserIdentifier, String sessionReference, BankAccountRequest bankAccountRequest)
      throws PedistackException {
    bankAccountEntityDaoManager.checkExistingBankAccount(
        sessionUserIdentifier,
        bankAccountRequest.getAccountNumber(),
        bankAccountRequest.getAccountProviderCode());
    final BankAccountEntity bankAccountEntity = new BankAccountEntity();
    bankAccountEntity.setAccountNumber(bankAccountRequest.getAccountNumber());
    bankAccountEntity.setDescription(bankAccountRequest.getDescription());
    bankAccountEntity.setName(bankAccountRequest.getName());
    bankAccountEntity.setUser(userEntityDaoManager.findByIdentifier(sessionUserIdentifier));
    bankAccountEntity.setStatus(BankAccountStatus.UNVERIFIED.name());
    bankAccountEntity.setAccountProvider(
        financialAccountProviderEntityDaoManager.findByAccountProviderCode(
            bankAccountRequest.getAccountProviderCode()));
    bankAccountEntity.setCreatedUserId(sessionUserIdentifier);
    bankAccountEntityDaoManager.save(bankAccountEntity);
    final BankAccount bankAccount = createBankAccountResponse(bankAccountEntity);
    applicationEventPublisher.publishEvent(
        new BankAccountVerificationEvent(
            this,
            null,
            sessionUserIdentifier,
            sessionReference,
            bankAccountRequest,
            bankAccount,
            bankAccountEntity));
    return bankAccount;
  }

  @Override
  public BankAccount get(String tenant,String sessionUserIdentifier, String sessionReference, String identifier)
      throws PedistackException {
    return createBankAccountResponse(bankAccountEntityDaoManager.findByIdentifier(identifier));
  }

  @Override
  public PageResponse<BankAccount> list(String tenant,
      String sessionUserIdentifier, String sessionReference, Integer page, Integer size)
      throws PedistackException {
    final Page<BankAccountEntity> bankAccountEntityPage =
        bankAccountEntityDaoManager.findAll(PageRequest.of(page, size));
    return PageResponse.create(
        bankAccountEntityPage.stream().map(this::createBankAccountResponse).toList(),
        page,
        size,
        bankAccountEntityPage.getTotalElements(),
        bankAccountEntityPage.getTotalPages());
  }

  @Override
  @Transactional
  public BankAccount update(String tenant,
      String sessionUserIdentifier,
      String sessionReference,
      String identifier,
      BankAccountRequest bankAccountRequest)
      throws PedistackException {
    final BankAccountEntity bankAccountEntity =
        bankAccountEntityDaoManager.findByIdentifier(identifier);
    if (bankAccountRequest.getAccountNumber() != null) {
      bankAccountEntity.setAccountNumber(bankAccountRequest.getAccountNumber());
      bankAccountEntity.setStatus(BankAccountStatus.UNVERIFIED.name());
    }
    if (bankAccountRequest.getDescription() != null) {
      bankAccountEntity.setDescription(bankAccountRequest.getDescription());
    }
    if (bankAccountRequest.getName() != null) {
      bankAccountEntity.setName(bankAccountRequest.getName());
    }
    if (bankAccountRequest.getAccountProviderCode() != null) {
      bankAccountEntity.setAccountProvider(
          financialAccountProviderEntityDaoManager.findByAccountProviderCode(
              bankAccountRequest.getAccountProviderCode()));
      bankAccountEntity.setStatus(BankAccountStatus.UNVERIFIED.name());
    }
    bankAccountEntityDaoManager.save(bankAccountEntity);
    final BankAccount bankAccount = createBankAccountResponse(bankAccountEntity);
    if (bankAccountEntity.getStatus().equals(BankAccountStatus.UNVERIFIED.name())) {
      applicationEventPublisher.publishEvent(
          new BankAccountVerificationEvent(
              this,
              null,
              sessionUserIdentifier,
              sessionReference,
              bankAccountRequest,
              bankAccount,
              bankAccountEntity));
    }
    return bankAccount;
  }

  @Override
  @Transactional
  public void delete(String tenant,String sessionUserIdentifier, String sessionReference, String identifier)
      throws PedistackException {
    bankAccountEntityDaoManager.delete(bankAccountEntityDaoManager.findByIdentifier(identifier));
  }

  private BankAccount createBankAccountResponse(BankAccountEntity bankAccountEntity) {
    final BankAccount bankAccount = new BankAccount();
    bankAccount.setId(bankAccountEntity.getId());
    bankAccount.setAccountNumber(bankAccountEntity.getAccountNumber());
    bankAccount.setAccountProviderCode(bankAccountEntity.getAccountProvider().getCode());
    bankAccount.setAccountProviderName(bankAccountEntity.getAccountProvider().getName());
    bankAccount.setName(bankAccountEntity.getName());
    bankAccount.setDescription(bankAccountEntity.getDescription());
    return bankAccount;
  }
}
