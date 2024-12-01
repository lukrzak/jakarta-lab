package org.lab.views;

import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import jakarta.security.enterprise.SecurityContext;
import org.lab.exceptions.EntityNotFoundException;
import org.lab.models.Event;
import org.lab.models.Organiser;
import org.lab.services.EventService;

import java.util.List;

@RequestScoped
@Named
public class ListEvent {

    @Inject
    private SecurityContext securityContext;

    private EventService eventService;

    @EJB
    public void setEventService(EventService service) {
        System.out.println("TEST");
        this.eventService = service;
    }

    public boolean hasRole(String role) {
        return securityContext.isCallerInRole(role);
    }

    public List<Event> getEvents() {
        if (hasRole(Organiser.Role.ADMIN)) {
            return eventService.getEvents();
        } else {
            return eventService.getEvents(securityContext.getCallerPrincipal().getName());
        }
    }

    public void deleteEvent(Long id) throws EntityNotFoundException {
        eventService.deleteEvent(id);
    }

}