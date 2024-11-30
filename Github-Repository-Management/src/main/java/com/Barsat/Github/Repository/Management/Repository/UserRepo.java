package com.Barsat.Github.Repository.Management.Repository;

import com.Barsat.Github.Repository.Management.Models.TheUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<TheUser, Integer> {

    TheUser findByUsername(String username);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);

}
