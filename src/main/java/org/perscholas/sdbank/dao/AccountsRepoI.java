package org.perscholas.sdbank.dao;

import org.perscholas.sdbank.models.Accounts;
import org.perscholas.sdbank.models.Customers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountsRepoI extends JpaRepository<Accounts,Integer> {

    Optional<Accounts> findById(int id);

    Optional<Accounts> findByAccountNum(int num);

}
