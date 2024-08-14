package org.kamegatze.dbeditor.backend.info.metadata.repositories.database.postgresql.impl;

import org.kamegatze.dbeditor.backend.info.metadata.domain.postgresql.SchemaMeta;
import org.kamegatze.dbeditor.backend.info.metadata.repositories.database.postgresql.DatabaseSchemaMetaRepository;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Collection;
import java.util.Optional;

public class DatabaseSchemaMetaRepositoryImpl implements DatabaseSchemaMetaRepository {

    private final JdbcTemplate jdbcTemplate;

    public DatabaseSchemaMetaRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<SchemaMeta> findById(String schemaName) {
        return getById(schemaName, jdbcTemplate);
    }

    @Override
    public Collection<SchemaMeta> findAll() {
        return getAll(jdbcTemplate);
    }

    @Override
    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }
}
