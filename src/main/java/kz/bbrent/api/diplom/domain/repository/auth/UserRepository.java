package kz.bbrent.api.diplom.domain.repository.auth;


import kz.bbrent.api.diplom.domain.entity.auth.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByUsername(String username);
    boolean existsByUsername(String username);
}
