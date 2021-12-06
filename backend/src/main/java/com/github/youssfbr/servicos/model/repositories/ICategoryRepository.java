package com.github.youssfbr.servicos.model.repositories;

import com.github.youssfbr.servicos.model.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICategoryRepository extends JpaRepository<Category, Long> {
}
