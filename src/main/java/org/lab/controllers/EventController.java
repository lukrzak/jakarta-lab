package org.lab.controllers;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ejb.EJB;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.lab.dtos.GetEventResponse;
import org.lab.dtos.GetEventsResponse;
import org.lab.dtos.PatchEventRequest;
import org.lab.dtos.PutEventRequest;
import org.lab.exceptions.EntityNotFoundException;
import org.lab.models.Event;
import org.lab.services.EventService;

import java.util.List;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("events")
public class EventController {

    private EventService eventService;

    private HttpServletResponse response;

    @Context
    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }

    @EJB
    public void setEventService(EventService eventService) {
        this.eventService = eventService;
    }

    @GET
    @Produces(APPLICATION_JSON)
    @RolesAllowed("USER")
    public Response getEvents() {
        List<Event> events = eventService.getEvents();
        return Response.ok(new GetEventsResponse(events)).build();
    }

    @GET
    @Path("/{id}")
    @Produces(APPLICATION_JSON)
    @RolesAllowed("USER")
    public Response getEvent(@PathParam("id") Long id) {
        try {
            Event event = eventService.getEvent(id);
            return Response.ok(new GetEventResponse(event)).build();
        } catch (EntityNotFoundException e ) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed("USER")
    public Response addEvent(PutEventRequest request) {
        eventService.createEvent(request);
        return Response.ok().build();
    }

    @PATCH
    @Path("/{id}")
    @Consumes(APPLICATION_JSON)
    @RolesAllowed("USER")
    public Response modifyEvent(@PathParam("id") Long id, PatchEventRequest request) {
        try {
            eventService.modifyEvent(id, request);
            return Response.ok().build();
        } catch (EntityNotFoundException e ) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed("USER")
    public Response deleteEvent(@PathParam("id") Long id) {
        try {
            eventService.deleteEvent(id);
            return Response.ok().build();
        } catch (EntityNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

}
