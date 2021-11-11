package ru.domain.purchaser.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.domain.purchaser.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String user);
}
