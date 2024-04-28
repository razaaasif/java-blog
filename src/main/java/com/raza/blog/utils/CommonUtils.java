package com.raza.blog.utils;

public final class CommonUtils {
	private CommonUtils() {
	}

	public static boolean hasText(String text) {
		return text != null && text.trim().length() > 0;
	}
}
