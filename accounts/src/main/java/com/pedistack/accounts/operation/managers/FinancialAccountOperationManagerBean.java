package com.pedistack.accounts.operation.managers;

import com.pedistack.accounts.v1_0.common.FinancialAccount;
import com.pedistack.accounts.v1_0.common.FinancialAccountStatus;
import com.pedistack.accounts.v1_0.common.FinancialAccountType;
import com.pedistack.common.accounts.AccountStatus;
import com.pedistack.common.accounts.AccountType;
import com.pedistack.common.exception.PedistackErrorDescriptions;
import com.pedistack.common.exception.PedistackException;
import com.pedistack.db.accounts.FinancialAccountEntity;
import com.pedistack.db.accounts.FinancialAccountEntityDaoManager;
import com.pedistack.db.oauth.UserEntity;
import com.pedistack.db.oauth.UserEntityDaoManager;
import com.pedistack.db.planet.CurrencyEntity;
import com.pedistack.db.planet.CurrencyEntityDaoManager;
import com.pedistack.events.accounts.AccountStatusUpdatedEvent;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class FinancialAccountOperationManagerBean implements FinancialAccountOperationManager {

  private final FinancialAccountEntityDaoManager financialAccountEntityDaoManager;
  private final CurrencyEntityDaoManager currencyEntityDaoManager;
  private final UserEntityDaoManager userEntityDaoManager;
  private final ApplicationEventPublisher applicationEventPublisher;

  public FinancialAccountOperationManagerBean(
      FinancialAccountEntityDaoManager financialAccountEntityDaoManager,
      CurrencyEntityDaoManager currencyEntityDaoManager,
      UserEntityDaoManager userEntityDaoManager,
      ApplicationEventPublisher applicationEventPublisher) {
    this.financialAccountEntityDaoManager = financialAccountEntityDaoManager;
    this.currencyEntityDaoManager = currencyEntityDaoManager;
    this.userEntityDaoManager = userEntityDaoManager;
    this.applicationEventPublisher = applicationEventPublisher;
  }

  @Override
  public void createUserAccounts(
      String tenant,
      String sessionUserIdentifier,
      String sessionReference,
      String userIdentifier,
      String currencyCode,
      AccountType... accountTypes)
      throws PedistackException {
    final CurrencyEntity currencyEntity = currencyEntityDaoManager.findByCode(currencyCode);
    final UserEntity userEntity = userEntityDaoManager.findByIdentifier(userIdentifier);
    final List<FinancialAccountEntity> financialAccountEntityList = new ArrayList<>();
    for (AccountType accountType : accountTypes) {
      if (financialAccountEntityDaoManager
          .findByUserIdentifierAndAccountTypeAndCurrencyCodeReturnOptional(
              userIdentifier, accountType.name(), currencyCode)
          .isEmpty()) {
        final FinancialAccountEntity financialAccountEntity = new FinancialAccountEntity();
        financialAccountEntity.setAccountNumber(RandomStringUtils.randomNumeric(10));
        financialAccountEntity.setAvailableBalance(BigDecimal.ZERO);
        financialAccountEntity.setCurrency(currencyEntity);
        financialAccountEntity.setUser(userEntity);
        financialAccountEntity.setReservedBalance(BigDecimal.ZERO);
        financialAccountEntity.setCommittedBalance(BigDecimal.ZERO);
        financialAccountEntity.setStatus(AccountStatus.ACTIVE.name());
        financialAccountEntity.setType(accountType.name());
        financialAccountEntity.setName("ACCT:" + userEntity.getId() + "/" + accountType.name());
        financialAccountEntityList.add(financialAccountEntity);
      }
    }
    financialAccountEntityDaoManager.saveAll(financialAccountEntityList);
  }

  @Override
  public List<FinancialAccount> userAccounts(
      String tenant, String sessionUserIdentifier, String sessionReference)
      throws PedistackException {
    UserEntity userEntity = userEntityDaoManager.findByIdentifier(sessionUserIdentifier);
    final List<FinancialAccountEntity> financialAccountEntityList =
        financialAccountEntityDaoManager.userAccounts(userEntity.getId());
    return financialAccountEntityList.stream().map(this::createAccountResponse).toList();
  }

  @Override
  public List<FinancialAccount> addUserAccount(
      String tenant,
      String sessionUserIdentifier,
      String sessionReference,
      List<String> currencyCodes)
      throws PedistackException {
    for (String currencyCode : currencyCodes) {
      createUserAccounts(
          tenant,
          sessionUserIdentifier,
          sessionReference,
          sessionUserIdentifier,
          currencyCode,
          AccountType.MAIN,
          AccountType.LOYALTY);
    }
    return userAccounts(tenant, sessionUserIdentifier, sessionReference);
  }

  @Override
  @Transactional
  public void blockFinancialAccount(
      String tenant,
      String sessionUserIdentifier,
      String sessionReference,
      String accountIdentifier)
      throws PedistackException {
    final FinancialAccountEntity financialAccountEntity =
        financialAccountEntityDaoManager.findByIdentifier(accountIdentifier);
    financialAccountEntity.setStatus(AccountStatus.BLOCKED.name());
    financialAccountEntityDaoManager.save(financialAccountEntity);
    applicationEventPublisher.publishEvent(
        new AccountStatusUpdatedEvent(
            this,
            tenant,
            sessionUserIdentifier,
            sessionUserIdentifier,
            financialAccountEntity,
            AccountStatus.BLOCKED));
  }

  @Override
  @Transactional
  public void unblockFinancialAccount(
      String tenant,
      String sessionUserIdentifier,
      String sessionReference,
      String accountIdentifier)
      throws PedistackException {
    final FinancialAccountEntity financialAccountEntity =
        financialAccountEntityDaoManager.findByIdentifier(accountIdentifier);
    financialAccountEntity.setStatus(AccountStatus.ACTIVE.name());
    financialAccountEntityDaoManager.save(financialAccountEntity);
    applicationEventPublisher.publishEvent(
        new AccountStatusUpdatedEvent(
            this,
            tenant,
            sessionUserIdentifier,
            sessionUserIdentifier,
            financialAccountEntity,
            AccountStatus.ACTIVE));
  }

  @Override
  @Transactional
  public void closeFinancialAccount(
      String tenant,
      String sessionUserIdentifier,
      String sessionReference,
      String accountIdentifier)
      throws PedistackException {
    final FinancialAccountEntity financialAccountEntity =
        financialAccountEntityDaoManager.findByIdentifier(accountIdentifier);
    if (financialAccountEntity.getAvailableBalance().compareTo(BigDecimal.ZERO) != 0) {
      throw PedistackException.createInternalErrorException(
          PedistackErrorDescriptions.ACCOUNT_CLOSE_ERROR_DESCRIPTION);
    }
    financialAccountEntity.setStatus(AccountStatus.CLOSED.name());
    financialAccountEntityDaoManager.save(financialAccountEntity);
    applicationEventPublisher.publishEvent(
        new AccountStatusUpdatedEvent(
            this,
            tenant,
            sessionUserIdentifier,
            sessionUserIdentifier,
            financialAccountEntity,
            AccountStatus.CLOSED));
  }

  private FinancialAccount createAccountResponse(FinancialAccountEntity financialAccountEntity) {
    final FinancialAccount financialAccount = new FinancialAccount();
    BeanUtils.copyProperties(financialAccountEntity, financialAccount);
    financialAccount.setStatus(FinancialAccountStatus.valueOf(financialAccountEntity.getStatus()));
    financialAccount.setType(FinancialAccountType.valueOf(financialAccountEntity.getType()));
    financialAccount.setCurrencyCode(financialAccountEntity.getCurrency().getCode());
    return financialAccount;
  }
}
