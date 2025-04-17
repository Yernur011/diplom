package kz.bbrent.api.diplom.domain.repository;

import kz.bbrent.api.diplom.domain.entity.TabBar;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TabBarRepository extends JpaRepository<TabBar, UUID> {
}
