package org.lab.repositories;

import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.lab.models.Event;
import org.lab.models.Organiser;

import java.util.List;
import java.util.Optional;

@Dependent
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

    public List<Event> getEvents(String username) {
        return em.createQuery("select e from Event e where e.organiser.name = :name", Event.class)
                .setParameter("name", username)
                .getResultList();
    }

    public void addEvent(Event event) {
        if (event.getId() == null) {
            em.persist(event);
        } else {
            em.merge(event);
        }
    }

    public void deleteEvent(Long id) {
       em.remove(em.find(Event.class, id));
    }

    public void updateEvent(Event event) {
        em.merge(event);
    }
}
