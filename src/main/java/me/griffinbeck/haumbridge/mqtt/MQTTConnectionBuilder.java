package me.griffinbeck.haumbridge.mqtt;

import org.eclipse.paho.mqttv5.client.MqttConnectionOptions;

public class MQTTConnectionBuilder {
    private final MqttConnectionOptions connectionOptions;
    private String clientID;

    public MQTTConnectionBuilder() {
        connectionOptions = new MqttConnectionOptions();
    }

    public MQTTConnectionBuilder setHostURI(String uri) {
        connectionOptions.setServerURIs(new String[]{uri});
        return this;
    }

    public MQTTConnectionBuilder setClientID(String clientID) {
        this.clientID = clientID;
        return this;
    }

    public MQTTConnectionBuilder setKeepAlive(Integer keepAlive) {
        connectionOptions.setKeepAliveInterval(keepAlive);
        return this;
    }

    public MQTTConnectionBuilder setUser(String user) {
        connectionOptions.setUserName(user);
        return this;
    }

    public MQTTConnectionBuilder setPassword(String password) {
        connectionOptions.setPassword(password.getBytes());
        return this;
    }

    public MQTTConnectionBuilder setAuth(String user, String password) {
        return setUser(user).setPassword(password);
    }

    public MQTTConnectionBuilder cleanStart() {
        connectionOptions.setCleanStart(true);
        return this;
    }

    public MQTTConnectionBuilder maxInflightMessages(Integer numMessages) {
        connectionOptions.setReceiveMaximum(numMessages);
        return this;
    }

    public MQTTConnectionBuilder autoReconnect() {
        return doAutoReconnect(true);
    }

    public MQTTConnectionBuilder doAutoReconnect(boolean autoReconnect) {
        connectionOptions.setAutomaticReconnect(autoReconnect);
        return this;
    }

    public MQTTConnection build() {
        if (clientID == null || clientID.isBlank()) {
            // No client ID provided, generate one from the process ID
            long pid = Thread.currentThread().getId(); //ProcessHandle.current().pid();
            clientID = "mqtt-client-" + pid;
        }
        return new MQTTConnection(connectionOptions, clientID);
    }
}
