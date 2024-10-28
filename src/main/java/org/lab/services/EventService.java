package org.lab.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import org.lab.exceptions.EntityNotFoundException;
import org.lab.models.Event;
import org.lab.models.PaymentStatus;
import org.lab.repositories.EventRepository;

import java.time.LocalDate;
import java.util.List;

@ApplicationScoped
public class EventService {

    private final EventRepository eventRepository;

    public EventService() {
        System.out.println("No-args constructor for EventService");
        this.eventRepository = null;
    }

    @Inject
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
        System.out.println("EventService initialized successfully");
        eventRepository.getEvents()
                .forEach(System.out::println);
    }

    public List<Event> getEvents() {
        return eventRepository.getEvents();
    }

    public Event getEvent(Long id) throws EntityNotFoundException {
        return eventRepository.getEvent(id)
                .orElseThrow(() -> new EntityNotFoundException(id));
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
