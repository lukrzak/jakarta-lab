package org.lab.repositories;

import jakarta.enterprise.context.Dependent;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.lab.models.Event;
import org.lab.models.Organiser;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Dependent
public class OrganiserRepository {

    private EntityManager em;

    @PersistenceContext(name = "eventsPu")
    public void setEm(EntityManager em) {
        this.em = em;
    }

    public Optional<Organiser> getOrganiser(UUID id) {
        return Optional.ofNullable(em.find(Organiser.class, id));
    }

    public Optional<Organiser> getOrganiser(String name) {
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Organiser> cq = cb.createQuery(Organiser.class);
            Root<Organiser> organiserRoot = cq.from(Organiser.class);
            cq.where(cb.equal(organiserRoot.get("name"), name));
            return Optional.of(em.createQuery(cq).getSingleResult());
        } catch (EntityNotFoundException | NoResultException ex) {
            return Optional.empty();
        }
    }

    public List<Organiser> getOrganisers() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Organiser> cq = cb.createQuery(Organiser.class);
        Root<Organiser> root = cq.from(Organiser.class);
        cq.select(root);

        return em.createQuery(cq).getResultList();
        //return em.createQuery("select o from Organiser o", Organiser.class).getResultList();
    }

    public void addOrganiser(Organiser organiser) {
        em.persist(organiser);
        em.flush();
        System.out.println("Added organiser: " + organiser.getName());
    }

}
