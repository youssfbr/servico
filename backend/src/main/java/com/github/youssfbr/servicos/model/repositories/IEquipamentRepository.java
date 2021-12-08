package com.github.youssfbr.servicos.model.repositories;

import com.github.youssfbr.servicos.model.entities.Equipament;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEquipamentRepository extends JpaRepository<Equipament, Long> {
}
