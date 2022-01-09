package com.patrizia.udacity.flower.flower.repository;

import com.patrizia.udacity.flower.flower.entity.Plant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

public interface PlantRepository extends JpaRepository<Plant, Long> {

    Boolean existsPlantByIdAndDeliveryCompleted(Long id, Boolean delivered);

    @Query("SELECT p.delivery.completed " +
            "FROM Plant p " +
            "WHERE p.id = :plantId")
    Boolean deliveryCompleted(Long plantId);

    @Query("SELECT new java.lang.Boolean(p.delivery.completed)" +
            "FROM Plant p" +
            "WHERE p.id = :plantId")
    Boolean deliveryCompletedBoolean(Long plantId);

    List<Plant> findByPrinceLessThan(BigDecimal price);


}
