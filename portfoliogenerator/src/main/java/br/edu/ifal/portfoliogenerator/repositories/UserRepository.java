package br.edu.ifal.portfoliogenerator.repositories;

import br.edu.ifal.portfoliogenerator.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUsername(String username);
}
