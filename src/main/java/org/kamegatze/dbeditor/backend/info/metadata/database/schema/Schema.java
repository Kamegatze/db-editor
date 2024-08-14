package org.kamegatze.dbeditor.backend.info.metadata.database.schema;

import org.kamegatze.dbeditor.backend.info.metadata.database.schema.table.Table;
import org.kamegatze.dbeditor.backend.info.metadata.database.schema.view.View;

import java.util.List;

public interface Schema {

    String getName();
    List<Table> getTables();
    List<View> getViews();
}
