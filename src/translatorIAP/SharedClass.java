package translatorIAP;
/**
 * 
 * @author Julian and Felix
 * 
 * Shared Class: the other classes are using this with getter/setter communication
 *
 */


public class SharedClass {

	private static String[] userdata = new String[2];
	private static boolean mainButtonClicked = false;
	private static boolean tryLogin = true;
	private static boolean loginReceive = false;
	private static String loginMessage = new String();
	private static boolean translateButtonClicked = false;
	private static boolean trySendTranslate = true;
	private static String english = new String();
	private static String spanish = new String();
	private static String word = new String();
	private static String translation = new String();
	private static boolean backToTranslateButton = false;
	private static boolean xmlButtonClicked = false;
	private static boolean translationReceived = false;
	private static boolean translationPossible = false;
	private static String transMessage = new String();

	public static void reset() {
		translateButtonClicked = false;
		trySendTranslate = true;
		english = new String();
		spanish = new String();
		word = new String();
		translation = new String();
		backToTranslateButton = false;
		xmlButtonClicked = false;
		translationReceived = false;
		translationPossible = false;
	}

	public static void resetLogin() {
		mainButtonClicked = false;
		tryLogin = false;
		userdata = new String[2];
		loginReceive = false;
	}

	public static boolean isLoginReceive() {
		return loginReceive;
	}

	public static void setLoginReceive(boolean loginReceive) {
		SharedClass.loginReceive = loginReceive;
	}

	public static String getLoginMessage() {
		return loginMessage;
	}

	public static void setLoginMessage(String loginMessage) {
		SharedClass.loginMessage = loginMessage;
	}

	public static boolean isTranslationReceived() {
		return translationReceived;
	}

	public static void setTranslationReceived(boolean translationReceived) {
		SharedClass.translationReceived = translationReceived;
	}

	public static boolean isTranslationPossible() {
		return translationPossible;
	}

	public static void setTranslationPossible(boolean translationPossible) {
		SharedClass.translationPossible = translationPossible;
	}

	public static String getTransMessage() {
		return transMessage;
	}

	public static void setTransMessage(String transMessage) {
		SharedClass.transMessage = transMessage;
	}

	public static String[] getUserdata() {
		return userdata;
	}

	public static void setUserdata(String[] userdata) {
		SharedClass.userdata = userdata;
	}

	public static boolean isMainButtonClicked() {
		return mainButtonClicked;
	}

	public static void setMainButtonClicked(boolean mainButtonClicked) {
		SharedClass.mainButtonClicked = mainButtonClicked;
	}

	public static boolean isTryLogin() {
		return tryLogin;
	}

	public static void setTryLogin(boolean tryLogin) {
		SharedClass.tryLogin = tryLogin;
	}

	public static boolean isTranslateButtonClicked() {
		return translateButtonClicked;
	}

	public static void setTranslateButtonClicked(boolean translateButtonClicked) {
		SharedClass.translateButtonClicked = translateButtonClicked;
	}

	public static boolean isTrySendTranslate() {
		return trySendTranslate;
	}

	public static void setTrySendTranslate(boolean trySendTranslate) {
		SharedClass.trySendTranslate = trySendTranslate;
	}

	public static String getEnglish() {
		return english;
	}

	public static void setEnglish(String english) {
		SharedClass.english = english;
	}

	public static String getSpanish() {
		return spanish;
	}

	public static void setSpanish(String spanish) {
		SharedClass.spanish = spanish;
	}

	public static String getWord() {
		return word;
	}

	public static void setWord(String word) {
		SharedClass.word = word;
	}

	public static String getTranslation() {
		return translation;
	}

	public static void setTranslation(String translation) {
		SharedClass.translation = translation;
	}

	static boolean isBackToTranslateButton() {
		return backToTranslateButton;
	}

	public static void setBackToTranslateButton(boolean backToTranslateButton) {
		SharedClass.backToTranslateButton = backToTranslateButton;
	}

	public static boolean isXmlButtonClicked() {
		return xmlButtonClicked;
	}

	public static void setXmlButtonClicked(boolean xmlButtonClicked) {
		SharedClass.xmlButtonClicked = xmlButtonClicked;
	}

}