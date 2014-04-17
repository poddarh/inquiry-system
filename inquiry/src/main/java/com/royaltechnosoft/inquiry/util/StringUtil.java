package com.royaltechnosoft.inquiry.util;

public class StringUtil {
	// Generates and returns random string with the specified length
	public static String generateRandomString(int length) {
		StringBuffer buffer = new StringBuffer();
		// The character to use in the random string
		String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		int charactersLength = characters.length();
		
		for (int i = 0; i < length; i++) {
			double index = Math.random() * charactersLength;
			buffer.append(characters.charAt((int) index));
		}
		return buffer.toString();
	}
}
