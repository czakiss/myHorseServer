package com.example.myHorseServer.repository;

import com.example.myHorseServer.model.GamerEquipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface GamerEquipmentRepository extends JpaRepository<GamerEquipment,Integer> {
    @Cacheable
    Optional<GamerEquipment> findByEquipmentId(Integer equipmentId);

    @Cacheable
    Optional<GamerEquipment> findByGamerId(Integer gamerId);

    @Cacheable
    Optional<GamerEquipment> findByIdItem(Integer idItem);
}
