package com.crossover.auth;

import static com.crossover.OfyService.ofy;

import java.io.IOException;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

import com.crossover.Constants;
import com.crossover.entity.Customer;
import com.crossover.entity.SavingsAccount;
import com.crossover.entity.Transaction;
import com.crossover.entity.Transaction.TransactionType;
import com.crossover.util.AccountUtils;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.inject.Singleton;

/**
 *
 */
@Singleton
@Slf4j
public class AuthFilter extends AbstractFilter {

	@Override
	public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
		// Only process this for requests, not forwards (which would trigger the filter again)
		final String uri = request.getRequestURI();
		UserService userService = UserServiceFactory.getUserService();
		if (userService.isUserLoggedIn()) {
			// Check if the customer is created
			if (request.getSession().getAttribute(Constants.ATTRIBUTE_CUSTOMER) == null) {
				Customer customer = createSession(userService.getCurrentUser());
				request.getSession().setAttribute(Constants.ATTRIBUTE_CUSTOMER, customer);
			}
		} else {
			if (request.getAttribute("javax.servlet.forward.request_uri") == null && !uri.contains("/index.jsp") && !uri.contains("/index.html")
					&& !uri.contains("ServiceLogin") && !uri.contains("/_ah/login") && !uri.contains("/_ah/logout")) {
				if (!userService.isUserLoggedIn()) {
					final String url = userService.createLoginURL(request.getRequestURI());
					log.info("User is not logged in. Redirect to login " + url);
					request.getSession().removeAttribute(Constants.ATTRIBUTE_CUSTOMER);
					response.sendRedirect(url);
					return;
				} 
			} 
		}
		if (!userService.isUserLoggedIn()) {
			request.getSession().removeAttribute(Constants.ATTRIBUTE_CUSTOMER);
		}
		chain.doFilter(request, response);
	}

	public Customer createSession(final User user) {
		Customer customer = ofy().load().type(Customer.class).id(user.getNickname()).now();
		if (customer != null) {
			customer.loggedIn();
			checkAdminPermission(customer, user);
			ofy().save().entity(customer);

			//getAllAccounts(customer);
		} else {
			// Create the customer for the 1st time
			customer = new Customer(user.getNickname(), user.getEmail());
			checkAdminPermission(customer, user);
			customer.loggedIn();
			ofy().save().entity(customer).now();

			// Generate dummy data
			generateTestAccount(customer);
			generateTestAccount(customer);
		}
		return customer;
	}

	private void generateTestAccount(final Customer customer) {
		// Create saving account
		final String accountNo = AccountUtils.generateAccountNo();
		final SavingsAccount savingAccount = new SavingsAccount(accountNo, 0, customer.getId());
		ofy().save().entity(savingAccount).now();

		// Generate 10 transaction for this account
		double balance = 0;
		for (int i = 0; i < 10; i++) {
			final int amount = AccountUtils.generateBalance();
			balance += amount;
			Transaction transaction = new Transaction(accountNo, amount, TransactionType.DEPOSIT);
			ofy().save().entity(transaction).now();
		}

		final Transaction transaction = new Transaction(accountNo, 100, TransactionType.WITHDRAWAL);
		balance -= 100;
		ofy().save().entity(transaction).now();

		// Save account balance
		savingAccount.setBalance(balance);
		ofy().save().entity(savingAccount).now();
	}

	private void getAllAccounts(final Customer customer) {
		List<SavingsAccount> accounts = ofy().load().type(SavingsAccount.class).filter("customerNo =", customer.getId()).list();
		for (SavingsAccount savingAccount : accounts) {
			log.info("Account no ---- " + savingAccount.getAccountNo());
			log.info("Balance ---- " + savingAccount.getBalance());

			// Get transaction
			List<Transaction> transactions = ofy().load().type(Transaction.class).filter("accountNo =", savingAccount.getAccountNo()).list();
			for (Transaction transaction : transactions) {
				log.info("Transaction id ----  " + transaction.getId());
				log.info("Transaction type ----  " + transaction.getTransType());
				log.info("Transaction amount ----  " + transaction.getAmount());
			}
		}

	}

	private void checkAdminPermission(Customer customer, User user) {
		if (Constants.ADMIN_ID.equalsIgnoreCase(user.getEmail())) {
			customer.isAdmin(true);
		} else {
			customer.isAdmin(false);
		}
	}
}
