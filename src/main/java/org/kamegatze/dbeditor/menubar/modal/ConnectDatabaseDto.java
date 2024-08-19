package org.kamegatze.dbeditor.menubar.modal;

import javafx.beans.property.*;
import org.kamegatze.dbeditor.loader.properties.TypeDatabase;

public class ConnectDatabaseDto {
    private final StringProperty nameConnect;
    private final StringProperty host;
    private final StringProperty port;
    private final StringProperty username;
    private final StringProperty password;
    private final ObjectProperty<TypeDatabase> typeDatabase;

    public ConnectDatabaseDto(String host, String username, String password, String nameConnect,
                              String port, String typeDatabase) {
        this.host = new SimpleStringProperty(host);
        this.username = new SimpleStringProperty(username);
        this.password = new SimpleStringProperty(password);
        this.nameConnect = new SimpleStringProperty(nameConnect);
        this.port = new SimpleStringProperty(port);
        this.typeDatabase = new SimpleObjectProperty<>(TypeDatabase.getTypeDatabase(typeDatabase));
    }

    public String getNameConnect() {
        return nameConnect.get();
    }

    public StringProperty nameConnectProperty() {
        return nameConnect;
    }

    public String getPort() {
        return port.get();
    }

    public StringProperty portProperty() {
        return port;
    }

    public void setNameConnect(String connect) {
        this.nameConnect.set(connect);
    }

    public void setPort(String port) {
        this.port.set(port);
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

    public void setTypeDatabase(String typeDatabase) {
        this.typeDatabase.set(TypeDatabase.getTypeDatabase(typeDatabase));
    }

    public ObjectProperty<TypeDatabase> getTypeDatabase() {
        return typeDatabase;
    }

    public TypeDatabase getTypeDatabaseValue() {
        return typeDatabase.get();
    }
}
