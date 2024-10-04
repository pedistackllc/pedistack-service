package com.pedistack.planet.operation.managers;

import com.pedistack.common.exception.PedistackException;
import com.pedistack.common.managers.CrudOperationManager;
import com.pedistack.planet.v1_0.CurrencyRequest;
import com.pedistack.planet.v1_0.common.Currency;

public interface CurrencyOperationManager extends CrudOperationManager<CurrencyRequest, Currency> {

    Currency findByCode(String sessionUserIdentifier,String sessionReference,String code) throws PedistackException;

}
