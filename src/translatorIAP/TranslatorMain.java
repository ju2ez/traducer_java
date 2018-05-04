package translatorIAP;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.ParseException;

import consumer.LoggedPeticion;
import consumer.TranslatePeticion;
import gui.*;
import publisher.EnviaSolicitud;;
/**
 * 
 * @author Julian and Felix
 * 
 * version 4. 
 * - sharedClass communication now via getter/setter
 * - fixed ClassNames
 * - deleted unused classes
 */




public class TranslatorMain {

	
	@SuppressWarnings("unused")
	public static void main(String[] args) throws IOException, TimeoutException, JSONException,
			ParserConfigurationException, TransformerException, ParseException, InterruptedException {

		boolean clientID = false;

		do {
			GuiMainPage myMain = new GuiMainPage();
			myMain.runGui();

			System.out.println(myMain.buttonClicked);
			while (SharedClass.isMainButtonClicked() == false) {
				Thread.sleep(10);
			}

			String[] userdata = SharedClass.getUserdata();

			CreateLoginJson createLogin = new CreateLoginJson();
			createLogin.createLogin(userdata);
			LoggedPeticion receiveLogin = new LoggedPeticion();
			publisher.EnviaLogin sendLogin = new publisher.EnviaLogin(userdata);
			GuiPleaseWait pleaseWait = new GuiPleaseWait();
			pleaseWait.runGui();
			
			int i = 0;
			while (SharedClass.isLoginReceive() == false) {
				i++;
				Thread.sleep(10);
			}

			pleaseWait.stopGui();
			JSONObject myObj = new JSONObject();
			myObj.put("clientID", SharedClass.getLoginMessage());
			System.out.println("transMain: clientID: " + myObj.toString());

			if (SharedClass.isTryLogin() == false) {
				SharedClass.resetLogin();
			}

			else {
				receiveLogin = null;
				sendLogin = null;
			}

		} while (SharedClass.isTryLogin() == false); // while Client ID of the
													// received message
		// is wrong

		do {
			GuiTranslate myTranslate = new GuiTranslate();
			myTranslate.runGui();
			int i = 0;

			while (SharedClass.getEnglish().length() < 1 && SharedClass.getSpanish().length() < 1) {
				Thread.sleep(10);
			}
			if (!SharedClass.getEnglish().isEmpty()) {

				GuiPleaseWait pleaseWait = new GuiPleaseWait();
				pleaseWait.runGui();
				SharedClass.setWord(SharedClass.getEnglish());

				if (!SharedClass.isXmlButtonClicked()) {
					TranslatePeticion receiveTranslation = new TranslatePeticion();
					EnviaSolicitud sendWord = new EnviaSolicitud(SharedClass.getEnglish(), "englishToSpanish", "json");
					Thread.sleep(100);
					while (SharedClass.isTranslationReceived() == false) {
						i++;
						Thread.sleep(10);
					}

					receiveTranslation = null;

				}

				else if (SharedClass.isXmlButtonClicked()) {
					TranslatePeticion receiveTranslation = new TranslatePeticion();
					EnviaSolicitud sendWord = new EnviaSolicitud(SharedClass.getEnglish(), "englishToSpanish", "xml");
					Thread.sleep(100);
					while (SharedClass.isTranslationReceived() == false) {
						i++;
						Thread.sleep(10);
					}

					receiveTranslation = null;
				}

				if (SharedClass.isTranslationPossible() == true) {
					// here check the received Data
					String[] transWord = SharedClass.getTransMessage().split("=");
					String strBuf = transWord[1];
					strBuf = strBuf.replaceAll("}]", "");
					SharedClass.setTranslation(strBuf);
					pleaseWait.stopGui();
					GuiTranslationPossible myTransPossible = new GuiTranslationPossible();
					myTransPossible.runGui();
					while (SharedClass.isBackToTranslateButton() == false) {
						Thread.sleep(10);
					}
					System.out.println("baaaack");
					pleaseWait = null;
					myTransPossible = null;
					myTranslate = null;
					SharedClass.reset();

				}

				else {
					pleaseWait.stopGui();
					GuiTranslationFail failTrans = new GuiTranslationFail();
					failTrans.runGui();
					while (SharedClass.isBackToTranslateButton() == false) {
						Thread.sleep(10);
					}
					System.out.println("baaaack");
					pleaseWait = null;
					failTrans = null;
					myTranslate = null;
					SharedClass.reset();
				}

			}

			else if (!SharedClass.getSpanish().isEmpty()) {

				SharedClass.setWord(SharedClass.getSpanish());
				GuiPleaseWait pleaseWait = new GuiPleaseWait();
				pleaseWait.runGui();

				if (!SharedClass.isXmlButtonClicked()) {
					TranslatePeticion receiveTranslation = new TranslatePeticion();
					EnviaSolicitud sendWord = new EnviaSolicitud(SharedClass.getSpanish(), "spanishToEnglish", "json");
					Thread.sleep(100);
					while (SharedClass.isTranslationReceived() == false) {
						i++;
						Thread.sleep(10);
					}
					receiveTranslation = null;

				} else if (SharedClass.isXmlButtonClicked()) {
					TranslatePeticion receiveTranslation = new TranslatePeticion();
					EnviaSolicitud sendWord = new EnviaSolicitud(SharedClass.getSpanish(), "spanishToEnglish", "xml");
					Thread.sleep(100);
					while (SharedClass.isTranslationReceived() == false) {
						i++;
						Thread.sleep(10);
					}
					receiveTranslation = null;
				}

				if (SharedClass.isTranslationPossible() == true) {

					// here check the received Data
					String[] transWord = SharedClass.getTransMessage().split("=");
					String strBuf = transWord[1];
					strBuf = strBuf.replaceAll("}]", "");
					SharedClass.setTranslation(strBuf);
					pleaseWait.stopGui();
					GuiTranslationPossible myTransPossible = new GuiTranslationPossible();
					myTransPossible.runGui();
					while (SharedClass.isBackToTranslateButton() == false) {
						Thread.sleep(10);
					}
					pleaseWait = null;
					myTransPossible = null;
					myTranslate = null;
					SharedClass.reset();
				} else {
					pleaseWait.stopGui();
					GuiTranslationFail failTrans = new GuiTranslationFail();
					failTrans.runGui();
					while (SharedClass.isBackToTranslateButton() == false) {
						Thread.sleep(10);
					}
					System.out.println("baaaack");
					pleaseWait = null;
					failTrans = null;
					myTranslate = null;
					SharedClass.reset();
				}

			}
		} while (true);

	}

}
