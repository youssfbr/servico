package com.github.youssfbr.servicos.model.repositories;

import com.github.youssfbr.servicos.model.entities.Telephone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITelephoneRepository extends JpaRepository<Telephone, Long> {
}
