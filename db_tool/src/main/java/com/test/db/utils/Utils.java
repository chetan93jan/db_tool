package com.test.db.utils;

import javax.servlet.http.HttpSession;

public class Utils {
	
	public static <T> T getValue(HttpSession session, String sName, T defaultValue) {
		Object o = session.getAttribute(sName);
		if (o == null)
			return defaultValue;
		try {
			@SuppressWarnings("unchecked")
			T t = (T) o;
			return t;
		}
		catch (Exception e) {
			System.out.println("Utils::Exception: "+e.toString());
		}
		return defaultValue;
	}
	
	public static boolean isEmpty(String string) {
		return (string == null || string.length() == 0);
	}

}
