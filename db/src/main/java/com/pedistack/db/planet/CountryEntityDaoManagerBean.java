package com.pedistack.db.planet;

import com.pedistack.common.exception.PedistackErrorDescriptions;
import com.pedistack.common.exception.PedistackException;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class CountryEntityDaoManagerBean implements CountryEntityDaoManager {

  private final CountryEntityDao countryEntityDao;

  public CountryEntityDaoManagerBean(CountryEntityDao countryEntityDao) {
    this.countryEntityDao = countryEntityDao;
  }

  @Override
  public Optional<CountryEntity> findByNameReturnOptional(String name) throws PedistackException {
    return countryEntityDao.findByName(name);
  }

  @Override
  public CountryEntity findByName(String name, Supplier<? extends PedistackException> supplier)
      throws PedistackException {
    return countryEntityDao.findByName(name).orElseThrow(supplier);
  }

  @Override
  public CountryEntity findByName(String name) throws PedistackException {
    return countryEntityDao
        .findByName(name)
        .orElseThrow(
            () ->
                PedistackException.createInternalErrorException(
                    PedistackErrorDescriptions.COUNTRY_NOT_FOUND_ERROR_DESCRIPTION));
  }

  @Override
  public Optional<CountryEntity> findByAlpha2CodeReturnOptional(String alpha2Code)
      throws PedistackException {
    return countryEntityDao.findByAlpha2Code(alpha2Code);
  }

  @Override
  public CountryEntity findByAlpha2Code(
      String alpha2Code, Supplier<? extends PedistackException> supplier)
      throws PedistackException {
    return countryEntityDao.findByAlpha2Code(alpha2Code).orElseThrow(supplier);
  }

  @Override
  public CountryEntity findByAlpha2Code(String alpha2Code) throws PedistackException {
    return countryEntityDao
        .findByAlpha2Code(alpha2Code)
        .orElseThrow(
            () ->
                PedistackException.createInternalErrorException(
                    PedistackErrorDescriptions.COUNTRY_NOT_FOUND_ERROR_DESCRIPTION));
  }

  @Override
  public Optional<CountryEntity> findByAlpha3CodeReturnOptional(String alpha3Code)
      throws PedistackException {
    return countryEntityDao.findByAlpha3Code(alpha3Code);
  }

  @Override
  public CountryEntity findByAlpha3Code(
      String alpha3Code, Supplier<? extends PedistackException> supplier)
      throws PedistackException {
    return countryEntityDao.findByAlpha3Code(alpha3Code).orElseThrow(supplier);
  }

  @Override
  public CountryEntity findByAlpha3Code(String alpha3Code) throws PedistackException {
    return countryEntityDao
        .findByAlpha3Code(alpha3Code)
        .orElseThrow(
            () ->
                PedistackException.createInternalErrorException(
                    PedistackErrorDescriptions.COUNTRY_NOT_FOUND_ERROR_DESCRIPTION));
  }

  @Override
  public void checkExistingName(String name) throws PedistackException {
    if (countryEntityDao.findByName(name).isPresent()) {
      throw PedistackException.createInternalErrorException(
          PedistackErrorDescriptions.COUNTRY_ALREADY_REGISTERED_ERROR_DESCRIPTION);
    }
  }

  @Override
  public void checkExistingAlpha2Code(String alpha2Code) throws PedistackException {
    if (countryEntityDao.findByAlpha2Code(alpha2Code).isPresent()) {
      throw PedistackException.createInternalErrorException(
          PedistackErrorDescriptions.COUNTRY_ALREADY_REGISTERED_ERROR_DESCRIPTION);
    }
  }

  @Override
  public void checkExistingAlpha3Code(String alpha3Code) throws PedistackException {
    if (countryEntityDao.findByAlpha3Code(alpha3Code).isPresent()) {
      throw PedistackException.createInternalErrorException(
          PedistackErrorDescriptions.COUNTRY_ALREADY_REGISTERED_ERROR_DESCRIPTION);
    }
  }

  @Override
  public CountryEntity save(CountryEntity countryEntity) throws PedistackException {
    return countryEntityDao.save(countryEntity);
  }

  @Override
  public List<CountryEntity> saveAll(List<CountryEntity> countryEntityList) {
    return countryEntityDao.saveAll(countryEntityList);
  }

  @Override
  public void delete(CountryEntity countryEntity) throws PedistackException {
    countryEntityDao.delete(countryEntity);
  }

  @Override
  public CountryEntity findByIdentifier(String identifier) throws PedistackException {
    return countryEntityDao
        .findById(identifier)
        .orElseThrow(
            () ->
                PedistackException.createInternalErrorException(
                    PedistackErrorDescriptions.COUNTRY_NOT_FOUND_ERROR_DESCRIPTION));
  }

  @Override
  public Page<CountryEntity> findAll(Pageable pageable) throws PedistackException {
    return countryEntityDao.findAll(pageable);
  }
}
