package org.lab.controllers;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.lab.dtos.GetParticipantResponse;
import org.lab.dtos.GetParticipantsResponse;
import org.lab.dtos.PutParticipantRequest;
import org.lab.dtos.PatchParticipantRequest;
import org.lab.exceptions.EntityNotFoundException;
import org.lab.models.Participant;
import org.lab.services.ParticipantService;

import java.util.List;
import java.util.Optional;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/events/{event_id}/participants")
public class ParticipantController {

    private final ParticipantService participantService;

    @Inject
    public ParticipantController(ParticipantService participantService) {
        this.participantService = participantService;
    }

    @GET
    @Produces(APPLICATION_JSON)
    public Response getParticipants(@PathParam("event_id") Long event_id) {
        List<Participant> participants = participantService.getParticipantsByEvent(event_id);
        return Response.ok(new GetParticipantsResponse(participants)).build();
    }

    @GET
    @Path("/{id}")
    @Produces(APPLICATION_JSON)
    public Response getParticipant(@PathParam("event_id") Long event_id, @PathParam("id") Long id) {
        List<Participant> participants = participantService.getParticipantsByEvent(event_id);
        Optional<Participant> participant = participants.stream().filter(p -> p.getId().equals(id)).findFirst();

        if (participant.isPresent()) {
            return Response.ok(new GetParticipantResponse(participant.get())).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addParticipant(@PathParam("event_id") Long event_id,
                                   @PathParam("id") Long id,
                                   PutParticipantRequest request) {
        try {
            return Response.ok(participantService.addParticipant(event_id, id, request)).build();
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