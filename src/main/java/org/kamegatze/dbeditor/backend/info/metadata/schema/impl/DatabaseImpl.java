package org.kamegatze.dbeditor.backend.info.metadata.schema.impl;


import org.kamegatze.dbeditor.backend.info.metadata.schema.Database;
import org.kamegatze.dbeditor.backend.info.metadata.table.Table;
import org.kamegatze.dbeditor.backend.info.metadata.view.View;

import java.util.List;

public class DatabaseImpl implements Database {

    private String name;

    public DatabaseImpl(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<Table> getTables() {
        return List.of();
    }

    @Override
    public List<View> getViews() {
        return List.of();
    }

    @Override
    public String toString() {
        return "SchemaImpl{" +
                "name='" + name + '\'' +
                '}';
    }
}
