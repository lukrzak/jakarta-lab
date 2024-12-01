package org.lab.repositories;

import jakarta.enterprise.context.Dependent;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
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
            return Optional.of(em.createQuery("select o from Organiser o where o.name = :name", Organiser.class)
                    .setParameter("name", name)
                    .getSingleResult());
        } catch (EntityNotFoundException | NoResultException ex) {
            return Optional.empty();
        }
    }

    public List<Organiser> getOrganisers() {
        return em.createQuery("select o from Organiser o", Organiser.class).getResultList();
    }

    public void addOrganiser(Organiser organiser) {
        em.persist(organiser);
        em.flush();
        System.out.println("Added organiser: " + organiser.getName());
    }

}
