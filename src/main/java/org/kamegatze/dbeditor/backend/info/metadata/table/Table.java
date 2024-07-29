package org.kamegatze.dbeditor.backend.info.metadata.table;

import org.kamegatze.dbeditor.backend.info.metadata.table.column.Column;
import org.kamegatze.dbeditor.backend.info.metadata.table.constraint.Constraint;
import org.kamegatze.dbeditor.backend.info.metadata.table.index.Index;
import org.kamegatze.dbeditor.backend.info.metadata.table.trigger.Trigger;

import java.util.List;

public interface Table {
    String getName();
    List<Column> getColumns();
    List<Constraint> getConstraints();
    List<Index> getIndexes();
    List<Trigger> getTrigger();
}
