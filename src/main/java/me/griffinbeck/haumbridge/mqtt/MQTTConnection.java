package me.griffinbeck.haumbridge.mqtt;

import org.eclipse.paho.mqttv5.client.MqttConnectionOptions;

public class MQTTConnection {
    private String clientID;
    private MqttConnectionOptions conOpts;

    /**
     * Initialises an MQTTv5 Connection Object which holds the configuration
     * required to open a connection to an MQTTv5 server
     */
    MQTTConnection(MqttConnectionOptions options, String clientID) {
        this.clientID = clientID;
        this.conOpts = options;
    }


    public String getClientID() {
        return clientID;
    }

    public MqttConnectionOptions getConOpts() {
        return conOpts;
    }
}
