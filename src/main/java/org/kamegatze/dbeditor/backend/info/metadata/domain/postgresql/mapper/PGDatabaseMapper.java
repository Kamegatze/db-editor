package org.kamegatze.dbeditor.backend.info.metadata.domain.postgresql.mapper;

import org.kamegatze.dbeditor.backend.info.metadata.domain.postgresql.PGDatabase;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PGDatabaseMapper implements RowMapper<PGDatabase> {
    @Override
    public PGDatabase mapRow(ResultSet rs, int rowNum) throws SQLException {
        PGDatabase pgDatabase = new PGDatabase();
        pgDatabase.setId(rs.getLong("oid"));
        pgDatabase.setDatabaseName(rs.getString("datname"));
        pgDatabase.setTemplate(rs.getBoolean("datistemplate"));
        return pgDatabase;
    }
}
