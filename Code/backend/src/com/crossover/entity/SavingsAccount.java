package com.crossover.entity;

import java.io.Serializable;

import lombok.NoArgsConstructor;

import com.googlecode.objectify.annotation.Cache;
import com.googlecode.objectify.annotation.Entity;


@Entity
@Cache
@NoArgsConstructor
public class SavingsAccount extends Account implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6862622597303819236L;

	
	public SavingsAccount(final String accountNo, final double balance, final String customerNo) {
		this.accountNo = accountNo;
		this.balance = balance;
		this.customerNo = customerNo;
	}
	
}
