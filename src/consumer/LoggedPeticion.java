package consumer;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import translatorIAP.SharedClass;

public class LoggedPeticion {
	private final static String NOMBRE_EXCHANGE = "sistemaTraduccion";
	public LoggedPeticion() throws IOException, TimeoutException {
		
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		channel.exchangeDeclare(NOMBRE_EXCHANGE, BuiltinExchangeType.TOPIC);
		String COLA_CONSUMER = channel.queueDeclare().getQueue();
		channel.queueBind(COLA_CONSUMER, NOMBRE_EXCHANGE, "respuestaLogin");
		DefaultConsumer consumer = new DefaultConsumer(channel) {
			public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
					byte[] body) throws IOException {
				String message = new String(body, "UTF-8");
				System.out.println("Recibido: '" + message + "'");
				if (message.equals("[]")) {
					System.out.println("Invalid user or password, please try again :)");
					SharedClass.setTryLogin(false);
					SharedClass.setLoginReceive(true);
				} else {
					System.out.println("Welcome!");
					SharedClass.setTryLogin(true);
					SharedClass.setLoginMessage(message);
					SharedClass.setLoginReceive(true);
					connection.close();
				}

			}
		};
		channel.basicConsume(COLA_CONSUMER, true, consumer);
		
	}

}