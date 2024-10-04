package com.pedistack.accounts.operation.managers;

import com.pedistack.accounts.v1_0.FinancialAccountProviderRequest;
import com.pedistack.accounts.v1_0.common.FinancialAccountProvider;
import com.pedistack.accounts.v1_0.common.FinancialAccountProviderType;
import com.pedistack.common.exception.PedistackException;
import com.pedistack.common.io.PageResponse;
import com.pedistack.db.accounts.FinancialAccountProviderEntity;
import com.pedistack.db.accounts.FinancialAccountProviderEntityDaoManager;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class FinancialAccountProviderOperationManagerBean
    implements FinancialAccountProviderOperationManager {

  private final FinancialAccountProviderEntityDaoManager financialAccountProviderEntityDaoManager;

  public FinancialAccountProviderOperationManagerBean(
      FinancialAccountProviderEntityDaoManager financialAccountProviderEntityDaoManager) {
    this.financialAccountProviderEntityDaoManager = financialAccountProviderEntityDaoManager;
  }

  @Override
  public List<FinancialAccountProvider> listByFinancialAccountProviderType(
      String tenant,
      String sessionUserIdentifier,
      String sessionReference,
      FinancialAccountProviderType financialAccountProviderType,
      String countryCode) {
    final List<FinancialAccountProviderEntity> financialAccountProviderEntityList =
        financialAccountProviderEntityDaoManager.findByAccountProviderTypeAndCountryAlpha2Code(
            financialAccountProviderType.name(), countryCode);
    return financialAccountProviderEntityList.stream()
        .map(this::createFinancialAccountProviderResponse)
        .toList();
  }

  @Override
  public List<FinancialAccountProvider> listByFinancialAccountProviderType(
      String tenant, String sessionUserIdentifier, String sessionReference, String countryCode) {
    final List<FinancialAccountProviderEntity> financialAccountProviderEntityList =
        financialAccountProviderEntityDaoManager.findByCountryAlpha2Code(countryCode);
    return financialAccountProviderEntityList.stream()
        .map(this::createFinancialAccountProviderResponse)
        .toList();
  }

  @Override
  @Transactional
  public FinancialAccountProvider create(String tenant,
      String sessionUserIdentifier,
      String sessionReference,
      FinancialAccountProviderRequest financialAccountProviderRequest)
      throws PedistackException {
    financialAccountProviderEntityDaoManager.checkExistingAccountProviderCodeAndCountryAlpha2Code(
        financialAccountProviderRequest.getCode(),
        financialAccountProviderRequest.getCountryCode());
    final FinancialAccountProviderEntity financialAccountProviderEntity =
        new FinancialAccountProviderEntity();
    financialAccountProviderEntity.setCode(financialAccountProviderRequest.getCode());
    financialAccountProviderEntity.setDescription(financialAccountProviderRequest.getDescription());
    financialAccountProviderEntity.setType(financialAccountProviderRequest.getType().name());
    financialAccountProviderEntity.setName(financialAccountProviderRequest.getName());
    financialAccountProviderEntity.setCreatedUserId(sessionUserIdentifier);
    financialAccountProviderEntityDaoManager.save(financialAccountProviderEntity);
    return createFinancialAccountProviderResponse(financialAccountProviderEntity);
  }

  @Override
  public FinancialAccountProvider get(String tenant,
      String sessionUserIdentifier, String sessionReference, String identifier)
      throws PedistackException {
    return createFinancialAccountProviderResponse(
        financialAccountProviderEntityDaoManager.findByIdentifier(identifier));
  }

  @Override
  public PageResponse<FinancialAccountProvider> list(String tenant,
      String sessionUserIdentifier, String sessionReference, Integer page, Integer size)
      throws PedistackException {
    final Page<FinancialAccountProviderEntity> financialAccountProviderEntityPage =
        financialAccountProviderEntityDaoManager.findAll(PageRequest.of(page, size));
    return PageResponse.create(
        financialAccountProviderEntityPage.stream()
            .map(this::createFinancialAccountProviderResponse)
            .toList(),
        page,
        size,
        financialAccountProviderEntityPage.getTotalElements(),
        financialAccountProviderEntityPage.getTotalPages());
  }

  @Override
  @Transactional
  public FinancialAccountProvider update(String tenant,
      String sessionUserIdentifier,
      String sessionReference,
      String identifier,
      FinancialAccountProviderRequest financialAccountProviderRequest)
      throws PedistackException {
    final FinancialAccountProviderEntity financialAccountProviderEntity =
        financialAccountProviderEntityDaoManager.findByIdentifier(identifier);
    if (financialAccountProviderRequest.getCode() != null) {
      financialAccountProviderEntityDaoManager.checkExistingAccountProviderCodeAndCountryAlpha2Code(
          financialAccountProviderRequest.getCode(),
          financialAccountProviderEntity.getCountry().getAlpha2Code());
      financialAccountProviderEntity.setCode(financialAccountProviderRequest.getCode());
    }
    if (financialAccountProviderRequest.getName() != null) {
      financialAccountProviderEntity.setName(financialAccountProviderRequest.getName());
    }
    if (financialAccountProviderRequest.getDescription() != null) {
      financialAccountProviderEntity.setDescription(
          financialAccountProviderRequest.getDescription());
    }
    if (financialAccountProviderRequest.getType() != null) {
      financialAccountProviderEntity.setType(financialAccountProviderRequest.getType().name());
    }
    financialAccountProviderEntityDaoManager.save(financialAccountProviderEntity);
    return createFinancialAccountProviderResponse(financialAccountProviderEntity);
  }

  @Override
  @Transactional
  public void delete(String tenant,String sessionUserIdentifier, String sessionReference, String identifier)
      throws PedistackException {
    financialAccountProviderEntityDaoManager.delete(
        financialAccountProviderEntityDaoManager.findByIdentifier(identifier));
  }

  private FinancialAccountProvider createFinancialAccountProviderResponse(
      FinancialAccountProviderEntity financialAccountProviderEntity) {
    final FinancialAccountProvider financialAccountProvider = new FinancialAccountProvider();
    financialAccountProvider.setId(financialAccountProviderEntity.getId());
    financialAccountProvider.setName(financialAccountProviderEntity.getName());
    financialAccountProvider.setDescription(financialAccountProviderEntity.getDescription());
    financialAccountProvider.setType(
        FinancialAccountProviderType.valueOf(financialAccountProviderEntity.getType()));
    financialAccountProvider.setCode(financialAccountProviderEntity.getCode());
    return financialAccountProvider;
  }
}
