package com.shyam.commonlib.util;

public abstract class CommonUtil {
	public static boolean isEmpty(String obj) {
		return obj == null || "".equals(obj.toString().trim());
	}
}
