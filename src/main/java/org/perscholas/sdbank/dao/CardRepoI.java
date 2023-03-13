package org.perscholas.sdbank.dao;

import org.perscholas.sdbank.models.Cards;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CardRepoI extends JpaRepository<Cards,Integer> {

    Optional<Cards> findById(int id);

    Optional<Cards> findByCardNum(long cardNum);
}
