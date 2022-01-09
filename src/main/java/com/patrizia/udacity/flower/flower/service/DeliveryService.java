package com.patrizia.udacity.flower.flower.service;

import com.patrizia.udacity.flower.flower.entity.Delivery;
import com.patrizia.udacity.flower.flower.projecton.RecipientAndPrice;
import com.patrizia.udacity.flower.flower.repository.DeliveryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeliveryService {

    @Autowired
    DeliveryRepository deliveryRepository;

    public RecipientAndPrice getBill(Long deliveryId) {
        return deliveryRepository.getBill(deliveryId);
    }


    public Long save(Delivery delivery) {
        delivery.getPlants()
                .forEach(plant -> plant.setDelivery(delivery));
        deliveryRepository.persist(delivery);

        return delivery.getId();
    }
}
