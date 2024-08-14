package org.kamegatze.dbeditor.backend.info.metadata.repositories.database;

import org.kamegatze.dbeditor.backend.info.metadata.repositories.AbstractRepository;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Collection;
import java.util.Optional;


public interface DatabaseRepository<T, ID> extends AbstractRepository<T, ID>{
    Optional<T> findById(ID id);
    Collection<T> findAll();

    default T save(T entity) {
        throw new UnsupportedOperationException();
    }
    default T update(T entity) {
        throw new UnsupportedOperationException();
    }
    default void delete(ID id) {
        throw new UnsupportedOperationException();
    };

    JdbcTemplate getJdbcTemplate();
}