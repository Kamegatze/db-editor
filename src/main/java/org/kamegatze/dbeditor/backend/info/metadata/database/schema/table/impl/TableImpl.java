package org.kamegatze.dbeditor.backend.info.metadata.database.schema.table.impl;

import org.kamegatze.dbeditor.backend.info.metadata.database.schema.table.Table;
import org.kamegatze.dbeditor.backend.info.metadata.database.schema.table.column.Column;
import org.kamegatze.dbeditor.backend.info.metadata.database.schema.table.constraint.Constraint;
import org.kamegatze.dbeditor.backend.info.metadata.database.schema.table.index.Index;
import org.kamegatze.dbeditor.backend.info.metadata.database.schema.table.trigger.Trigger;

import java.util.List;

public class TableImpl implements Table {

    private final String name;

    public TableImpl(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<Column> getColumns() {
        return List.of();
    }

    @Override
    public List<Constraint> getConstraints() {
        return List.of();
    }

    @Override
    public List<Index> getIndexes() {
        return List.of();
    }

    @Override
    public List<Trigger> getTrigger() {
        return List.of();
    }
}
