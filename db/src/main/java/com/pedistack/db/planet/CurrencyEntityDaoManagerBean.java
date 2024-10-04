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
public class CurrencyEntityDaoManagerBean implements CurrencyEntityDaoManager {

  private final CurrencyEntityDao currencyEntityDao;

  public CurrencyEntityDaoManagerBean(CurrencyEntityDao currencyEntityDao) {
    this.currencyEntityDao = currencyEntityDao;
  }

  @Override
  public Optional<CurrencyEntity> findByNameReturnOptional(String name) throws PedistackException {
    return currencyEntityDao.findByName(name);
  }

  @Override
  public CurrencyEntity findByName(String name, Supplier<? extends PedistackException> supplier)
      throws PedistackException {
    return currencyEntityDao.findByName(name).orElseThrow(supplier);
  }

  @Override
  public CurrencyEntity findByName(String name) throws PedistackException {
    return currencyEntityDao
        .findByName(name)
        .orElseThrow(
            () ->
                PedistackException.createInternalErrorException(
                    PedistackErrorDescriptions.CURRENCY_NOT_FOUND_ERROR_DESCRIPTION));
  }

  @Override
  public Optional<CurrencyEntity> findByCodeReturnOptional(String code) throws PedistackException {
    return currencyEntityDao.findByCode(code);
  }

  @Override
  public CurrencyEntity findByCode(String code, Supplier<? extends PedistackException> supplier)
      throws PedistackException {
    return currencyEntityDao.findByCode(code).orElseThrow(supplier);
  }

  @Override
  public CurrencyEntity findByCode(String code) throws PedistackException {
    return currencyEntityDao
        .findByCode(code)
        .orElseThrow(
            () ->
                PedistackException.createInternalErrorException(
                    PedistackErrorDescriptions.CURRENCY_NOT_FOUND_ERROR_DESCRIPTION));
  }

  @Override
  public Optional<CurrencyEntity> findBySymbolReturnOptional(String symbol)
      throws PedistackException {
    return currencyEntityDao.findBySymbol(symbol);
  }

  @Override
  public CurrencyEntity findBySymbol(String symbol, Supplier<? extends PedistackException> supplier)
      throws PedistackException {
    return currencyEntityDao.findBySymbol(symbol).orElseThrow(supplier);
  }

  @Override
  public CurrencyEntity findBySymbol(String symbol) throws PedistackException {
    return currencyEntityDao
        .findBySymbol(symbol)
        .orElseThrow(
            () ->
                PedistackException.createInternalErrorException(
                    PedistackErrorDescriptions.CURRENCY_NOT_FOUND_ERROR_DESCRIPTION));
  }

  @Override
  public void checkExistingName(String name) throws PedistackException {
    if (currencyEntityDao.findByName(name).isPresent()) {
      throw PedistackException.createInternalErrorException(
          PedistackErrorDescriptions.CURRENCY_ALREADY_REGISTERED_ERROR_DESCRIPTION);
    }
  }

  @Override
  public void checkExistingCode(String code) throws PedistackException {
    if (currencyEntityDao.findByCode(code).isPresent()) {
      throw PedistackException.createInternalErrorException(
          PedistackErrorDescriptions.CURRENCY_ALREADY_REGISTERED_ERROR_DESCRIPTION);
    }
  }

  @Override
  public CurrencyEntity save(CurrencyEntity currencyEntity) throws PedistackException {
    return currencyEntityDao.save(currencyEntity);
  }

  @Override
  public List<CurrencyEntity> saveAll(List<CurrencyEntity> currencyEntityList) {
    return currencyEntityDao.saveAll(currencyEntityList);
  }

  @Override
  public void delete(CurrencyEntity currencyEntity) throws PedistackException {
    currencyEntityDao.delete(currencyEntity);
  }

  @Override
  public CurrencyEntity findByIdentifier(String identifier) throws PedistackException {
    return currencyEntityDao
        .findById(identifier)
        .orElseThrow(
            () ->
                PedistackException.createInternalErrorException(
                    PedistackErrorDescriptions.CURRENCY_NOT_FOUND_ERROR_DESCRIPTION));
  }

  @Override
  public Page<CurrencyEntity> findAll(Pageable pageable) throws PedistackException {
    return currencyEntityDao.findAll(pageable);
  }
}
