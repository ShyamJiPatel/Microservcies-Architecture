package com.shyam.api.productservice.entity;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Random;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class ProductCodeGenerator implements IdentifierGenerator {

	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {

		String prefix = "PRO";

		Random rand = new Random();
		String randomNo = String.valueOf(rand.nextInt(90000) + 10000);

		Calendar calendar = Calendar.getInstance();

		String timestamp = String.valueOf(calendar.get(Calendar.YEAR)) + String.valueOf(calendar.get(Calendar.MONTH))
				+ String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)) + String.valueOf(calendar.get(Calendar.HOUR))
				+ String.valueOf(calendar.get(Calendar.MINUTE)) + String.valueOf(calendar.get(Calendar.SECOND))
				+ String.valueOf(calendar.get(Calendar.MILLISECOND));

		return prefix + randomNo + timestamp;
	}

}
