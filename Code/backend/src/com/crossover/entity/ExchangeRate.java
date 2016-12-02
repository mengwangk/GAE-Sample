package com.crossover.entity;

import java.io.Serializable;

import lombok.Getter;

public class ExchangeRate implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Getter
	private String countryISO;
	
	@Getter
	private double rate;

}
