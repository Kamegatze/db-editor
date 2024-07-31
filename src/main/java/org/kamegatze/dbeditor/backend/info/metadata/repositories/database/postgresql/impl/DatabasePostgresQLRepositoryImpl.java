package org.kamegatze.dbeditor.backend.info.metadata.repositories.database.postgresql.impl;

import org.kamegatze.dbeditor.backend.info.metadata.domain.postgresql.PGDatabase;
import org.kamegatze.dbeditor.backend.info.metadata.repositories.database.postgresql.DatabasePostgresQLRepository;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Collection;
import java.util.Optional;

public class DatabasePostgresQLRepositoryImpl implements DatabasePostgresQLRepository {

    private JdbcTemplate jdbcTemplate;

    public DatabasePostgresQLRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public DatabasePostgresQLRepositoryImpl() {
    }

    @Override
    public Optional<PGDatabase> findById(Long id) {
        return getById(id, jdbcTemplate);
    }

    @Override
    public Collection<PGDatabase> findAll() {
        return getAll(jdbcTemplate).stream()
                .filter(item -> !item.isTemplate())
                .toList();
    }

    @Override
    public PGDatabase save(PGDatabase entity) {
        return save(entity, jdbcTemplate);
    }

    @Override
    public PGDatabase update(PGDatabase entity) {
        return update(entity, jdbcTemplate);
    }

    @Override
    public void delete(Long aLong) {
        delete(aLong, jdbcTemplate);
    }
}
