package org.kamegatze.dbeditor.backend.info.metadata;

import org.kamegatze.dbeditor.backend.info.metadata.schema.Database;

import java.util.List;

public interface ConnectionDatabase {
    String getName();
    void setName(String name);
    List<Database> getDatabase();
}
