package com.pedistack.planet.operation.managers;

import com.pedistack.common.exception.PedistackException;
import com.pedistack.db.planet.CountryEntity;
import com.pedistack.db.planet.CountryEntityDaoManager;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class CountrySetupManager implements ApplicationListener<ContextRefreshedEvent> {

  private final CountryEntityDaoManager countryEntityDaoManager;

  public CountrySetupManager(CountryEntityDaoManager countryEntityDaoManager) {
    this.countryEntityDaoManager = countryEntityDaoManager;
  }

  @Override
  public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
    final String[] countryCodes = Locale.getISOCountries();
    Arrays.stream(countryCodes)
        .forEach(
            countryCode -> {
              try {
                if (countryEntityDaoManager.findByAlpha2CodeReturnOptional(countryCode).isEmpty()) {
                  final Locale locale = new Locale("", countryCode);
                  final CountryEntity countryEntity = new CountryEntity();
                  countryEntity.setName(locale.getDisplayCountry());
                  countryEntity.setAlpha2Code(locale.getCountry());
                  countryEntity.setAlpha3Code(locale.getISO3Country());
                  countryEntity.setLanguageCodes(List.of(locale.getLanguage()));
                  countryEntityDaoManager.save(countryEntity);
                }
              } catch (PedistackException e) {
                throw new RuntimeException(e);
              }
            });
  }
}
