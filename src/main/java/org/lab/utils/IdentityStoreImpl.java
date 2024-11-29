//package org.lab.utils;
//
//import jakarta.ejb.EJB;
//import jakarta.security.enterprise.credential.Credential;
//import jakarta.security.enterprise.credential.UsernamePasswordCredential;
//import jakarta.security.enterprise.identitystore.CredentialValidationResult;
//import jakarta.security.enterprise.identitystore.IdentityStore;
//import org.lab.models.Organiser;
//import org.lab.services.OrganiserService;
//
//import java.util.Set;
//
//public class IdentityStoreImpl implements IdentityStore {
//
//    private OrganiserService organiserService;
//
//    @EJB
//    public void setOrganiserService(OrganiserService organiserService) {
//        this.organiserService = organiserService;
//    }
//
//    @Override
//    public CredentialValidationResult validate(Credential credential) {
//        if (credential instanceof UsernamePasswordCredential usernamePasswordCredential) {
//            String username = usernamePasswordCredential.getCaller();
//            String password = usernamePasswordCredential.getPasswordAsString();
//
//            Organiser organiser = organiserService.getOrganiser(username).orElse(null);
//            if (organiser != null && organiserService.verify(username, password)) {
//                return new CredentialValidationResult(username, Set.of("USER"));
//            }
//
//        }
//
//        return CredentialValidationResult.INVALID_RESULT;
//    }
//}
