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
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class FinancialAccountOperationManagerBean implements FinancialAccountOperationManager {

  private final FinancialAccountEntityDaoManager financialAccountEntityDaoManager;
  private final CurrencyEntityDaoManager currencyEntityDaoManager;
  private final UserEntityDaoManager userEntityDaoManager;

  public FinancialAccountOperationManagerBean(
      FinancialAccountEntityDaoManager financialAccountEntityDaoManager,
      CurrencyEntityDaoManager currencyEntityDaoManager,
      UserEntityDaoManager userEntityDaoManager) {
    this.financialAccountEntityDaoManager = financialAccountEntityDaoManager;
    this.currencyEntityDaoManager = currencyEntityDaoManager;
    this.userEntityDaoManager = userEntityDaoManager;
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
    final List<FinancialAccountEntity> financialAccountEntityList =
        Arrays.stream(accountTypes)
            .map(
                accountType -> {
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
                  return financialAccountEntity;
                })
            .toList();
    financialAccountEntityDaoManager.saveAll(financialAccountEntityList);
  }

  @Override
  public List<FinancialAccount> userAccounts(
      String tenant,
      String sessionUserIdentifier,
      String sessionReference,
      String emailAddress,
      String mobileNumber,
      String username)
      throws PedistackException {
    UserEntity userEntity = null;
    if (emailAddress != null) {
      userEntity = userEntityDaoManager.findByEmailAddress(emailAddress);
    } else if (mobileNumber != null) {
      userEntity = userEntityDaoManager.findByMobileNumber(mobileNumber);
    } else if (username != null) {
      userEntity = userEntityDaoManager.findByUsername(username);
    } else {
      throw PedistackException.createInternalErrorException(
          PedistackErrorDescriptions.USER_NOT_FOUND);
    }
    final List<FinancialAccountEntity> financialAccountEntityList =
        financialAccountEntityDaoManager.userAccounts(userEntity.getId());
    return financialAccountEntityList.stream().map(this::createAccountResponse).toList();
  }

  private FinancialAccount createAccountResponse(FinancialAccountEntity financialAccountEntity) {
    final FinancialAccount financialAccount = new FinancialAccount();
    BeanUtils.copyProperties(financialAccountEntity, financialAccount);
    financialAccount.setStatus(
        FinancialAccountStatus.valueOf(financialAccountEntity.getStatus()));
    financialAccount.setType(
        FinancialAccountType.valueOf(financialAccountEntity.getType()));
    return financialAccount;
  }
}
