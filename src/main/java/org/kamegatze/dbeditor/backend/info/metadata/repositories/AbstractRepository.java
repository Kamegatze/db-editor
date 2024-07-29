package org.kamegatze.dbeditor.backend.info.metadata.repositories;

import org.kamegatze.dbeditor.backend.info.metadata.annotions.database.Table;
import org.kamegatze.dbeditor.backend.info.metadata.utility.ReflectionUtility;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public interface AbstractRepository<T, ID> {

    default Optional<T> getById(ID id, JdbcTemplate jdbcTemplate) {
        final String tableName = getTableName();
        if (tableName.isEmpty()) {
            return Optional.empty();
        }
        String sql = String.format("select * from %s where id = ?", tableName);
        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, getRowMapper(), id));
    }

    default Collection<T> getAll(JdbcTemplate jdbcTemplate) {
        final String tableName = getTableName();
        if (tableName.isEmpty()) {
            return List.of();
        }
        String sql = String.format("select * from %s", tableName);
        return jdbcTemplate.query(sql, getRowMapper());
    }


    //todo moving to the utility class
    default String getTableName() {
        ReflectionUtility reflectionUtility = new ReflectionUtility();
        Optional<ParameterizedType> interfacesWithGenerics = reflectionUtility
                .getInterfacesWithGenerics(getClass());

        if (interfacesWithGenerics.isPresent()) {
            ParameterizedType parameterizedType = interfacesWithGenerics.get();
            if (parameterizedType.getActualTypeArguments().length != 0) {
                Type actualTypeArgument = parameterizedType.getActualTypeArguments()[0];
                if (actualTypeArgument instanceof Class<?> clazz) {
                    Table table = clazz.getAnnotation(Table.class);
                    if (Objects.nonNull(table)) {
                        return table.name();
                    }
                }
            }
        }
        return "";
    }

    //todo moving to the utility class
    @SuppressWarnings("unchecked")
    default RowMapper<T> getRowMapper() {
        ReflectionUtility reflectionUtility = new ReflectionUtility();
        Optional<ParameterizedType> interfacesWithGenerics = reflectionUtility
                .getInterfacesWithGenerics(getClass());

        if (interfacesWithGenerics.isPresent()) {
            ParameterizedType parameterizedType = interfacesWithGenerics.get();
            if (parameterizedType.getActualTypeArguments().length != 0) {
                Type actualTypeArgument = parameterizedType.getActualTypeArguments()[0];
                if (actualTypeArgument instanceof Class<?> clazz) {
                    Table table = clazz.getAnnotation(Table.class);
                    if (Objects.nonNull(table)) {
                        try {
                            return (RowMapper<T>) table.mapper().getConstructor().newInstance();
                        } catch (InvocationTargetException | InstantiationException | IllegalAccessException |
                                 NoSuchMethodException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
        }
        return null;
    }
}
