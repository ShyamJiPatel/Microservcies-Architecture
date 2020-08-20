package com.shyam.api.productservice.util;

import java.time.LocalDateTime;
import java.util.Random;

public class CustomValueGenerator {

	public static String generateProductCode(String prefix) {

		Random rand = new Random();
		String randomNo = String.valueOf(rand.nextInt(90000) + 10000);

		LocalDateTime dateTime = LocalDateTime.now();

		String timestamp = String.valueOf(dateTime.getYear()) + String.valueOf(dateTime.getMonthValue())
				+ String.valueOf(dateTime.getDayOfMonth()) + String.valueOf(dateTime.getHour())
				+ String.valueOf(dateTime.getMinute()) + String.valueOf(dateTime.getSecond());

		return prefix + randomNo + timestamp;
	}

}
