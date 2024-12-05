package org.lab.repositories;

import jakarta.enterprise.context.Dependent;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.lab.models.Event;
import org.lab.models.Organiser;
import org.lab.models.Participant;

import java.util.List;
import java.util.Optional;

@Dependent
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
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Participant> cq = cb.createQuery(Participant.class);
        Root<Participant> root = cq.from(Participant.class);
        cq.select(root);

        return em.createQuery(cq).getResultList();
        //return em.createQuery("select p from Participant p", Participant.class).getResultList();
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
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Participant> cq = cb.createQuery(Participant.class);
        Root<Participant> participantRoot = cq.from(Participant.class);
        cq.where(cb.equal(participantRoot.get("event"), event));

        return em.createQuery(cq).getResultList();
    }

}
