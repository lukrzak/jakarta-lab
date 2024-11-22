package org.lab.utils;

import jakarta.security.enterprise.credential.Credential;
import jakarta.security.enterprise.credential.UsernamePasswordCredential;
import jakarta.security.enterprise.identitystore.CredentialValidationResult;
import jakarta.security.enterprise.identitystore.IdentityStore;

import java.util.Set;

public class IdentityStoreImpl implements IdentityStore {

    @Override
    public CredentialValidationResult validate(Credential credential) {
        if (credential instanceof UsernamePasswordCredential usernamePasswordCredential) {
            String username = usernamePasswordCredential.getCaller();
            String password = usernamePasswordCredential.getPasswordAsString();

            if ("user".equals(username) && "password".equals(password)) {
                return new CredentialValidationResult(username, Set.of("USER"));
            }
        }

        return CredentialValidationResult.INVALID_RESULT;
    }
}
