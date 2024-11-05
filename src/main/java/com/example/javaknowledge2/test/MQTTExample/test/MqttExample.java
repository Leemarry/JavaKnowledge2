package com.example.javaknowledge2.test.MQTTExample.test;

import org.eclipse.paho.client.mqttv3.*;

public class MqttExample implements MqttCallback {

    private static final String MQTT_BROKER_URL = "tcp://your.mqtt.broker:1883";
    private static final String CLIENT_ID = "JavaSample";
    private static final String TOPIC = "quan";

    private MqttClient client;

    public void start() {
        try {
            client = new MqttClient(MQTT_BROKER_URL, CLIENT_ID);
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(true);
            client.setCallback(this);
            client.connect(connOpts);
            client.subscribe(TOPIC);
            System.out.println("Subscribed to topic " + TOPIC);

            // 发送消息示例
            publish("Hello MQTTjava");

        } catch (MqttException me) {
            System.out.println("reason " + me.getReasonCode());
            System.out.println("msg " + me.getMessage());
            System.out.println("loc " + me.getLocalizedMessage());
            System.out.println("cause " + me.getCause());
            System.out.println("excep " + me);
            me.printStackTrace();
        }
    }

    private void publish(String message) {
        try {
            MqttMessage mqttMessage = new MqttMessage(message.getBytes());
            mqttMessage.setQos(2);
            client.publish(TOPIC, mqttMessage);
            System.out.println("Message published");
        } catch (MqttException me) {
            System.out.println("reason " + me.getReasonCode());
            System.out.println("msg " + me.getMessage());
            System.out.println("loc " + me.getLocalizedMessage());
            System.out.println("cause " + me.getCause());
            System.out.println("excep " + me);
            me.printStackTrace();
        }
    }

    @Override
    public void connectionLost(Throwable cause) {
        System.out.println("Connection lost!");
    }

    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        System.out.println("Message arrived. Topic: " + topic + " Message: " + new String(message.getPayload()));
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {
        // 可以在这里处理消息发送完成的逻辑
    }

    public static void main(String[] args) {
        MqttExample sample = new MqttExample();
        sample.start();
    }
}
