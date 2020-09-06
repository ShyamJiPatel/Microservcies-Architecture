package com.shyam.commonlib.util;

import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class CommonUtil {
	public static boolean isEmpty(String obj) {
		return obj == null || "".equals(obj.toString().trim());
	}

	public static <T> T getObjectFromLinkedHashMap(Class<T> outputType, Object input) {
		ObjectMapper mapper = new ObjectMapper();
		T output = mapper.convertValue(input, outputType);
		return output;
	}
}
