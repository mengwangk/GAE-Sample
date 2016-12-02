package com.crossover.action.message;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

import com.crossover.entity.SavingsAccount;
import com.google.gson.annotations.Expose;

public final class GetSavingsAccounts extends Response {

	@Expose
	@Getter
	@Setter
	private List<SavingsAccount> savingsAccounts;
	
}
