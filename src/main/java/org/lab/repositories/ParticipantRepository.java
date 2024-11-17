package org.lab.repositories;

import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.lab.exceptions.EntityNotFoundException;
import org.lab.models.Event;
import org.lab.models.Participant;

import java.util.List;
import java.util.Optional;

@RequestScoped
public class ParticipantRepository {

    private EntityManager em;

    @PersistenceContext(name = "eventsPu")
    public void setEm(EntityManager em) {
        this.em = em;
    }

    public Optional<Participant> getParticipant(Long id) {
        return Optional.ofNullable(em.find(Participant.class, id));
    }

    public List<Participant> getParticipants() {
        return em.createQuery("select p from Participant p", Participant.class).getResultList();
    }

    public void addParticipant(Participant participant) {
        em.persist(participant);
    }

    public void deleteParticipant(Long id) {
        em.remove(em.find(Participant.class, id));
    }

    public void updateParticipant(Participant participant) {
        em.merge(participant);
    }

    public List<Participant> getParticipantsByEvent(Event event) {
        return em.createQuery("select p from Participant p where p.event = :event", Participant.class)
                .setParameter("event", event)
                .getResultList();
    }

}
