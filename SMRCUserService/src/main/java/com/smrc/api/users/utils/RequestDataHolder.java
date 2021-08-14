package com.smrc.api.users.utils;

public class RequestDataHolder {
	private static final ThreadLocal<String> securityLocalVariable = new ThreadLocal<String>();

	public static String getEncodingSecurityKey() {
		return securityLocalVariable.get();
	}

	public static void setEncodingSecurityKey(String encodingSecurityKey) {
		securityLocalVariable.set(encodingSecurityKey);
	}

}
