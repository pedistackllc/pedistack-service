package com.pedistack.common.managers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class GlobalConfigurationManagerBean implements GlobalConfigurationManager {

  @Value("${jwt.signing.key}")
  private String jwtSigningKey;

  @Value("${jwt.expiry.seconds}")
  private String jwtExpirySeconds;

  @Value("${credential.expiry.months}")
  private String credentialExpiryMonths;

  @Value("${credential.maximum.attempts}")
  private String credentialMaximumAttempts;

  @Value("${user.email.activation.token.length}")
  private String emailActivationTokenLength;

  @Value("${user.msisdn.activation.token.length}")
  private String msisdnActivationTokenLength;

  @Value("${default.business.profile.name}")
  private String defaultBusinessProfileName;

  @Value("${default.agent.profile.name}")
  private String defaultAgentProfileName;

  @Value("${default.customer.profile.name}")
  private String defaultCustomerProfileName;

  @Override
  public String jwtSigningKey() {
    return jwtSigningKey;
  }

  @Override
  public long jwtExpirationSeconds() {
    return Long.parseLong(jwtExpirySeconds);
  }

  @Override
  public int credentialExpiryMonths() {
    return Integer.parseInt(credentialExpiryMonths);
  }

  @Override
  public int credentialMaximumAttempts() {
    return Integer.parseInt(credentialMaximumAttempts);
  }

  @Override
  public int emailActivationTokenLength() {
    return Integer.parseInt(emailActivationTokenLength);
  }

  @Override
  public int msisdnActivationTokenLength() {
    return Integer.parseInt(msisdnActivationTokenLength);
  }

  @Override
  public String defaultCustomerProfileName() {
    return defaultCustomerProfileName;
  }

  @Override
  public String defaultBusinessProfileName() {
    return defaultBusinessProfileName;
  }

  @Override
  public String defaultAgentProfileName() {
    return defaultAgentProfileName;
  }
}
