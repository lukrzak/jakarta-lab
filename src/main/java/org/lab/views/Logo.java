package org.lab.views;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;

import java.io.Serializable;

@Named
@SessionScoped
public class Logo implements Serializable {

    public String getImgPath() {
        String language = FacesContext.getCurrentInstance().getViewRoot().getLocale().getLanguage();
        if (language.equals("pl")){
            return "resources/pl/img/logo.png";
        } else if (language.equals("en")){
            return "resources/en/img/logo.png";
        }
        return "resources/en/img/logo.png";
    }

    private boolean resourceExists(String path) {
        return getClass().getResourceAsStream(path) != null;
    }
}
