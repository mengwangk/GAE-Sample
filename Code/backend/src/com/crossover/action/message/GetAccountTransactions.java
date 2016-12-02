package com.crossover.action.message;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

import com.crossover.entity.Transaction;
import com.google.gson.annotations.Expose;

public class GetAccountTransactions extends Response {

	@Expose
	@Getter
	@Setter
	private List<Transaction> transactions;
}
