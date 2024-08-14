package org.kamegatze.dbeditor.backend.info.metadata.repositories.database.postgresql;

import org.kamegatze.dbeditor.backend.info.metadata.domain.postgresql.TableMeta;
import org.kamegatze.dbeditor.backend.info.metadata.repositories.database.DatabaseRepository;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Optional;

public interface DatabaseTableMetaRepository extends DatabaseRepository<TableMeta, String> {

    @Override
    default Optional<TableMeta> getById(String s, JdbcTemplate jdbcTemplate) {
        throw new UnsupportedOperationException();
    }
}
