package org.kamegatze.dbeditor.loader.properties;

import org.kamegatze.dbeditor.Main;

public enum TypeDatabase {
    MYSQL(Props.PROPERTIES.getProperty("connection.databases.name.mysql")) {
        @Override
        public String toString() {
            return "MySQL";
        }
    },
    POSTGRESQL(Props.PROPERTIES.getProperty("connection.databases.name.postgresql")) {
        @Override
        public String toString() {
            return "PostgreSQL";
        }
    },
    ORACLE(Props.PROPERTIES.getProperty("connection.databases.name.oracle")) {
        @Override
        public String toString() {
            return "Oracle";
        }
    };

    private final String property;

    TypeDatabase(String property) {
        this.property = property;
    }

    public String getProperty() {
        return property;
    }

    public static TypeDatabase getTypeDatabase(final String property) {
        for (TypeDatabase type : TypeDatabase.values()) {
            if (type.getProperty().equals(property)) {
                return type;
            }
        }
        throw new IllegalArgumentException(property);
    }
}
