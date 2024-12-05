package org.lab.controllers;

import jakarta.ejb.EJB;
import jakarta.inject.Inject;
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
import org.lab.dtos.GetParticipantResponse;
import org.lab.dtos.GetParticipantsResponse;
import org.lab.dtos.PutParticipantRequest;
import org.lab.dtos.PatchParticipantRequest;
import org.lab.exceptions.EntityNotFoundException;
import org.lab.models.Participant;
import org.lab.services.OrganiserService;
import org.lab.services.ParticipantService;

import java.util.List;
import java.util.Optional;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/events/{event_id}/participants")
public class ParticipantController {

    private ParticipantService participantService;

    private HttpServletResponse response;

    @Context
    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }

    @EJB
    public void setParticipantService(ParticipantService participantService) {
        this.participantService = participantService;
    }

    @GET
    @Produces(APPLICATION_JSON)
    public Response getParticipants(@PathParam("event_id") Long event_id) {
        try {
            if (event_id == 0) {
                List<Participant> participants = participantService.getParticipants();
                return Response.ok(new GetParticipantsResponse(participants)).build();
            }
            List<Participant> participants = participantService.getParticipantsByEvent(event_id);
            return Response.ok(new GetParticipantsResponse(participants)).build();
        } catch (EntityNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(APPLICATION_JSON)
    public Response getParticipant(@PathParam("event_id") Long event_id, @PathParam("id") Long id) {
        try {
            List<Participant> participants = participantService.getParticipantsByEvent(event_id);
            Optional<Participant> participant = participants.stream().filter(p -> p.getId().equals(id)).findFirst();
            if (participant.isPresent()) {
                return Response.ok(new GetParticipantResponse(participant.get())).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        } catch (EntityNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addParticipant(@PathParam("event_id") Long event_id, PutParticipantRequest request) {
        try {
            participantService.addParticipant(event_id, request);
            return Response.ok().build();
        } catch (EntityNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @PATCH
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response modifyParticipant(@PathParam("id") Long id, PatchParticipantRequest request) {
        try {
            participantService.modifyParticipant(id, request);
            return Response.ok().build();
        } catch (EntityNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteParticipant(@PathParam("id") Long id) {
        try {
            participantService.deleteParticipant(id);
            return Response.ok().build();
        } catch (EntityNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
