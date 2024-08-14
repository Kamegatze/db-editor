package org.kamegatze.dbeditor.backend.info.metadata.domain.postgresql;

import org.kamegatze.dbeditor.backend.info.metadata.annotions.database.Column;
import org.kamegatze.dbeditor.backend.info.metadata.annotions.database.Table;
import org.kamegatze.dbeditor.backend.info.metadata.domain.postgresql.mapper.TableMetaMapper;

@Table(name = "information_schema.tables", mapper = TableMetaMapper.class)
public class TableMeta {

    @Column(name = "table_catalog")
    private String tableCatalog;
    @Column(name = "table_schema")
    private String tableSchema;
    @Column(name = "table_name")
    private String tableName;
    @Column(name = "table_type")
    private String tableType;

    public TableMeta() {}

    public TableMeta(String tableCatalog, String tableSchema, String tableName, String tableType) {
        this.tableCatalog = tableCatalog;
        this.tableSchema = tableSchema;
        this.tableName = tableName;
        this.tableType = tableType;
    }

    public String getTableCatalog() {
        return tableCatalog;
    }

    public void setTableCatalog(String tableCatalog) {
        this.tableCatalog = tableCatalog;
    }

    public String getTableSchema() {
        return tableSchema;
    }

    public void setTableSchema(String tableSchema) {
        this.tableSchema = tableSchema;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableType() {
        return tableType;
    }

    public void setTableType(String tableType) {
        this.tableType = tableType;
    }
}
