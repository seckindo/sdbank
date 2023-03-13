package org.perscholas.sdbank.dao;

import org.perscholas.sdbank.models.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MyUserRepoI extends JpaRepository <MyUser, Integer> {

    Optional <MyUser> findByEmail(String email);

}
