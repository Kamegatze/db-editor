package org.kamegatze.dbeditor.backend.info.metadata.annotions.database;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.jdbc.core.RowMapper;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Table {
    String name();
    Class<? extends RowMapper<?>> mapper();
}
