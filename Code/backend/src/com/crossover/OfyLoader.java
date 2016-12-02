/*
 */

package com.crossover;

import static com.crossover.OfyService.ofy;

import com.crossover.entity.EmailLookup;
import com.crossover.entity.Customer;
import com.googlecode.objectify.impl.LoaderImpl;

/**
 * Extend the Loader command with our own logic
 *
 */
public class OfyLoader extends LoaderImpl<OfyLoader>
{
	/** */
	public OfyLoader(Ofy base) {
		super(base);
	}

	/**
	 * Gets the Person associated with the email, or null if there is no association.
	 */
	public Customer personByEmail(String email) {
		if (email == null || email.trim().length() == 0)
			return null;

		EmailLookup lookup = email(email);
		if (lookup == null)
			return null;
		else
			return lookup.getPerson();
	}

	/**
	 * Gets the EmailLookup, or null if the normalized email is not in the system.
	 */
	public EmailLookup email(String email) {
		return ofy().load().type(EmailLookup.class).id(EmailLookup.normalize(email)).now();
	}

}