package br.instal.labs.lab_mqtt.client;

import java.util.Random;
import java.util.UUID;

import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class SensorTemperaturaPublisher {
	
	public static void main(String[] args) {
		IMqttClient publisher = null;
		try {			
			// 1.Criar o publisher:
			String publisherId = UUID.randomUUID().toString();
			publisher = new MqttClient(MyConstants.URI_BROKER, publisherId);
			
			// 3.Conecta
			MqttConnectOptions options = new MqttConnectOptions();
			options.setAutomaticReconnect(true);
			options.setCleanSession(true);
			options.setConnectionTimeout(10);
			publisher.connect(options);
			
			for(int i=0; i<10; i++) {
				// 2.Criar a mensagem
				MqttMessage msg = getTemperatureMessage();
				msg.setQos(0);
				msg.setRetained(true);
				
				// 4.Publicar
				publisher.publish(MyConstants.TOPIC_SENSOR, msg);
				
				Thread.sleep(2000);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {			
			// 5.Desconectar
			try {
				publisher.disconnect();
				publisher.close();
			} catch (MqttException e) {
				// TODO : Não precisa fazer nada
			}
		}
		
	}

	private static MqttMessage getTemperatureMessage() {
		Random r = new Random();
		double temperatura = 80 + r.nextDouble() * 20;
		String temperaturaStr = String.format("T:%04.2f", temperatura);
		byte[] payload = temperaturaStr.getBytes();
		return new MqttMessage(payload);
	}
}
