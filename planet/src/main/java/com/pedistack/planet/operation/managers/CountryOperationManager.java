package com.pedistack.planet.operation.managers;

import com.pedistack.common.exception.PedistackException;
import com.pedistack.common.managers.CrudOperationManager;
import com.pedistack.planet.v1_0.CountryRequest;
import com.pedistack.planet.v1_0.common.Country;
import com.pedistack.v1_0.common.LanguageCode;

import java.util.List;

public interface CountryOperationManager extends CrudOperationManager<CountryRequest, Country> {

  Country findByAlpha2Code(
      String tenant, String sessionUserIdentifier, String sessionReference, String alpha2Code)
      throws PedistackException;

  Country findByAlpha3Code(
      String tenant, String sessionUserIdentifier, String sessionReference, String alpha3Code)
      throws PedistackException;

  Country updateSupportedCurrencies(
      String tenant,
      String sessionUserIdentifier,
      String sessionReference,
      String countryIdentifier,
      List<String> currencyCodes,
      Boolean status)
      throws PedistackException;

  Country updateSupportedLanguageCodes(
      String tenant,
      String sessionUserIdentifier,
      String sessionReference,
      String countryIdentifier,
      List<LanguageCode> languageCodes,
      Boolean status)
      throws PedistackException;
}
