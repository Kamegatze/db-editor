package org.kamegatze.dbeditor.backend.info.metadata.repositories.database.postgresql.impl;

import org.kamegatze.dbeditor.backend.info.metadata.domain.postgresql.TableMeta;
import org.kamegatze.dbeditor.backend.info.metadata.repositories.database.postgresql.DatabaseTableMetaRepository;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class DatabaseTableMetaRepositoryImpl implements DatabaseTableMetaRepository {

    private final JdbcTemplate jdbcTemplate;

    public DatabaseTableMetaRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<TableMeta> findById(String id) {
        return getById(id, jdbcTemplate);
    }

    @Override
    public Collection<TableMeta> findAll() {
        return getAll(jdbcTemplate);
    }

    @Override
    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }
}
