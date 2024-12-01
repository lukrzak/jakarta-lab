package org.lab.views;

import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.lab.dtos.PutEventRequest;
import org.lab.services.EventService;

import java.time.LocalDate;

@RequestScoped
@Named
public class AddEvent {

    @Inject
    private FacesContext facesContext;

    private EventService eventService;

    @EJB
    public void setEventService(EventService service) {
        this.eventService = service;
    }

    private String name;
    private float ticketPrice;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(float ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public String saveAction() {
        PutEventRequest req = new PutEventRequest();
        req.setTicketPrice(ticketPrice);
        req.setName(name);
        req.setOrganiserName(facesContext.getExternalContext().getUserPrincipal().getName());
        req.setStartDate(LocalDate.now());
        req.setTotalCost(100.0f);

        System.out.println("creating event " + name + " org: " + facesContext.getExternalContext().getUserPrincipal().getName());
        eventService.createEvent(req);
        return "event_list.xhtml?faces-redirect=true";
    }
}
