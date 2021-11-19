package com.github.youssfbr.servicos.model.repositories;

import com.github.youssfbr.servicos.model.entities.Servico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IServicoRepository extends JpaRepository<Servico, Long> {

    @Query(" SELECT s FROM Servico s join s.client c " +
            "where upper( c.name ) like upper( :name ) " +
            "and MONTH(s.date) =:month")
    List<Servico> findByNameClientAndMounth(
            @Param("name") String name,
            @Param("month") Integer month);
}
