package com.crossover.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import com.crossover.util.Utils;
import com.googlecode.objectify.annotation.Cache;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
@Cache
@NoArgsConstructor
@EqualsAndHashCode(of="id")
public class Transaction implements Serializable {

	public enum TransactionType {
		DEPOSIT(1),
		WITHDRAWAL(2);
		
		private int code;

		TransactionType(int code) {
			this.code = code;
		}

		public int getCode() {
			return this.code;
		}

		public static TransactionType get(final int code) {
			if (DEPOSIT.getCode() == code) {
				return DEPOSIT;
			} else if (WITHDRAWAL.getCode() == code) {
				return WITHDRAWAL;
			} else {
				return DEPOSIT;
			}
		}
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8399499861250434525L;
	
	@Id
	@Getter
	private Long id;	// Autogenerated
	
	@Index
	@Getter
	private String accountNo;
	
	@Getter
	private Date transDate;
	
	@Getter
	private double amount;
	
	@Getter
	private int transType;
	
	public Transaction(final String accountNo, final double amount, final TransactionType transType){
		this.accountNo = accountNo;
		this.amount = amount;
		this.transType = transType.getCode();
		this.transDate = Utils.currentDate();
	}
	

}