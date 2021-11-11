package com.github.youssfbr.servicos.model.repositories;

import com.github.youssfbr.servicos.model.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IClientRepository extends JpaRepository<Client, Long> {
}
