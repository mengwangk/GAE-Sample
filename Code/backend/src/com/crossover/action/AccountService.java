package com.crossover.action;

import static com.crossover.OfyService.ofy;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import lombok.extern.slf4j.Slf4j;

import com.crossover.Constants;
import com.crossover.action.message.GetAccountTransactions;
import com.crossover.action.message.GetSavingsAccounts;
import com.crossover.action.message.ServiceCode;
import com.crossover.entity.Customer;
import com.crossover.entity.SavingsAccount;
import com.crossover.entity.Transaction;

/**
 * Methods related to signing in and registering.
 */
@Path("/accounts")
@Slf4j
public class AccountService {

	@GET
	@Path("/savings")
	@Produces(MediaType.APPLICATION_JSON)
	public GetSavingsAccounts getSavingAccounts() {
		GetSavingsAccounts getAccountRequest = new GetSavingsAccounts();
		try {
			Customer customer = (Customer) request.getSession().getAttribute(Constants.ATTRIBUTE_CUSTOMER);
			if (customer == null) {
				log.warn("User is not authorized to access this service");
				getAccountRequest.setServiceCode(ServiceCode.NOT_AUTHORIZED.getCode());
			} else {
				// Retrieve all accounts related to this customer
				List<SavingsAccount> accounts = ofy().load().type(SavingsAccount.class).filter("customerNo =", customer.getId()).list();
				getAccountRequest.setServiceCode(ServiceCode.SUCCESS.getCode());
				getAccountRequest.setSavingsAccounts(accounts);
				return getAccountRequest;
			}
		} catch (Exception ex) {
			getAccountRequest.setServiceCode(ServiceCode.UNKNOWN_FAILURE.getCode());
			log.error("Unknown error", ex.getCause());
		}
		return getAccountRequest;
	}

	@GET
	@Path("/savings/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public GetAccountTransactions getSavingAccountTransaction(@PathParam("id") String accountNo) {
		GetAccountTransactions getTransactionRequest = new GetAccountTransactions();
		try {
			Customer customer = (Customer) request.getSession().getAttribute(Constants.ATTRIBUTE_CUSTOMER);
			if (customer == null) {
				log.warn("User is not authorized to access this service");
				getTransactionRequest.setServiceCode(ServiceCode.NOT_AUTHORIZED.getCode());
			} else {
				
				// TODO - for security an additional checking should also be performed here
				// to make sure the account belongs to this customer!!!
				
				List<Transaction> transactions = ofy().load().type(Transaction.class).filter("accountNo =", accountNo).list();
				getTransactionRequest.setServiceCode(ServiceCode.SUCCESS.getCode());
				getTransactionRequest.setTransactions(transactions);
			}
		} catch (Exception ex) {
			getTransactionRequest.setServiceCode(ServiceCode.UNKNOWN_FAILURE.getCode());
			log.error("Unknown error", ex.getCause());
		}
		return getTransactionRequest;
	}
}