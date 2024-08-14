package org.kamegatze.dbeditor.backend.info.metadata.database.impl;


import org.kamegatze.dbeditor.backend.info.metadata.ConnectionDatabase;
import org.kamegatze.dbeditor.backend.info.metadata.database.Database;
import org.kamegatze.dbeditor.backend.info.metadata.database.schema.Schema;
import org.kamegatze.dbeditor.backend.info.metadata.database.schema.impl.SchemaImpl;
import org.kamegatze.dbeditor.backend.info.metadata.domain.postgresql.SchemaMeta;
import org.kamegatze.dbeditor.backend.info.metadata.repositories.database.DatabaseRepository;
import org.kamegatze.dbeditor.backend.info.metadata.repositories.database.postgresql.impl.DatabaseSchemaMetaRepositoryImpl;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class DatabaseImpl implements Database {

    private final String name;
    private final DatabaseRepository<SchemaMeta, String> databaseRepository;
    public DatabaseImpl(String name, ConnectionDatabase connectionDatabase) {
        this.name = name;
        this.databaseRepository = new DatabaseSchemaMetaRepositoryImpl(getJdbcTemplate(connectionDatabase));
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<Schema> getSchemas() {
        return databaseRepository.findAll()
                .parallelStream()
                .map(item -> new SchemaImpl(item.getSchemaName(), databaseRepository.getJdbcTemplate()))
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public String toString() {
        return "SchemaImpl{" +
                "name='" + name + '\'' +
                '}';
    }

    private JdbcTemplate getJdbcTemplate(final ConnectionDatabase connectionDatabase) {
        final String url = String.format("%s%s", connectionDatabase.getUrl(), name);
        SimpleDriverDataSource simpleDriverDataSource;
        try {
            Driver driver = DriverManager.getDriver(url);
            simpleDriverDataSource = new SimpleDriverDataSource(driver, url,
                    connectionDatabase.getUser(), connectionDatabase.getPassword());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return new JdbcTemplate(simpleDriverDataSource);
    }
}
