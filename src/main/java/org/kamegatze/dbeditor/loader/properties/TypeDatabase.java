package org.kamegatze.dbeditor.loader.properties;

public enum TypeDatabase {
    MySQL(Props.PROPERTIES.getProperty("connection.databases.name.mysql")),
    PostgreSQL(Props.PROPERTIES.getProperty("connection.databases.name.postgresql")),
    Oracle(Props.PROPERTIES.getProperty("connection.databases.name.oracle"));

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
