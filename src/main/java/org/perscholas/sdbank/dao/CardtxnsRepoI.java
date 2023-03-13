package org.perscholas.sdbank.dao;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.perscholas.sdbank.models.Cardtxns;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CardtxnsRepoI extends JpaRepository<Cardtxns, Integer> {

    Optional<Cardtxns> findById(int id);

    Optional<Cardtxns> findByCard_Id(int id);
}
