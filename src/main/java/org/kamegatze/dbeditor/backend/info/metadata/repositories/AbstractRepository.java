package org.kamegatze.dbeditor.backend.info.metadata.repositories;

import org.kamegatze.dbeditor.backend.info.metadata.repositories.exceptions.NotFoundNameTableException;
import org.kamegatze.dbeditor.backend.info.metadata.repositories.exceptions.NotFoundRowMapperException;
import org.kamegatze.dbeditor.backend.info.metadata.utility.RepositoryReflectionUtility;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.*;

public interface AbstractRepository<T, ID> {

    default Optional<T> getById(ID id, JdbcTemplate jdbcTemplate) {
        final String tableName = RepositoryReflectionUtility.getTableName(getClass(), 0);
        if (tableName.isEmpty()) {
            throw new NotFoundNameTableException("Not found name table");
        }
        String sql = String.format("select * from %s where id = ?", tableName);
        Optional<RowMapper<T>> rowMapper = RepositoryReflectionUtility.getRowMapper(getClass(), 0);
        if (rowMapper.isPresent()) {
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, rowMapper.get(), id));
        }
        throw new NotFoundRowMapperException("Not found row mapper");
    }

    default Collection<T> getAll(JdbcTemplate jdbcTemplate) {
        final String tableName = RepositoryReflectionUtility.getTableName(getClass(), 0);
        if (tableName.isEmpty()) {
            throw new NotFoundNameTableException("Not found name table");
        }
        String sql = String.format("select * from %s", tableName);
        Optional<RowMapper<T>> rowMapper = RepositoryReflectionUtility.getRowMapper(getClass(), 0);
        if (rowMapper.isPresent()) {
            return jdbcTemplate.query(sql, rowMapper.get());
        }
        throw new NotFoundRowMapperException("Not found row mapper");
    }

    default T save(T entity, JdbcTemplate jdbcTemplate) {
        final String tableName = RepositoryReflectionUtility.getTableName(getClass(), 0);
        if (tableName.isEmpty()) {
            throw new NotFoundNameTableException("Not found name table");
        }
        String sql = RepositoryReflectionUtility.generateSqlSave(entity);
        Object[] args = RepositoryReflectionUtility.getArgsForSave(entity);

        jdbcTemplate.update(sql, args);

        return entity;
    }

    default T update(T entity, JdbcTemplate jdbcTemplate) {
        final String tableName = RepositoryReflectionUtility.getTableName(getClass(), 0);
        if (tableName.isEmpty()) {
            throw new NotFoundNameTableException("Not found name table");
        }
        String sql = RepositoryReflectionUtility.generateSqlUpdate(entity);
        Object[] args = RepositoryReflectionUtility.getArgsForUpdate(entity);
        jdbcTemplate.update(sql, args);
        return entity;
    }

    default void delete(ID id, JdbcTemplate jdbcTemplate) {
        final String tableName = RepositoryReflectionUtility.getTableName(getClass(), 0);
        if (tableName.isEmpty()) {
            throw new NotFoundNameTableException("Not found name table");
        }
        String sql = String.format("delete from %s where id =?", tableName);
        jdbcTemplate.update(sql, id);
    }
}
