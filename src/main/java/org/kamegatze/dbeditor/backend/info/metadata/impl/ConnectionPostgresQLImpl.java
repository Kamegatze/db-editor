package org.kamegatze.dbeditor.backend.info.metadata.impl;


import org.kamegatze.dbeditor.backend.info.metadata.ConnectionDatabase;
import org.kamegatze.dbeditor.backend.info.metadata.domain.postgresql.PGDatabase;
import org.kamegatze.dbeditor.backend.info.metadata.repositories.database.DatabaseRepository;
import org.kamegatze.dbeditor.backend.info.metadata.database.Database;
import org.kamegatze.dbeditor.backend.info.metadata.database.impl.DatabaseImpl;
import org.springframework.jdbc.datasource.AbstractDriverBasedDataSource;


import java.util.List;
import java.util.stream.Collectors;

public class ConnectionPostgresQLImpl implements ConnectionDatabase {

    private String name;
    private final AbstractDriverBasedDataSource driverDataSource;
    private final DatabaseRepository<PGDatabase, Long> databaseRepository;

    public ConnectionPostgresQLImpl(String name, AbstractDriverBasedDataSource driverDataSource,
                                    DatabaseRepository<PGDatabase, Long> databaseRepository) {
        this.name = name;
        this.databaseRepository = databaseRepository;
        this.driverDataSource = driverDataSource;
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
                .map(item -> new DatabaseImpl(item.getDatabaseName(), this))
                .collect(Collectors.toList());
    }

    @Override
    public String getUrl() {
        return driverDataSource.getUrl();
    }

    @Override
    public String getUser() {
        return driverDataSource.getUsername();
    }

    @Override
    public String getPassword() {
        return driverDataSource.getPassword();
    }
}
