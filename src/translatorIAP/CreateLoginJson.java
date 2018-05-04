package translatorIAP;

import org.json.JSONObject;

public class CreateLoginJson {

	public boolean createLogin(String[] UserData) {

		// to avoid SQL Injection you should implement an unescape function on
		// the userdata
		String username = UserData[0];
		String password = UserData[1];

		JSONObject userdata = new JSONObject();

		userdata.put("username", username);
		userdata.put("password", password);

		System.out.println(userdata.toString());

		if (userdata.length() == 2)
			return true;
		else
			return false;
	}

}
