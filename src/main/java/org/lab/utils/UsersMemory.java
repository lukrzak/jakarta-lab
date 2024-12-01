package org.lab.utils;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.security.DeclareRoles;
import jakarta.annotation.security.RunAs;
import jakarta.ejb.EJB;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import jakarta.inject.Inject;
import jakarta.security.enterprise.identitystore.Pbkdf2PasswordHash;
import org.lab.models.Organiser;
import org.lab.services.OrganiserService;

@Singleton
@Startup
@TransactionAttribute(value = TransactionAttributeType.NOT_SUPPORTED)
@DeclareRoles({Organiser.Role.ADMIN, Organiser.Role.USER})
@RunAs(Organiser.Role.ADMIN)
public class UsersMemory {

    @Inject
    private OrganiserService organiserService;

    @Inject
    private Pbkdf2PasswordHash passwordHash;

    @PostConstruct
    public void init() {
        Organiser admin = new Organiser("admin");
        admin.setRole(Organiser.Role.ADMIN);
        admin.setRole(Organiser.Role.USER);
        admin.setPassword(passwordHash.generate("admin".toCharArray()));
        organiserService.addOrganiser(admin);

        Organiser user = new Organiser("user");
        user.setRole(Organiser.Role.USER);
        user.setPassword(passwordHash.generate("user".toCharArray()));
        organiserService.addOrganiser(user);
    }
}
