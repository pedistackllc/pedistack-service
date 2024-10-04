package com.pedistack.accounts.operation.managers;

import com.pedistack.accounts.v1_0.CardAccountRequest;
import com.pedistack.accounts.v1_0.common.CardAccount;
import com.pedistack.accounts.v1_0.common.CardAccountType;
import com.pedistack.common.exception.PedistackException;
import com.pedistack.common.io.PageResponse;
import com.pedistack.db.accounts.CardAccountEntity;
import com.pedistack.db.accounts.CardAccountEntityDaoManager;
import com.pedistack.db.oauth.UserEntityDaoManager;
import com.pedistack.db.planet.CountryEntityDaoManager;
import com.pedistack.events.accounts.CardAccountChargeEvent;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class CardAccountOperationManagerBean implements CardAccountOperationManager {

  private final CardAccountEntityDaoManager cardAccountEntityDaoManager;
  private final CountryEntityDaoManager countryEntityDaoManager;
  private final UserEntityDaoManager userEntityDaoManager;
  private final ApplicationEventPublisher applicationEventPublisher;

  public CardAccountOperationManagerBean(
      CardAccountEntityDaoManager cardAccountEntityDaoManager,
      CountryEntityDaoManager countryEntityDaoManager,
      ApplicationEventPublisher applicationEventPublisher,
      UserEntityDaoManager userEntityDaoManager) {
    this.cardAccountEntityDaoManager = cardAccountEntityDaoManager;
    this.countryEntityDaoManager = countryEntityDaoManager;
    this.userEntityDaoManager = userEntityDaoManager;
    this.applicationEventPublisher = applicationEventPublisher;
  }

  @Override
  public List<CardAccount> userCardAccounts(
      String tenant, String sessionUserIdentifier, String sessionReference) {
    final List<CardAccountEntity> cardAccountEntityList =
        cardAccountEntityDaoManager.findByUserIdentifier(sessionUserIdentifier);
    return cardAccountEntityList.stream().map(this::createCardAccountResponse).toList();
  }

  @Override
  @Transactional
  public CardAccount create(
      String tenant,
      String sessionUserIdentifier,
      String sessionReference,
      CardAccountRequest cardAccountRequest)
      throws PedistackException {
    cardAccountEntityDaoManager.checkExistingCard(cardAccountRequest.getLast4());
    final CardAccountEntity cardAccountEntity = new CardAccountEntity();
    cardAccountEntity.setCardBin(cardAccountRequest.getBin());
    cardAccountEntity.setCardName(cardAccountRequest.getName());
    cardAccountEntity.setCardToken(cardAccountRequest.getToken());
    cardAccountEntity.setCardType(cardAccountRequest.getType().name());
    cardAccountEntity.setCountry(
        countryEntityDaoManager.findByAlpha2Code(cardAccountRequest.getCountryCode()));
    cardAccountEntity.setBank(cardAccountRequest.getBank());
    cardAccountEntity.setSignature(cardAccountRequest.getSignature());
    cardAccountEntity.setLast4(cardAccountRequest.getLast4());
    cardAccountEntity.setExpiryYear(cardAccountRequest.getYear());
    cardAccountEntity.setExpiryMonth(cardAccountRequest.getMonth());
    cardAccountEntity.setUser(userEntityDaoManager.findByIdentifier(sessionUserIdentifier));
    cardAccountEntity.setCreatedUserId(sessionUserIdentifier);
    cardAccountEntityDaoManager.save(cardAccountEntity);
    final CardAccount cardAccount = createCardAccountResponse(cardAccountEntity);
    applicationEventPublisher.publishEvent(
        new CardAccountChargeEvent(
            this, tenant, sessionUserIdentifier, sessionReference, cardAccount));
    return cardAccount;
  }

  @Override
  public CardAccount get(
      String tenant, String sessionUserIdentifier, String sessionReference, String identifier)
      throws PedistackException {
    final CardAccountEntity cardAccountEntity =
        cardAccountEntityDaoManager.findByIdentifier(identifier);
    return createCardAccountResponse(cardAccountEntity);
  }

  @Override
  public PageResponse<CardAccount> list(
      String tenant,
      String sessionUserIdentifier,
      String sessionReference,
      Integer page,
      Integer size)
      throws PedistackException {
    return null;
  }

  @Override
  public CardAccount update(
      String tenant,
      String sessionUserIdentifier,
      String sessionReference,
      String identifier,
      CardAccountRequest request)
      throws PedistackException {
    return null;
  }

  @Override
  @Transactional
  public void delete(
      String tenant, String sessionUserIdentifier, String sessionReference, String identifier)
      throws PedistackException {
    cardAccountEntityDaoManager.delete(cardAccountEntityDaoManager.findByIdentifier(identifier));
  }

  private CardAccount createCardAccountResponse(CardAccountEntity cardAccountEntity) {
    final CardAccount cardAccount = new CardAccount();
    BeanUtils.copyProperties(cardAccountEntity, cardAccount);
    cardAccount.setBank(cardAccountEntity.getBank());
    cardAccount.setName(cardAccountEntity.getCardName());
    cardAccount.setCountryCode(cardAccountEntity.getCountry().getAlpha2Code());
    cardAccount.setBin(cardAccountEntity.getCardBin());
    cardAccount.setToken(cardAccountEntity.getCardToken());
    cardAccount.setType(CardAccountType.valueOf(cardAccountEntity.getCardType()));
    return cardAccount;
  }
}
