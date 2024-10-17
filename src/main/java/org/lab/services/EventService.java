package org.lab.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.lab.exceptions.EntityNotFoundException;
import org.lab.models.Event;
import org.lab.models.PaymentStatus;
import org.lab.repositories.EventRepository;

import java.time.LocalDate;

@ApplicationScoped
public class EventService {

    private final EventRepository eventRepository;

    public EventService() {
        throw new RuntimeException("Initialization was not successful");
    }

    @Inject
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
        System.out.println("EventService initialized successfully");
        eventRepository.getEvents()
                .forEach(System.out::println);
    }

    public boolean createEvent(String name, LocalDate date, float ticketPrice, float totalCost) {
        long numberOfEvents = eventRepository.getEvents().size();
        Event event = new Event(++numberOfEvents, name, date, ticketPrice, totalCost);

        return eventRepository.addEvent(event);
    }

    public void deleteEvent(Long eventId) throws EntityNotFoundException {
        if (!eventRepository.deleteEvent(eventId)) {
            throw new EntityNotFoundException(eventId);
        }
    }

    public float getTicketsRevenue(Long id) throws EntityNotFoundException {
        Event event = eventRepository.getEvent(id)
                .orElseThrow(() -> new EntityNotFoundException(id));
        long paidParticipants = event.getParticipants().stream()
                .filter(p -> PaymentStatus.PAID.equals(p.getPaymentStatus()))
                .count();

        return paidParticipants * event.getTicketPrice();
    }

    public float getTotalRevenue(Long id) throws EntityNotFoundException {
        Event event = eventRepository.getEvent(id)
                .orElseThrow(() -> new EntityNotFoundException(id));
        float ticketsRevenue = getTicketsRevenue(id);

        return ticketsRevenue - event.getTotalCost();
    }

}
