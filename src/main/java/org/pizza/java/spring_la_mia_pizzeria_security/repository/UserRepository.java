package org.pizza.java.spring_la_mia_pizzeria_security.repository;


import java.util.Optional;

import org.pizza.java.spring_la_mia_pizzeria_security.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);
}
