package kz.bbrent.api.diplom.service.crud.core;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CrudService<E, I> {
    E save(E entity);
    E update(E entity);
    void delete(I id);
    List<E> findAll();
    Page<E> findAll(Pageable pageable);
    E findById(I id);
}
