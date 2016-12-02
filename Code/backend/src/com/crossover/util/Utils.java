package com.crossover.util;

import java.util.Calendar;
import java.util.Date;

public final class Utils {

	public static Date currentDate() {
		Calendar cal = Calendar.getInstance();
		return cal.getTime();
	}
}
