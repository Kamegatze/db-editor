package org.kamegatze.dbeditor.backend.info.metadata.database.schema.impl;

import org.kamegatze.dbeditor.backend.info.metadata.database.schema.Schema;
import org.kamegatze.dbeditor.backend.info.metadata.database.schema.table.Table;
import org.kamegatze.dbeditor.backend.info.metadata.database.schema.table.impl.TableImpl;
import org.kamegatze.dbeditor.backend.info.metadata.database.schema.view.impl.ViewImpl;
import org.kamegatze.dbeditor.backend.info.metadata.domain.postgresql.TableMeta;
import org.kamegatze.dbeditor.backend.info.metadata.repositories.database.DatabaseRepository;
import org.kamegatze.dbeditor.backend.info.metadata.repositories.database.postgresql.impl.DatabaseTableMetaRepositoryImpl;
import org.kamegatze.dbeditor.backend.info.metadata.database.schema.view.View;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.stream.Collectors;

public class SchemaImpl implements Schema {

    private final String name;
    private final DatabaseRepository<TableMeta, String> databaseRepository;

    public SchemaImpl(String name, JdbcTemplate jdbcTemplate) {
        this.name = name;
        this.databaseRepository = new DatabaseTableMetaRepositoryImpl(jdbcTemplate);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<Table> getTables() {
        return databaseRepository.findAll()
                .parallelStream()
                .filter(item -> item.getTableSchema().equals(name) && item.getTableType().equals("BASE TABLE"))
                .map(item -> new TableImpl(item.getTableName()))
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public List<View> getViews() {
        return databaseRepository.findAll()
                .parallelStream()
                .filter(item -> item.getTableSchema().equals(name) && item.getTableType().equals("VIEW"))
                .map(item -> new ViewImpl(item.getTableName()))
                .collect(Collectors.toUnmodifiableList());
    }
}
