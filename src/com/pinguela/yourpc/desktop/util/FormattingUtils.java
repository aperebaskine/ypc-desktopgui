package com.pinguela.yourpc.desktop.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.pinguela.yourpc.model.AbstractPerson;

public class FormattingUtils {

	private static final char WHITESPACE = ' ';
	
	public static final DateFormat DATE_FORMAT;
	public static final DateFormat DATETIME_FORMAT;
	
	static {
		Locale locale = Locale.getDefault();
		
		DATE_FORMAT = SimpleDateFormat.getDateInstance(SimpleDateFormat.SHORT, locale);
		DATETIME_FORMAT = SimpleDateFormat.getDateTimeInstance(SimpleDateFormat.SHORT, SimpleDateFormat.MEDIUM, locale);
	}

	public static String formatDate(Date date) {
		return DATE_FORMAT.format(date);
	}

	public static String formatDateTime(Date date) {
		return DATETIME_FORMAT.format(date);
	}

	public static String formatFullName(AbstractPerson person) {
		return String.join(Character.toString(WHITESPACE), person.getFirstName(), formatLastNames(person));
	}

	public static String formatLastNames(AbstractPerson person) {
		return person.getLastName2() == null ? person.getLastName1() :
					String.join(Character.toString(WHITESPACE), person.getLastName1(), person.getLastName2());
	}

}
