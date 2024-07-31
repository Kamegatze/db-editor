package org.kamegatze.dbeditor.backend.info.metadata.domain.postgresql;

import org.kamegatze.dbeditor.backend.info.metadata.annotions.database.Column;
import org.kamegatze.dbeditor.backend.info.metadata.annotions.database.Id;
import org.kamegatze.dbeditor.backend.info.metadata.annotions.database.Table;
import org.kamegatze.dbeditor.backend.info.metadata.domain.postgresql.mapper.PGDatabaseMapper;

/** PostgreSQL database metadata. */
@Table(name = "pg_database", mapper = PGDatabaseMapper.class)
public class PGDatabase {

    @Id
    @Column(name = "oid")
    private Long id;
    @Column(name = "datname")
    private String databaseName;
    @Column(name = "datistemplate")
    private boolean isTemplate;

    public PGDatabase(Long id, String databaseName, boolean isTemplate) {
        this.id = id;
        this.databaseName = databaseName;
        this.isTemplate = isTemplate;
    }

    public PGDatabase() {
    }

    public boolean isTemplate() {
        return isTemplate;
    }

    public void setTemplate(boolean template) {
        isTemplate = template;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }
}
