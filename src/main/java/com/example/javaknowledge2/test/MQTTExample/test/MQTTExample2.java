package com.example.javaknowledge2.test.MQTTExample.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.util.HashMap;
import java.util.Map;

public class MQTTExample2 implements MqttCallback {

    public static final String BROKER_URL_1 = "tcp://127.0.0.1:1883";
    public static final String BROKER_URL_2 = "tcp://192.168.0.1:1883";
    public static final String CLIENT_ID = "JavaClient";

    private Map<String, String[]> subscriptionMap;

    public MQTTExample2() {
        subscriptionMap = new HashMap<>();
        subscriptionMap.put(BROKER_URL_1, new String[]{"topic1_1", "topic1_2"});
        subscriptionMap.put(BROKER_URL_2, new String[]{"topic2_1", "topic2_2"});
    }

    public static void main(String[] args) {
        MQTTExample2 example = new MQTTExample2();
        example.start();
    }

    public void start() {
        for (Map.Entry<String, String[]> entry : subscriptionMap.entrySet()) {
            String brokerUrl = entry.getKey();
            String[] topics = entry.getValue();

            MemoryPersistence persistence = new MemoryPersistence();

            try {
                MqttClient client = new MqttClient(brokerUrl, CLIENT_ID, persistence);
                MqttConnectOptions options = new MqttConnectOptions();
                options.setCleanSession(true);

                client.setCallback(this);
                client.connect(options);

                // 发送消息
                sendMessage(client, "default_topic");

                // 订阅主题
                client.subscribe(topics);

            } catch (MqttException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendMessage(MqttClient client, String topic) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(new MyObject("value1", 20));
            MqttMessage message = new MqttMessage(json.getBytes());
            message.setQos(1);
            client.publish(topic, message);
        } catch (MqttException | JsonProcessingException e) {
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

    static class MyObject {
        private String name;
        private int age;

        public MyObject(String name, int age) {
            this.name = name;
            this.age = age;
        }

        // 为了方便打印和序列化，添加 getter 方法
        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }
    }
}
