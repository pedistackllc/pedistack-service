package com.pedistack.identity.event.listeners;

import com.pedistack.common.exception.PedistackException;
import com.pedistack.db.identity.IdentityEntity;
import com.pedistack.db.identity.IdentityEntityDaoManager;
import com.pedistack.events.identity.IdentityActivationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class IdentityActivationEventListener {

  private final IdentityEntityDaoManager identityEntityDaoManager;

  public IdentityActivationEventListener(IdentityEntityDaoManager identityEntityDaoManager) {
    this.identityEntityDaoManager = identityEntityDaoManager;
  }

  @Async
  @EventListener
  @Transactional
  public void identityActivation(IdentityActivationEvent identityActivationEvent)
      throws PedistackException {
    final IdentityEntity identityEntity =
        identityEntityDaoManager.findByUserIdentifier(identityActivationEvent.getUserIdentifier());
    identityEntity.setStatus(identityActivationEvent.getIdentityStatus().name());
    identityEntityDaoManager.save(identityEntity);
  }
}
