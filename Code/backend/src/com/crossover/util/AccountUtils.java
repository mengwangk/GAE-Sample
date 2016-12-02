package com.crossover.util;

import java.util.Random;
import java.util.UUID;

public final class AccountUtils {

	
	public static String generateAccountNo(){
		UUID uuid = UUID.randomUUID();
		return uuid.toString();
	}
	
	public static int generateBalance(){
	    Random randomGenerator = new Random();
	    return randomGenerator.nextInt(10000);
	}
}
