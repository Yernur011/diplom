package kz.bbrent.api.diplom.service.crud.impl;

import jakarta.persistence.EntityNotFoundException;
import kz.bbrent.api.diplom.domain.entity.business.Items;
import kz.bbrent.api.diplom.domain.repository.items.ItemRepository;
import kz.bbrent.api.diplom.service.crud.ItemCrudService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ItemCrudServiceImpl implements ItemCrudService {
    private final ItemRepository itemRepository;

    @Override
    public Items save(Items entity) {
        return itemRepository.save(entity);
    }

    @Override
    public Items update(Items entity) {
        return Optional.of(entity)
                .filter(item -> item.getId() != null)
                .map(itemRepository::save)
                .orElseThrow(() -> new EntityNotFoundException("Item with id " + entity.getId() + " not found"));
    }

    @Override
    public void delete(UUID id) {
        itemRepository.deleteById(id);
    }

    @Override
    public List<Items> findAll() {
        return itemRepository.findAll();
    }

    @Override
    public Page<Items> findAll(Pageable pageable) {
        return itemRepository.findAll(pageable);
    }

    @Override
    public Items findById(UUID id) {
        return itemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Item with id " + id + " not found"));
    }
}
