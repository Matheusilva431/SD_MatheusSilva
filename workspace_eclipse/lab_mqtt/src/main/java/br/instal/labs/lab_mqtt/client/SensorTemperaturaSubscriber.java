package br.instal.labs.lab_mqtt.client;

import java.util.UUID;

import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;

public class SensorTemperaturaSubscriber {
	
	public static void main(String[] args) {
		
		String subscriberId = UUID.randomUUID().toString();
		IMqttClient subscriber = null;
		try {
			subscriber = new MqttClient(MyConstants.URI_BROKER, subscriberId);
			MqttConnectOptions options = new MqttConnectOptions();
			options.setAutomaticReconnect(true);
			options.setCleanSession(true);
			options.setConnectionTimeout(10);
			subscriber.connect(options);
			System.out.println("Subscriber connectado e aguardando msg...");
			
			// recebendo as menssagens
			subscriber.subscribe(MyConstants.TOPIC_SENSOR, (topic, msg) -> {
				// byte[] payload = msg.getPayload();
				System.out.println("Mensagem recebida recebido: " + msg);
				System.out.println("Topic especifico: " + topic);
			});
			
		} catch (MqttException e) {
			e.printStackTrace();
		} finally {
			try {
				subscriber.close();
			} catch (MqttException e) {
				// TODO: Não faz nada
			}
		}
		
	}
	
}
