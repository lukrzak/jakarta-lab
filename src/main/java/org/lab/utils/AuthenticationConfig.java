//package org.lab.utils;
//
//import jakarta.enterprise.context.ApplicationScoped;
//import jakarta.inject.Named;
//import jakarta.security.enterprise.authentication.mechanism.http.CustomFormAuthenticationMechanismDefinition;
//import jakarta.security.enterprise.authentication.mechanism.http.LoginToContinue;
//import jakarta.security.enterprise.identitystore.DatabaseIdentityStoreDefinition;
//import jakarta.security.enterprise.identitystore.Pbkdf2PasswordHash;
//
//@CustomFormAuthenticationMechanismDefinition(
//        loginToContinue = @LoginToContinue(
//                loginPage = "/views/auth/login.xhtml",
//                errorPage = "/views/auth/login_error.xhtml"
//        )
//)
//@ApplicationScoped
//@DatabaseIdentityStoreDefinition(
//        dataSourceLookup = "jdbc/Events",
//        callerQuery = "select password from organiser where name = ?",
//        groupsQuery = "select role from users_roles where id = (select id from organiser where name = ?)",
//        hashAlgorithm = Pbkdf2PasswordHash.class
//)
//@Named
//public class AuthenticationConfig {
//}
