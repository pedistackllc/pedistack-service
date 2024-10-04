package com.pedistack.common.managers;

public interface GlobalConfigurationManager {

    String jwtSigningKey();
    long jwtExpirationSeconds();
    int credentialExpiryMonths();
    int credentialMaximumAttempts();
    int emailActivationTokenLength();
    int msisdnActivationTokenLength();
    String defaultCustomerProfileName();
    String defaultBusinessProfileName();
    String defaultAgentProfileName();

}
