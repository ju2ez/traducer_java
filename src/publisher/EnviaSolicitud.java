package publisher;

import java.io.IOException;
import java.io.StringWriter;
import java.util.concurrent.TimeoutException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import translatorIAP.SharedClass;

public class EnviaSolicitud {
	private final static String NOMBRE_EXCHANGE = "sistemaTraduccion";
	

	public EnviaSolicitud(String word, String option, String format)
			throws IOException, TimeoutException, JSONException, ParserConfigurationException, TransformerException {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		channel.exchangeDeclare(NOMBRE_EXCHANGE, BuiltinExchangeType.TOPIC);

		String petition = "";
		if (format.equals("json") && option.equals("spanishToEnglish")) {
			petition = creaJson(SharedClass.getLoginMessage(), "espanyol", "ingles", word);
		} else if (format.equals("json") && option.equals("englishToSpanish")) {
			petition = creaJson(SharedClass.getLoginMessage(), "ingles", "espanyol", word);
		} else if (format.equals("xml") && option.equals("spanishToEnglish")) {
			petition = creaXml(SharedClass.getLoginMessage(), "espanyol", "ingles", word);
		} else if (format.equals("xml") && option.equals("englishToSpanish")) {
			petition = creaXml(SharedClass.getLoginMessage(), "ingles", "espanyol", word);
		}
		String message = petition;
		if (SharedClass.isXmlButtonClicked() == false)
			channel.basicPublish(NOMBRE_EXCHANGE, "solicitud", null, message.getBytes());
		else
			channel.basicPublish(NOMBRE_EXCHANGE, "solicitudXML", null, message.getBytes());
			System.out.println("Eviando...." + message);
		channel.close();
		connection.close();
	}

	public static String creaJson(String iden, String ori, String dest, String pal) throws JSONException {
		JSONObject res = new JSONObject().put("cliente_id", iden).put("idioma_origen", ori).put("idioma_destino", dest)
				.put("palabra", pal);
		return res.toString();
	}

	public static String creaXml(String iden, String ori, String dest, String pal)
			throws ParserConfigurationException, TransformerException {
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		Document doc = docBuilder.newDocument();
		Element rootElement = doc.createElement("peticion");
		doc.appendChild(rootElement);
		Element id = doc.createElement("cliente_id");
		id.appendChild(doc.createTextNode(iden));
		rootElement.appendChild(id);
		Element idioma_origen = doc.createElement("idioma_origen");
		idioma_origen.appendChild(doc.createTextNode(ori));
		rootElement.appendChild(idioma_origen);
		Element idioma_destino = doc.createElement("idioma_destino");
		idioma_destino.appendChild(doc.createTextNode(dest));
		rootElement.appendChild(idioma_destino);
		Element palabra = doc.createElement("palabra");
		palabra.appendChild(doc.createTextNode(pal));
		rootElement.appendChild(palabra);
		// write the content into xml file
		DOMSource source = new DOMSource(doc);
		try {
			StringWriter sw = new StringWriter();
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer transformer = tf.newTransformer();
			transformer.transform(source, new StreamResult(sw));
			return sw.toString();
		} catch (Exception ex) {
			throw new RuntimeException("Error converting to String", ex);
		}
	}
}