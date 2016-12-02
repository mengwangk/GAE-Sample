/*
 */

package com.crossover;

import com.crossover.entity.EmailLookup;
import com.crossover.entity.Customer;
import com.crossover.entity.SavingsAccount;
import com.crossover.entity.Transaction;
import com.google.inject.Injector;
import com.googlecode.objectify.ObjectifyFactory;

import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Our version of ObjectifyFactory which integrates with Guice.  You could and convenience methods here too.
 *
 */
@Singleton
@Slf4j
public class OfyFactory extends ObjectifyFactory
{
	/** */
	private Injector injector;

	/** Register our entity types */
	@Inject
	public OfyFactory(Injector injector) {
		this.injector = injector;

		long time = System.currentTimeMillis();

		this.register(Customer.class);
		this.register(EmailLookup.class);
		this.register(SavingsAccount.class);
		this.register(Transaction.class);

		long millis = System.currentTimeMillis() - time;
		log.info("Registration took " + millis + " millis");
	}

	/** Use guice to make instances instead! */
	@Override
	public <T> T construct(Class<T> type) {
		return injector.getInstance(type);
	}

	@Override
	public Ofy begin() {
		return new Ofy(this);
	}
}