package com.pedistack.db.accounts;

import com.pedistack.common.exception.PedistackErrorDescriptions;
import com.pedistack.common.exception.PedistackException;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class CardAccountEntityDaoManagerBean implements CardAccountEntityDaoManager {

  private final CardAccountEntityDao cardAccountEntityDao;

  public CardAccountEntityDaoManagerBean(CardAccountEntityDao cardAccountEntityDao) {
    this.cardAccountEntityDao = cardAccountEntityDao;
  }

  @Override
  public List<CardAccountEntity> findByUserIdentifier(String userIdentifier) {
    return cardAccountEntityDao.findByUser_Id(userIdentifier);
  }

  @Override
  public void checkExistingCard(String last4) throws PedistackException {
    if (cardAccountEntityDao.findByLast4(last4).isPresent()) {
      throw PedistackException.createInternalErrorException(
          PedistackErrorDescriptions.CARD_ALREADY_REGISTERED_ERROR_DESCRIPTION);
    }
  }

  @Override
  public CardAccountEntity save(CardAccountEntity cardAccountEntity) throws PedistackException {
    return cardAccountEntityDao.save(cardAccountEntity);
  }

  @Override
  public List<CardAccountEntity> saveAll(List<CardAccountEntity> cardAccountEntityList) {
    return cardAccountEntityDao.saveAll(cardAccountEntityList);
  }

  @Override
  public void delete(CardAccountEntity cardAccountEntity) throws PedistackException {
    cardAccountEntityDao.delete(cardAccountEntity);
  }

  @Override
  public CardAccountEntity findByIdentifier(String identifier) throws PedistackException {
    return cardAccountEntityDao
        .findById(identifier)
        .orElseThrow(
            () ->
                PedistackException.createInternalErrorException(
                    PedistackErrorDescriptions.CARD_NOT_FOUND_ERROR_DESCRIPTION));
  }

  @Override
  public Page<CardAccountEntity> findAll(Pageable pageable) throws PedistackException {
    return cardAccountEntityDao.findAll(pageable);
  }
}
