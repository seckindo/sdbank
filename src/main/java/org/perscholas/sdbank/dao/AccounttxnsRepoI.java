package org.perscholas.sdbank.dao;

import org.perscholas.sdbank.models.Accounttxns;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccounttxnsRepoI extends JpaRepository<Accounttxns, Integer> {

    Optional<Accounttxns> findById(int id);

    Optional<Accounttxns> findByAccount_Id(int id);

}
