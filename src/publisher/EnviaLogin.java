package publisher;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.json.JSONException;
import org.json.JSONObject;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class EnviaLogin {
	private final static String NOMBRE_EXCHANGE = "sistemaTraduccion";

	public EnviaLogin(String[]userdata) throws IOException, TimeoutException,JSONException {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		channel.exchangeDeclare(NOMBRE_EXCHANGE,
				BuiltinExchangeType.TOPIC);
		//Read user input
		String jsonString = new JSONObject()
                .put("username", userdata[0])
                .put("password", userdata[1]).toString();
		String message = jsonString;
		channel.basicPublish(NOMBRE_EXCHANGE, "login", null,
				message.getBytes());
		System.out.println("Eviando...."+message);
//		channel.close();
//		connection.close();
	}
}