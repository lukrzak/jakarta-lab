package org.lab.controllers;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import org.lab.dtos.GetOrganiserResponse;
import org.lab.exceptions.EntityNotFoundException;
import org.lab.services.EventService;
import org.lab.services.ParticipantService;

import java.util.Arrays;
import java.util.UUID;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet(urlPatterns = {"/api/*"})
@MultipartConfig(maxFileSize = 200 * 1024)
public class ApiServlet extends HttpServlet {

    private final OrganiserController organiserController;
    private final EventService eventService;
    private final ParticipantService participantService;
    private final Pattern UUID = Pattern.compile("[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}");
    private final Pattern CDI_URL = Pattern.compile("/cdi");
    private final Pattern ORGANISERS_URL = Pattern.compile("/organisers");
    private final Pattern ORGANISER_URL = Pattern.compile("/organisers/(%s)".formatted(UUID.pattern()));
    private final Pattern LOGO_URL = Pattern.compile("/organisers/(%s)/logo".formatted(UUID.pattern()));
    private final Jsonb jsonb = JsonbBuilder.create();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getMethod().equals("PATCH")) {
            doPatch(request, response);
        } else {
            super.service(request, response);
        }
    }

    @Inject
    public ApiServlet(OrganiserController organiserController, EventService es, ParticipantService ps) {
        this.organiserController = organiserController;
        this.eventService = es;
        this.participantService = ps;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = parseRequestPath(request);
        if ("/api".equals(request.getServletPath())) {
            if (path.matches(ORGANISER_URL.pattern())) {
                response.setContentType("application/json");
                UUID id = extractUuid(ORGANISER_URL, request.getPathInfo());
                GetOrganiserResponse responseBody = organiserController.getOrganiser(id);
                if (responseBody == null) {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                }
                response.getWriter().write(jsonb.toJson(organiserController.getOrganiser(id)));
                return;
            } else if (path.matches(ORGANISERS_URL.pattern())) {
                response.setContentType("application/json");
                response.getWriter().write(jsonb.toJson(organiserController.getOrganisers()));
                return;
            } else if (path.matches(LOGO_URL.pattern())) {
                UUID uuid = extractUuid(LOGO_URL, path);
                try {
                    byte[] logo = organiserController.getOrganiserLogo(uuid);
                    response.setContentType("image/png");
                    response.setContentLength(logo.length);
                    response.getOutputStream().write(logo);
                } catch (IOException e) {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                }
                return;
            } else if (path.matches(CDI_URL.pattern())) {
                try {
                    eventService.getTotalRevenue(1L);
                } catch (EntityNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        response.sendError(HttpServletResponse.SC_BAD_REQUEST);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = parseRequestPath(request);
        if ("/api".equals(request.getServletPath())) {
            if (path.matches(LOGO_URL.pattern())) {
                UUID uuid = extractUuid(LOGO_URL, request.getPathInfo());
                organiserController.putOrganiserLogo(uuid, request.getPart("logo").getInputStream());
                return;
            }
        }
        response.sendError(HttpServletResponse.SC_BAD_REQUEST);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = parseRequestPath(request);
        if ("/api".equals(request.getServletPath())) {
            if (path.matches(LOGO_URL.pattern())) {
                UUID uuid = extractUuid(LOGO_URL, request.getPathInfo());
                response.getWriter().write(jsonb.toJson(organiserController.deleteOrganiserLogo(uuid)));
                return;
            }
        }
        response.sendError(HttpServletResponse.SC_BAD_REQUEST);
    }

    protected void doPatch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = parseRequestPath(request);
        if ("/api".equals(request.getServletPath())) {
            if (path.matches(LOGO_URL.pattern())) {
                UUID uuid = extractUuid(LOGO_URL, request.getPathInfo());
                organiserController.putOrganiserLogo(uuid, request.getPart("logo").getInputStream());
                return;
            }
        }
        response.sendError(HttpServletResponse.SC_BAD_REQUEST);
    }


    private UUID extractUuid(Pattern pattern, String path) {
        Matcher matcher = pattern.matcher(path);
        if (matcher.matches()) {
            return java.util.UUID.fromString(matcher.group(1));
        }
        throw new IllegalArgumentException("No UUID in path.");
    }

    private String parseRequestPath(HttpServletRequest request) {
        String path = request.getPathInfo();
        path = path != null ? path : "";
        return path;
    }

}
