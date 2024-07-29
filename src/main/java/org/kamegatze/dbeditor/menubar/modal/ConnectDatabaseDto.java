package org.kamegatze.dbeditor.menubar.modal;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ConnectDatabaseDto {
    private final StringProperty nameConnect;
    private final StringProperty host;
    private final StringProperty port;
    private final StringProperty username;
    private final StringProperty password;

    public ConnectDatabaseDto(String host, String username, String password, String nameConnect, String port) {
        this.host = new SimpleStringProperty(host);
        this.username = new SimpleStringProperty(username);
        this.password = new SimpleStringProperty(password);
        this.nameConnect = new SimpleStringProperty(nameConnect);
        this.port = new SimpleStringProperty(port);
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

    public void getPort(String port) {
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
}
