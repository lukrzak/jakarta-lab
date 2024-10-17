//package org.lab.listeners;
//
//import jakarta.servlet.ServletContextEvent;
//import jakarta.servlet.ServletContextListener;
//import jakarta.servlet.annotation.WebListener;
//import org.lab.controllers.OrganiserController;
//import org.lab.services.OrganiserService;
//
//@WebListener
//public class ControllersListener implements ServletContextListener {
//
//    @Override
//    public void contextInitialized(ServletContextEvent event) {
//        final OrganiserService organiserService = (OrganiserService) event.getServletContext().getAttribute("organiserService");;
//
//        event.getServletContext().setAttribute("organiserController", new OrganiserController(organiserService));
//    }
//
//}
