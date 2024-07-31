package org.kamegatze.dbeditor.backend.info.metadata.repositories.database;

import org.kamegatze.dbeditor.backend.info.metadata.repositories.AbstractRepository;

import java.util.Collection;
import java.util.Optional;


public interface DatabaseRepository<T, ID> extends AbstractRepository<T, ID>{
    Optional<T> findById(ID id);
    Collection<T> findAll();
    T save(T entity);
    T update(T entity);
    void delete(ID id);
}