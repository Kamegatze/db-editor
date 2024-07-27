package org.kamegatze.dbeditor.menubar.modal;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ConnectDatabaseDto {
    private final StringProperty host;
    private final StringProperty path;
    private final StringProperty username;
    private final StringProperty password;

    public ConnectDatabaseDto(String host, String path, String username, String password) {
        this.host = new SimpleStringProperty(host);
        this.path = new SimpleStringProperty(path);
        this.username = new SimpleStringProperty(username);
        this.password = new SimpleStringProperty(password);
    }

    public String getHost() {
        return host.get();
    }

    public StringProperty hostProperty() {
        return host;
    }

    public void setHost(String host) {
        this.host.set(host);
    }

    public String getPath() {
        return path.get();
    }

    public StringProperty pathProperty() {
        return path;
    }

    public void setPath(String path) {
        this.path.set(path);
    }

    public String getUsername() {
        return username.get();
    }

    public StringProperty usernameProperty() {
        return username;
    }

    public void setUsername(String username) {
        this.username.set(username);
    }

    public String getPassword() {
        return password.get();
    }

    public StringProperty passwordProperty() {
        return password;
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    @Override
    public String toString() {
        return "ConnectDatabaseDto{" +
                "host=" + host.getValue() +
                ", path=" + path.getValue() +
                ", username=" + username.getValue() +
                ", password=" + password.getValue() +
                '}';
    }
}
