package org.lab.views;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.security.enterprise.AuthenticationStatus;
import jakarta.security.enterprise.SecurityContext;
import jakarta.security.enterprise.credential.Credential;
import jakarta.security.enterprise.credential.Password;
import jakarta.security.enterprise.credential.UsernamePasswordCredential;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.util.List;
import java.util.Locale;

import static jakarta.security.enterprise.authentication.mechanism.http.AuthenticationParameters.withParams;

@Named
@RequestScoped
public class UserLogin {

    private String username;
    private String password;

    @Inject
    private SecurityContext securityContext;

    @Inject
    private FacesContext facesContext;

    @Inject
    private HttpServletRequest request;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String loginAction() {
        Credential credential = new UsernamePasswordCredential(username, new Password(password));
        AuthenticationStatus status = securityContext.authenticate(
                request,
                (HttpServletResponse) facesContext.getExternalContext().getResponse(),
                withParams().credential(credential)
        );
        facesContext.responseComplete();
        System.out.println("username: " + username);
        System.out.println("password: " + password);
        System.out.println("Auth completed: " + status.toString());

        return "/views/pages/event_list.xhtml?faces-redirect=true";
    }

    public String logoutAction() throws ServletException {
        System.out.println("LOgout");
        request.logout();
        return "/views/auth/login.xhtml?faces-redirect=true";
    }
}
