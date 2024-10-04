package com.pedistack.planet.operation.managers;

import com.pedistack.common.exception.PedistackException;
import com.pedistack.common.io.PageResponse;
import com.pedistack.db.planet.CurrencyEntity;
import com.pedistack.db.planet.CurrencyEntityDaoManager;
import com.pedistack.planet.v1_0.CurrencyRequest;
import com.pedistack.planet.v1_0.common.Currency;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class CurrencyOperationManagerBean implements CurrencyOperationManager {

  private final CurrencyEntityDaoManager currencyEntityDaoManager;

  public CurrencyOperationManagerBean(CurrencyEntityDaoManager currencyEntityDaoManager) {
    this.currencyEntityDaoManager = currencyEntityDaoManager;
  }

  @Override
  @Transactional
  public Currency create(String tenant,
      String sessionUserIdentifier, String sessionReference, CurrencyRequest currencyRequest)
      throws PedistackException {
    currencyEntityDaoManager.checkExistingName(currencyRequest.getName());
    currencyEntityDaoManager.checkExistingCode(currencyRequest.getCode());
    final CurrencyEntity currencyEntity = new CurrencyEntity();
    currencyEntity.setName(currencyRequest.getName());
    currencyEntity.setCode(currencyRequest.getCode());
    currencyEntity.setDescription(currencyRequest.getDescription());
    currencyEntity.setSymbol(currencyRequest.getSymbol());
    currencyEntityDaoManager.save(currencyEntity);
    return createCurrencyResponse(currencyEntity);
  }

  @Override
  public Currency get(String tenant,String sessionUserIdentifier, String sessionReference, String identifier)
      throws PedistackException {
    return createCurrencyResponse(currencyEntityDaoManager.findByIdentifier(identifier));
  }

  @Override
  public PageResponse<Currency> list(String tenant,
      String sessionUserIdentifier, String sessionReference, Integer page, Integer size)
      throws PedistackException {
    final Page<CurrencyEntity> currencyEntityPage =
        currencyEntityDaoManager.findAll(PageRequest.of(page, size));
    return PageResponse.create(
        currencyEntityPage.stream().map(this::createCurrencyResponse).toList(),
        page,
        size,
        currencyEntityPage.getTotalElements(),
        currencyEntityPage.getTotalPages());
  }

  @Override
  @Transactional
  public Currency update(String tenant,
      String sessionUserIdentifier,
      String sessionReference,
      String identifier,
      CurrencyRequest currencyRequest)
      throws PedistackException {
    final CurrencyEntity currencyEntity = currencyEntityDaoManager.findByIdentifier(identifier);
    if (currencyRequest.getName() != null) {
      currencyEntityDaoManager.checkExistingName(currencyRequest.getName());
      currencyEntity.setName(currencyRequest.getName());
    }
    if (currencyRequest.getCode() != null) {
      currencyEntityDaoManager.checkExistingCode(currencyRequest.getCode());
      currencyEntity.setCode(currencyRequest.getCode());
    }
    if (currencyRequest.getDescription() != null) {
      currencyEntity.setDescription(currencyRequest.getDescription());
    }
    if (currencyRequest.getSymbol() != null) {
      currencyEntity.setSymbol(currencyRequest.getSymbol());
    }
    currencyEntityDaoManager.save(currencyEntity);
    return createCurrencyResponse(currencyEntity);
  }

  @Override
  @Transactional
  public void delete(String tenant,String sessionUserIdentifier, String sessionReference, String identifier)
      throws PedistackException {
    currencyEntityDaoManager.delete(currencyEntityDaoManager.findByIdentifier(identifier));
  }

  @Override
  public Currency findByCode(String sessionUserIdentifier, String sessionReference, String code)
      throws PedistackException {
    return createCurrencyResponse(currencyEntityDaoManager.findByCode(code));
  }

  private Currency createCurrencyResponse(CurrencyEntity currencyEntity) {
    final Currency currency = new Currency();
    currency.setCode(currencyEntity.getCode());
    currency.setName(currencyEntity.getName());
    currency.setDescription(currencyEntity.getDescription());
    currency.setId(currencyEntity.getId());
    currency.setSymbol(currencyEntity.getSymbol());
    return currency;
  }
}
