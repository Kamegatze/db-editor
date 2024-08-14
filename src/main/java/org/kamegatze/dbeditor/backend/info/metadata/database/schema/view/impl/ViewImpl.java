package org.kamegatze.dbeditor.backend.info.metadata.database.schema.view.impl;

import org.kamegatze.dbeditor.backend.info.metadata.database.schema.view.View;

public class ViewImpl implements View {
    private final String name;

    public ViewImpl(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
