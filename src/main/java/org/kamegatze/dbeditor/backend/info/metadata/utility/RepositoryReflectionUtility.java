package org.kamegatze.dbeditor.backend.info.metadata.utility;

import org.kamegatze.dbeditor.backend.info.metadata.annotions.database.Column;
import org.kamegatze.dbeditor.backend.info.metadata.annotions.database.Id;
import org.kamegatze.dbeditor.backend.info.metadata.annotions.database.Table;
import org.kamegatze.dbeditor.backend.info.metadata.repositories.exceptions.NotFoundFieldIdException;
import org.kamegatze.dbeditor.backend.info.metadata.repositories.exceptions.NotFoundNameTableException;
import org.springframework.jdbc.core.RowMapper;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class RepositoryReflectionUtility {


    @SuppressWarnings("unchecked")
    public static <T> Optional<RowMapper<T>> getRowMapper(Class<?> clazz, int index) {
        Optional<Table> annotation = getAnnotation(clazz, Table.class, index);
        if (annotation.isPresent()) {
            Table table = annotation.get();
            if (Objects.nonNull(table.mapper())) {
                try {
                    return Optional.of((RowMapper<T>) table.mapper().getConstructor().newInstance());
                } catch (NoSuchMethodException | InvocationTargetException | InstantiationException |
                         IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return Optional.empty();
    }

    public static String getTableName(Class<?> clazz, int index) {
        Optional<Table> actualTypeArgument = getAnnotation(clazz, Table.class, index);
        if (actualTypeArgument.isPresent()) {
            Table table = actualTypeArgument.get();
            return table.name();
        }
        return "";
    }

    public static String generateSqlSave(Object entity) {
        final String tableName = getTableNameViaEntity(entity);
        if (tableName.isEmpty()) {
            throw new NotFoundNameTableException("Not found name table");
        }
        Field[] fields = entity.getClass().getDeclaredFields();
        return generateSqlSaveViaFields(fields, tableName);
    }

    public static String generateSqlUpdate(Object entity) {
        final String tableName = getTableNameViaEntity(entity);
        if (tableName.isEmpty()) {
            throw new NotFoundNameTableException("Not found name table");
        }
        Field[] fields = entity.getClass().getDeclaredFields();
        return generateSqlUpdateViaFields(fields, tableName);
    }

    public static Object[] getArgsForSave(Object entity) {
        Field[] fields = entity.getClass().getDeclaredFields();
        List<Object> args = new ArrayList<>();
        for (Field field : fields) {
            Column annotation = field.getAnnotation(Column.class);
            if (Objects.nonNull(annotation)) {
                args.add(getPrivateFieldValue(field, entity));
            }
        }
        return args.toArray();
    }

    public static Object[] getArgsForUpdate(Object entity) {
        Field[] fields = entity.getClass().getDeclaredFields();
        List<Object> args = new ArrayList<>();
        Field fieldId = null;
        for (Field field : fields) {
            Id id = field.getAnnotation(Id.class);
            Column annotation = field.getAnnotation(Column.class);
            if (Objects.nonNull(annotation)) {
                if (Objects.nonNull(id)) {
                    fieldId = field;
                } else {
                    args.add(getPrivateFieldValue(field, entity));
                }
            }
        }
        if (Objects.isNull(fieldId)) {
            throw new NotFoundFieldIdException("Not found field id");
        }
        args.add(getPrivateFieldValue(fieldId, entity));
        return args.toArray();
    }

    private static String generateSqlUpdateViaFields(Field[] fields, String tableName) {
        String sql = String.format("update %s set ", tableName);
        StringBuilder stringBuilder = new StringBuilder(sql);
        Column columnId = null;
        for (Field field : fields) {
            char character = stringBuilder.charAt(stringBuilder.length() - 1);
            if (character == '?') {
                stringBuilder.append(',');
            }
            Id id = field.getAnnotation(Id.class);
            Column annotation = field.getAnnotation(Column.class);
            if (Objects.nonNull(annotation)) {
                if (Objects.nonNull(id)) {
                    columnId = annotation;
                } else {
                    if (Objects.nonNull(annotation.name())) {
                        stringBuilder.append(String.format(" %s = ?", annotation.name()));
                    }
                }
            }
        }
        if (Objects.isNull(columnId)) {
            throw new NotFoundFieldIdException("Not found field id");
        }
        stringBuilder.append(String.format(" where %s = ?", columnId.name()));
        return stringBuilder.toString();
    }

    private static Optional<Type> getActualTypeArgument(Class<?> clazz, int index) {
        Optional<ParameterizedType> interfacesWithGenerics = getInterfacesWithGenerics(clazz);

        if (interfacesWithGenerics.isPresent()) {
            ParameterizedType parameterizedType = interfacesWithGenerics.get();
            if (parameterizedType.getActualTypeArguments().length > index) {
                return Optional.of(parameterizedType.getActualTypeArguments()[index]);
            }
        }
        return Optional.empty();
    }

    private static <T extends Annotation> Optional<T> getAnnotation(Class<?> clazz, Class<T> annotation, int index) {
        Optional<Type> actualTypeArgument = getActualTypeArgument(clazz, index);
        if (actualTypeArgument.isPresent()) {
            Type type = actualTypeArgument.get();
            if (type instanceof Class<?> clazzObject) {
                T classAnnotation= clazzObject.getAnnotation(annotation);
                if (Objects.nonNull(classAnnotation)) {
                    return Optional.of(classAnnotation);
                }
            }
        }
        return Optional.empty();
    }

    private static Optional<ParameterizedType> getInterfacesWithGenerics(final Class<?> fromInterfaces) {
        for (Type interfaceGeneric : fromInterfaces.getGenericInterfaces()) {
            if (interfaceGeneric instanceof ParameterizedType parameterizedType) {
                return Optional.of(parameterizedType);
            }
        }

        return Optional.ofNullable(
                getInterfaceByClazzRecursive(
                        fromInterfaces.getInterfaces()
                )
        );
    }

    private static ParameterizedType getInterfaceByClazzRecursive(Class<?>[] fromInterfaces) {
        for (Class<?> fromInterface : fromInterfaces) {
            for (Type interfaceGeneric : fromInterface.getGenericInterfaces()) {
                if (interfaceGeneric instanceof ParameterizedType parameterizedType) {
                    return parameterizedType;
                }
            }
            return getInterfaceByClazzRecursive(fromInterface.getInterfaces());
        }
        return null;
    }

    private static String getTableNameViaEntity(Object entity) {
        Table table = entity.getClass().getAnnotation(Table.class);
        if (Objects.nonNull(table)) {
            if (Objects.nonNull(table.name())) {
                return table.name();
            }
        }
        return "";
    }

    private static String generateSqlSaveViaFields(Field[] fields, String tableName) {
        String sql = String.format("insert into %s", tableName);
        StringBuilder stringBuilder = new StringBuilder(sql);
        stringBuilder.append('(');
        int lengthColumns = 0;
        for (Field field : fields) {
            char c = stringBuilder.charAt(stringBuilder.length() - 1);
            if (c != '(' && c != ',') {
                stringBuilder.append(',');
            }
            Column annotation = field.getAnnotation(Column.class);
            if (Objects.nonNull(annotation)) {
                lengthColumns++;
                stringBuilder.append(annotation.name());
            }
        }
        stringBuilder.append(')');
        stringBuilder.append(" values (");
        for (int i = 0; i < lengthColumns; i++) {
            char c = stringBuilder.charAt(stringBuilder.length() - 1);
            if (c != '(' && c != ',') {
                stringBuilder.append(',');
            }
            stringBuilder.append('?');
        }
        stringBuilder.append(')');
        return stringBuilder.toString();
    }

    private static Object getPrivateFieldValue(Field field, Object entity) {
        try {
            field.setAccessible(true);
            return field.get(entity);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

}
