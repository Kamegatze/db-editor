package org.kamegatze.dbeditor.backend.info.metadata.repositories.database.postgresql.impl;

import org.kamegatze.dbeditor.backend.info.metadata.domain.postgresql.PGDatabase;
import org.kamegatze.dbeditor.backend.info.metadata.repositories.database.postgresql.DatabasePGDatabaseRepository;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Collection;
import java.util.Optional;

public class DatabasePGDatabaseRepositoryImpl implements DatabasePGDatabaseRepository {

    private final JdbcTemplate jdbcTemplate;

    public DatabasePGDatabaseRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
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
    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }
}
