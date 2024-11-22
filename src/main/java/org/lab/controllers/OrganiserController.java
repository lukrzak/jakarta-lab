package org.lab.controllers;

import jakarta.ejb.EJB;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.lab.dtos.GetEventResponse;
import org.lab.dtos.GetOrganiserResponse;
import org.lab.dtos.PutOrganiserRequest;
import org.lab.exceptions.EntityNotFoundException;
import org.lab.models.Event;
import org.lab.models.Organiser;
import org.lab.services.OrganiserService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Path("organisers")
public class OrganiserController {

    private OrganiserService organiserService;

    @EJB
    public void setOrganiserService(OrganiserService organiserService) {
        this.organiserService = organiserService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOrganisers() {
        List<GetOrganiserResponse> organisers = organiserService.getOrganisers()
                .stream()
                .map(org -> new GetOrganiserResponse(org))
                .toList();
        return Response.ok(organisers).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOrganiser(@PathParam("id") UUID id) {
        Optional<Organiser> organiser = organiserService.getOrganiser(id);
        if (organiser.isPresent()) {
            return Response.ok(new GetOrganiserResponse(organiser.get())).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void addNewOrganiser(PutOrganiserRequest request) {
        organiserService.addOrganiser(request);
    }

//
//    public GetOrganisersResponse getOrganisers() {
//        return new GetOrganisersResponse(organiserService.findAll());
//    }
//
//    public GetOrganiserResponse getOrganiser(UUID id) {
//        try {
//            Organiser organiser = organiserService.findById(id);
//            return new GetOrganiserResponse(organiser);
//        } catch (EntityNotFoundException e) {
//            return null;
//        }
//    }
//
//    public byte[] getOrganiserLogo(UUID id) throws IOException {
//        byte[] logo = organiserService.getOrganiserLogo(id);
//        if (logo == null) {
//            throw new IOException();
//        }
//        return logo;
//    }
//
//    public void putOrganiserLogo(UUID id, InputStream logo) throws IOException {
//        organiserService.putOrganiserLogo(id, logo);
//    }
//
//    public boolean deleteOrganiserLogo(UUID id) {
//        return organiserService.deleteOrganiserLogo(id);
//    }



}
