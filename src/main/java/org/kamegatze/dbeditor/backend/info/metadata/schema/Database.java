package org.kamegatze.dbeditor.backend.info.metadata.schema;

import org.kamegatze.dbeditor.backend.info.metadata.table.Table;
import org.kamegatze.dbeditor.backend.info.metadata.view.View;

import java.util.List;

public interface Database {
    String getName();
    List<Table> getTables();
    List<View> getViews();
}
