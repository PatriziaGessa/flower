package com.patrizia.udacity.flower.flower.service;

import com.patrizia.udacity.flower.flower.entity.Plant;
import com.patrizia.udacity.flower.flower.repository.PlantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

// a Plant Service that returns a Plant instance:
@Service
public class PlantService {

    @Autowired
    PlantRepository plantRepository;

    public Long save(Plant plant) {
        return plantRepository.save(plant).getId();
    }

    public Boolean delivered(Long id) {
        return plantRepository.existsPlantByIdAndDeliveryCompleted(id, true);
    }

    public List<Plant> findPlantsBelowPrice(BigDecimal price) {
        return plantRepository.findByPrinceLessThan(price);
    }

}
