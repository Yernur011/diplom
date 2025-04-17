package kz.bbrent.api.diplom.domain.repository.auth;

import kz.bbrent.api.diplom.domain.entity.auth.RegisterUser;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RegisterUserRepository extends CrudRepository<RegisterUser, Long> {
    Optional<RegisterUser> findByUsername(String email);
    void deleteByUsername(String email);
    boolean existsByUsername(String email);
}
