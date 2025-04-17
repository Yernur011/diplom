package kz.bbrent.api.diplom.domain.repository.items;

import kz.bbrent.api.diplom.domain.entity.business.ItemDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ItemDetailsRepository extends JpaRepository<ItemDetails, UUID> {
    ItemDetails findItemDetailsByItemsId(UUID itemId);
}
