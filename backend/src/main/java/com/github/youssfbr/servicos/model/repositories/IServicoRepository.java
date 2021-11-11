package com.github.youssfbr.servicos.model.repositories;

import com.github.youssfbr.servicos.model.entities.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IServicoRepository extends JpaRepository<Service, Long> {
}
