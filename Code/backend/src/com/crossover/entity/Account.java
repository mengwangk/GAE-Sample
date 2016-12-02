package com.crossover.entity;

import lombok.Getter;
import lombok.Setter;

import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;


public abstract class Account {

	@Id
	@Getter
	protected String accountNo;
	
	@Index
	@Getter
	protected String customerNo;
	
	@Getter
	@Setter
	protected double balance;
	
}
