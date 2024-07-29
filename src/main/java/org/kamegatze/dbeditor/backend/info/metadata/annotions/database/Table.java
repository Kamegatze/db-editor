package org.kamegatze.dbeditor.backend.info.metadata.annotions.database;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Table {
    String name();
    Class<?> mapper();
}
