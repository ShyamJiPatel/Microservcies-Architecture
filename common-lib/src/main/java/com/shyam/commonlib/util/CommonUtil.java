package com.shyam.commonlib.util;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public abstract class CommonUtil {
	public static boolean isEmpty(String obj) {
		return obj == null || "".equals(obj.toString().trim());
	}

	public static <T> T getObjectFromLinkedHashMap(Class<T> outputType, Object input) {
		ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
				false);
		objectMapper.registerModule(new JavaTimeModule());
		T output = objectMapper.convertValue(input, outputType);
		return output;
	}
}
