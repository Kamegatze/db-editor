package org.kamegatze.dbeditor.backend.info.metadata.database.schema.table;

import org.kamegatze.dbeditor.backend.info.metadata.database.schema.table.column.Column;
import org.kamegatze.dbeditor.backend.info.metadata.database.schema.table.constraint.Constraint;
import org.kamegatze.dbeditor.backend.info.metadata.database.schema.table.index.Index;
import org.kamegatze.dbeditor.backend.info.metadata.database.schema.table.trigger.Trigger;

import java.util.List;

public interface Table {
    String getName();
    List<Column> getColumns();
    List<Constraint> getConstraints();
    List<Index> getIndexes();
    List<Trigger> getTrigger();
}
