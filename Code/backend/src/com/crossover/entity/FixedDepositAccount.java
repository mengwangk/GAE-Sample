package com.crossover.entity;

import java.io.Serializable;

import lombok.Getter;

public class FixedDepositAccount extends Account implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2633331614652183821L;

	@Getter
	private double interest;

	@Getter
	private int months;

}
