package org.kamegatze.dbeditor.backend.info.metadata.database;

import org.kamegatze.dbeditor.backend.info.metadata.database.schema.Schema;

import java.util.List;

public interface Database {
    String getName();
    List<Schema> getSchemas();
}
