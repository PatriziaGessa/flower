package com.patrizia.udacity.flower.flower.repository;

import com.patrizia.udacity.flower.flower.entity.Delivery;
import com.patrizia.udacity.flower.flower.entity.Plant;
import com.patrizia.udacity.flower.flower.projecton.RecipientAndPrice;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Class to create methods to
 * persist, find, merge and delete Delivery objects
 **/
@Repository
@Transactional
public class DeliveryRepository {
    @PersistenceContext
    EntityManager entityManager;

    public void persist(Delivery delivery) {
        entityManager.persist(delivery);
    }

    public Delivery find(Long id) {
        return entityManager.find(Delivery.class, id);
    }

    // return the newly updated delivery:
    public Delivery merge(Delivery delivery) {
        return entityManager.merge(delivery);
    }

    public void delete(Long id) {
        Delivery delivery = entityManager.find(Delivery.class, id);
        entityManager.remove(delivery);
    }

    /*
      method to use the Named Query:
      accept a String name and return a List of Delivery Entities:
     */
    public List<Delivery> findDeliveriesByName(String name) {
        TypedQuery<Delivery> query = entityManager.createNamedQuery("Delivery.findByName", Delivery.class);
        query.setParameter("name", name);
        return query.getResultList();
    }

    // create a method that uses CriteriaBuilder to populate an instance of the RecipientAndPrice class
    // should take a Long deliveryId and return a RecipientAndPrice
    // that contains the name of the delivery recipient and the sum of the pric
    public RecipientAndPrice getBill(Long deliveryId) {
        // create a Criteria to query using Java:
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        // create query to return RecipientAndPrice object:
        CriteriaQuery<RecipientAndPrice> query = cb.createQuery(RecipientAndPrice.class);
        // set root from Plant class:
        Root<Plant> root = query.from(Plant.class);

        // build a query from Criteria object:
        query.select(
                        cb.construct(
                                RecipientAndPrice.class,
                                root.get("delivery").get("name"),
                                cb.sum(root.get("price"))))
                .where(cb.equal(root.get("delivery").get("id"), deliveryId));
        return entityManager.createQuery(query).getSingleResult();
    }
}
