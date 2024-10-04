package com.pedistack.planet.operation.managers;

import com.pedistack.common.exception.PedistackException;
import com.pedistack.common.io.PageResponse;
import com.pedistack.db.planet.CountryEntity;
import com.pedistack.db.planet.CountryEntityDaoManager;
import com.pedistack.db.planet.CurrencyEntity;
import com.pedistack.db.planet.CurrencyEntityDaoManager;
import com.pedistack.planet.v1_0.CountryRequest;
import com.pedistack.planet.v1_0.common.Country;
import com.pedistack.v1_0.common.LanguageCode;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class CountryOperationManagerBean implements CountryOperationManager {

  private final CountryEntityDaoManager countryEntityDaoManager;
  private final CurrencyEntityDaoManager currencyEntityDaoManager;

  public CountryOperationManagerBean(
      CountryEntityDaoManager countryEntityDaoManager,
      CurrencyEntityDaoManager currencyEntityDaoManager) {
    this.countryEntityDaoManager = countryEntityDaoManager;
    this.currencyEntityDaoManager = currencyEntityDaoManager;
  }

  @Override
  public Country findByAlpha2Code(
      String tenant, String sessionUserIdentifier, String sessionReference, String alpha2Code)
      throws PedistackException {
    final CountryEntity countryEntity = countryEntityDaoManager.findByAlpha2Code(alpha2Code);
    return createCountryResponse(countryEntity);
  }

  @Override
  public Country findByAlpha3Code(
      String tenant, String sessionUserIdentifier, String sessionReference, String alpha3Code)
      throws PedistackException {
    final CountryEntity countryEntity = countryEntityDaoManager.findByAlpha3Code(alpha3Code);
    return createCountryResponse(countryEntity);
  }

  @Override
  @Transactional
  public Country updateSupportedCurrencies(
      String tenant,
      String sessionUserIdentifier,
      String sessionReference,
      String countryIdentifier,
      List<String> currencyCodes,
      Boolean status)
      throws PedistackException {
    final CountryEntity countryEntity = countryEntityDaoManager.findByIdentifier(countryIdentifier);
    if (countryEntity.getCurrencies() == null) {
      countryEntity.setCurrencies(new ArrayList<>());
    }
    final List<CurrencyEntity> currencyEntities =
        currencyCodes.stream()
            .map(
                currencyCode -> {
                  try {
                    return currencyEntityDaoManager.findByCode(currencyCode);
                  } catch (PedistackException e) {
                    throw new RuntimeException(e);
                  }
                })
            .toList();
    if (status) {
      countryEntity.getCurrencies().addAll(currencyEntities);
    } else {
      countryEntity.getCurrencies().removeAll(currencyEntities);
    }
    countryEntityDaoManager.save(countryEntity);
    return createCountryResponse(countryEntity);
  }

  @Override
  @Transactional
  public Country updateSupportedLanguageCodes(
      String tenant,
      String sessionUserIdentifier,
      String sessionReference,
      String countryIdentifier,
      List<LanguageCode> languageCodes,
      Boolean status)
      throws PedistackException {
    final CountryEntity countryEntity = countryEntityDaoManager.findByIdentifier(countryIdentifier);
    if (countryEntity.getLanguageCodes() == null) {
      countryEntity.setLanguageCodes(new ArrayList<>());
    }
    if (status) {
      countryEntity
          .getLanguageCodes()
          .addAll(languageCodes.stream().map(LanguageCode::name).toList());
    } else {
      countryEntity
          .getLanguageCodes()
          .removeAll(languageCodes.stream().map(LanguageCode::name).toList());
    }
    return createCountryResponse(countryEntityDaoManager.save(countryEntity));
  }

  @Override
  @Transactional
  public Country create(String tenant,
      String sessionUserIdentifier, String sessionReference, CountryRequest countryRequest)
      throws PedistackException {
    countryEntityDaoManager.checkExistingName(countryRequest.getName());
    countryEntityDaoManager.checkExistingAlpha2Code(countryRequest.getAlpha2Code());
    if (countryRequest.getAlpha3Code() != null) {
      countryEntityDaoManager.checkExistingAlpha3Code(countryRequest.getAlpha3Code());
    }
    final CountryEntity countryEntity = new CountryEntity();
    countryEntity.setName(countryRequest.getName());
    countryEntity.setAlpha2Code(countryRequest.getAlpha2Code());
    countryEntity.setAlpha3Code(countryRequest.getAlpha3Code());
    countryEntity.setCurrencies(
        countryRequest.getCurrencyCodes().stream()
            .map(
                currencyCode -> {
                  try {
                    return currencyEntityDaoManager.findByCode(currencyCode);
                  } catch (PedistackException pedistackException) {
                    throw new RuntimeException(pedistackException);
                  }
                })
            .toList());
    countryEntity.setLanguageCodes(
        countryRequest.getLanguageCodes().stream().map(LanguageCode::name).toList());
    countryEntityDaoManager.save(countryEntity);
    return createCountryResponse(countryEntity);
  }

  @Override
  public Country get(String tenant,String sessionUserIdentifier, String sessionReference, String identifier)
      throws PedistackException {
    return createCountryResponse(countryEntityDaoManager.findByIdentifier(identifier));
  }

  @Override
  public PageResponse<Country> list(String tenant,
      String sessionUserIdentifier, String sessionReference, Integer page, Integer size)
      throws PedistackException {
    final Page<CountryEntity> countryEntityPage =
        countryEntityDaoManager.findAll(PageRequest.of(page, size));
    return PageResponse.create(
        countryEntityPage.stream().map(this::createCountryResponse).toList(),
        page,
        size,
        countryEntityPage.getTotalElements(),
        countryEntityPage.getTotalPages());
  }

  @Override
  @Transactional
  public Country update(String tenant,
      String sessionUserIdentifier,
      String sessionReference,
      String identifier,
      CountryRequest countryRequest)
      throws PedistackException {
    final CountryEntity countryEntity = countryEntityDaoManager.findByIdentifier(identifier);
    if (countryRequest.getName() != null) {
      countryEntityDaoManager.checkExistingName(countryRequest.getName());
      countryEntity.setName(countryRequest.getName());
    }
    if (countryRequest.getAlpha2Code() != null) {
      countryEntityDaoManager.checkExistingAlpha2Code(countryRequest.getAlpha2Code());
      countryEntity.setAlpha2Code(countryRequest.getAlpha2Code());
    }
    if (countryRequest.getAlpha3Code() != null) {
      countryEntityDaoManager.checkExistingAlpha3Code(countryRequest.getAlpha3Code());
      countryEntity.setAlpha3Code(countryRequest.getAlpha3Code());
    }
    countryEntityDaoManager.save(countryEntity);
    return createCountryResponse(countryEntity);
  }

  @Override
  @Transactional
  public void delete(String tenant,String sessionUserIdentifier, String sessionReference, String identifier)
      throws PedistackException {
    countryEntityDaoManager.delete(countryEntityDaoManager.findByAlpha2Code(identifier));
  }

  private Country createCountryResponse(CountryEntity countryEntity) {
    final Country country = new Country();
    country.setAlpha2Code(countryEntity.getAlpha2Code());
    country.setCurrencyCodes(
        countryEntity.getCurrencies().stream().map(CurrencyEntity::getCode).toList());
    country.setId(countryEntity.getId());
    country.setName(countryEntity.getName());
    country.setAlpha2Code(countryEntity.getAlpha2Code());
    country.setAlpha3Code(countryEntity.getAlpha3Code());
    return country;
  }
}
