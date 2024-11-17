package org.lab.repositories;

import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.lab.models.Event;

import java.util.List;
import java.util.Optional;

@RequestScoped
public class EventRepository {

    private EntityManager em;

    @PersistenceContext(name = "eventsPu")
    public void setEm(EntityManager em) {
        this.em = em;
    }

    public Optional<Event> getEvent(Long id) {
        return Optional.ofNullable(em.find(Event.class, id));
    }

    public List<Event> getEvents() {
        return em.createQuery("select e from Event e", Event.class).getResultList();
    }

    public void addEvent(Event event) {
        em.persist(event);
    }

    public void deleteEvent(Long id) {
       em.remove(em.find(Event.class, id));
    }

    public void updateEvent(Event event) {
        em.merge(event);
    }

}
