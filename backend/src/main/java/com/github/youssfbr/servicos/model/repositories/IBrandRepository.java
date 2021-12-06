package com.github.youssfbr.servicos.model.repositories;

import com.github.youssfbr.servicos.model.entities.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBrandRepository extends JpaRepository<Brand, Long> {
}
