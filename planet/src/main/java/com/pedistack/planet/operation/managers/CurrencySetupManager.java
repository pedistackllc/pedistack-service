package com.pedistack.planet.operation.managers;

import com.pedistack.common.exception.PedistackException;
import com.pedistack.db.planet.CurrencyEntity;
import com.pedistack.db.planet.CurrencyEntityDaoManager;
import java.util.List;
import org.joda.money.CurrencyUnit;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class CurrencySetupManager implements ApplicationListener<ContextRefreshedEvent> {

  private final CurrencyEntityDaoManager currencyEntityDaoManager;

  public CurrencySetupManager(CurrencyEntityDaoManager currencyEntityDaoManager) {
    this.currencyEntityDaoManager = currencyEntityDaoManager;
  }

  @Override
  public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
    final List<CurrencyUnit> currencyLists = CurrencyUnit.registeredCurrencies();
    currencyLists.forEach(
        currencyUnit -> {
          try {
            if (currencyEntityDaoManager
                .findByCodeReturnOptional(currencyUnit.getCode())
                .isEmpty()) {
              final CurrencyEntity currencyEntity = new CurrencyEntity();
              currencyEntity.setCode(currencyUnit.getCode());
              currencyEntity.setName(currencyUnit.getCode());
              currencyEntity.setSymbol(currencyUnit.getSymbol());
              currencyEntity.setDescription(currencyUnit.getNumeric3Code());
              currencyEntityDaoManager.save(currencyEntity);
            }
          } catch (PedistackException e) {
            throw new RuntimeException(e);
          }
        });
  }
}
