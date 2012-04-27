package demo;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class SingletonKeyController {

	transient protected ResourceBundle myGameKeys = ResourceBundle
			.getBundle("demo.GameKeysResourceBundle");

	private Map<String, Integer> myKeyCodes;

	private SingletonKeyController() {
		myKeyCodes = new HashMap<String, Integer>();
	}

	private static class SingletonKeyControlHolder {
		public static final SingletonKeyController instance = new SingletonKeyController();
	}

	public static SingletonKeyController getInstance() {
		return SingletonKeyControlHolder.instance;
	}

	public void convertResourceBundleToMap() {
		Enumeration<String> keys = myGameKeys.getKeys();
		while (keys.hasMoreElements()) {
			String key = keys.nextElement();
			myKeyCodes.put(key, Integer.parseInt(myGameKeys.getString(key)));
		}
	}
	
	public Integer getKeyCode(String keyName) {
		if (myKeyCodes.containsKey(keyName)) return myKeyCodes.get(keyName);
		else System.out.println("This key is not valid. Check that it is initialized properly in the resource bundle.");
		return null;
	}
	
	public void changeKeyCode(String keyName, Integer keyCode) {
		addKeyCode(keyName, keyCode);
	}
	
	public void addKeyCode(String keyName, Integer keyCode) {
		myKeyCodes.put(keyName, keyCode);
	}
}
