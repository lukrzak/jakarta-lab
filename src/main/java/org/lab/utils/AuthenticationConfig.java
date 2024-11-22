package org.lab.utils;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.security.enterprise.authentication.mechanism.http.BasicAuthenticationMechanismDefinition;

@ApplicationScoped
@BasicAuthenticationMechanismDefinition(realmName = "jakarta")
public class AuthenticationConfig {
}
