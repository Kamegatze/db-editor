package org.kamegatze.dbeditor.backend.info.metadata.domain.postgresql;

import org.kamegatze.dbeditor.backend.info.metadata.annotions.database.Column;
import org.kamegatze.dbeditor.backend.info.metadata.annotions.database.Id;
import org.kamegatze.dbeditor.backend.info.metadata.annotions.database.Table;
import org.kamegatze.dbeditor.backend.info.metadata.domain.postgresql.mapper.SchemaMetaMapper;

@Table(name = "information_schema.schemata", mapper = SchemaMetaMapper.class)
public class SchemaMeta {
    @Column(name = "catalog_name")
    private String catalogName;

    @Id
    @Column(name = "schema_name")
    private String schemaName;

    @Column(name = "schema_owner")
    private String schemaOwner;

    public SchemaMeta() {
    }

    public SchemaMeta(String catalogName, String schemaName, String schemaOwner) {
        this.catalogName = catalogName;
        this.schemaName = schemaName;
        this.schemaOwner = schemaOwner;
    }

    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }

    public String getSchemaName() {
        return schemaName;
    }

    public void setSchemaName(String schemaName) {
        this.schemaName = schemaName;
    }

    public String getSchemaOwner() {
        return schemaOwner;
    }

    public void setSchemaOwner(String schemaOwner) {
        this.schemaOwner = schemaOwner;
    }
}
