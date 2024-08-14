package org.kamegatze.dbeditor.backend.info.metadata.domain.postgresql.mapper;

import org.kamegatze.dbeditor.backend.info.metadata.domain.postgresql.TableMeta;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TableMetaMapper implements RowMapper<TableMeta> {
    @Override
    public TableMeta mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new TableMeta(rs.getString("table_catalog"), rs.getString("table_schema"),
                rs.getString("table_name"), rs.getString("table_type"));
    }
}
