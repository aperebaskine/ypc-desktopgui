package com.pinguela.yourpc.desktop.util;

import java.beans.Beans;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public final class I18n {

	private static final String BUNDLE_NAME = "i18n.messages"; //$NON-NLS-1$
	private static ResourceBundle RESOURCE_BUNDLE = loadBundle();
	
	private I18n() {
	}
	
	private static ResourceBundle loadBundle() {
		return ResourceBundle.getBundle(BUNDLE_NAME);
	}

	public static String getString(String key) {
		try {
			ResourceBundle bundle = Beans.isDesignTime() ? loadBundle() : RESOURCE_BUNDLE;
			return bundle.getString(key);
		} catch (MissingResourceException e) {
			return String.format("!%s!", key);
		}
	}
	
	public static boolean switchLocale(String localeIdentifier) {
		String[] components = localeIdentifier.split("_");
		String language = components[0];
		String country = components[1];
		String variant = components.length > 2 ? components[2] : null;

		return switchLocale(variant == null ?
				new Locale(language, country) :
					new Locale(language, country, variant));
	}
	
	public static boolean switchLocale(Locale locale) {
		if (locale.equals(Locale.getDefault())) {
			return false;
		}
		Locale.setDefault(locale);
		RESOURCE_BUNDLE = loadBundle();
		
		return true;
	}
	
}
