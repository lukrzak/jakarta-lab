package org.lab.listeners;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.lab.repositories.OrganiserRepository;
import org.lab.services.OrganiserService;

@WebListener
public class ServicesListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent event) {
        final OrganiserRepository organiserRepository = new OrganiserRepository();
        final String storagePath = event.getServletContext().getInitParameter("storagePath");

        event.getServletContext().setAttribute("organiserService", new OrganiserService(organiserRepository, storagePath));
    }

}
