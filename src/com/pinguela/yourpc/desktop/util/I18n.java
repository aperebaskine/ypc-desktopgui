package com.pinguela.yourpc.desktop.util;

import java.util.ResourceBundle;

public class I18n {
	
	private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("i18n.Messages");
	
	public static String getString(String key) {
		return BUNDLE.getString(key);
	}
	
}
