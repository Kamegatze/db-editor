package org.kamegatze.dbeditor.backend.info.metadata.impl;


import org.kamegatze.dbeditor.backend.info.metadata.ConnectionDatabase;
import org.kamegatze.dbeditor.backend.info.metadata.domain.postgresql.PGDatabase;
import org.kamegatze.dbeditor.backend.info.metadata.repositories.database.DatabaseRepository;
import org.kamegatze.dbeditor.backend.info.metadata.schema.Database;
import org.kamegatze.dbeditor.backend.info.metadata.schema.impl.DatabaseImpl;
import org.springframework.jdbc.core.JdbcTemplate;



import java.util.List;
import java.util.stream.Collectors;

public class ConnectionPostgresQLImpl implements ConnectionDatabase {

    private String name;
    private DatabaseRepository<PGDatabase, Long> databaseRepository;
    public ConnectionPostgresQLImpl(String name, DatabaseRepository databaseRepository) {
        this.name = name;
        this.databaseRepository = databaseRepository;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public List<Database> getDatabase() {
        return databaseRepository.findAll().parallelStream()
                .map(item -> new DatabaseImpl(item.getDatabaseName()))
                .collect(Collectors.toList());
    }
}
