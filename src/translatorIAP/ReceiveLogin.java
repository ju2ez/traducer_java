package translatorIAP;

import org.json.JSONObject;


public class ReceiveLogin {

	public boolean checkLogin(String myMessage) {

		JSONObject loginData = new JSONObject(myMessage);
		String clientID = (String) loginData.get("clientID");
		
		System.out.println("receiveLogin: loginData: "+loginData+"   clientID: "+clientID);

		if (clientID.compareTo("-1")==0)
			return false;

		else
			return true;
	}
}
