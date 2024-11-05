package com.example.javaknowledge2.test.MQTTExample;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class MQTTExample implements MqttCallback {

    public static final String BROKER_URL = "tcp://127.0.0.1:1883";
    public static final String CLIENT_ID = "JavaClient";
    public static final String TOPIC = "quan";

    public static void main(String[] args) {
        MQTTExample example = new MQTTExample();
        example.start();
    }

    public void start() {
        MemoryPersistence persistence = new MemoryPersistence();

        try {
            MqttClient client = new MqttClient(BROKER_URL, CLIENT_ID, persistence);
            MqttConnectOptions options = new MqttConnectOptions();
            options.setCleanSession(true);

            client.setCallback(this);
            client.connect(options);

            // 发送消息
            MqttMessage message = new MqttMessage("Hello, MQTT!".getBytes());
            message.setQos(1);
            client.publish(TOPIC, message);

            // 订阅主题
            client.subscribe(TOPIC);

        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void connectionLost(Throwable cause) {
        System.out.println("Connection lost: " + cause.getMessage());
    }

    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        System.out.println("Received message: " + new String(message.getPayload()) + " on topic: " + topic);
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {
        System.out.println("Delivery complete");
    }
}
