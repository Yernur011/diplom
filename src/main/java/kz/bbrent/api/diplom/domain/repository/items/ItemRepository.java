package kz.bbrent.api.diplom.domain.repository.items;

import kz.bbrent.api.diplom.domain.entity.business.Items;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ItemRepository extends JpaRepository<Items, UUID> {
}
