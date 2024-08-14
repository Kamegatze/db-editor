package org.kamegatze.dbeditor.backend.info.metadata.domain.postgresql.mapper;

import org.kamegatze.dbeditor.backend.info.metadata.domain.postgresql.SchemaMeta;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SchemaMetaMapper implements RowMapper<SchemaMeta> {

    @Override
    public SchemaMeta mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new SchemaMeta(rs.getString("catalog_name"), rs.getString("schema_name"),
                rs.getString("schema_owner"));
    }
}
