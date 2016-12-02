/*
 */

package com.crossover.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Cache;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Load;

/**
 * Lookup entity which allows us to make a strongly consistent, unique mapping of email address to person id.
 * These lookup objects are immutable; delete them and recreate them as necessary.
 */
@Entity(name="Email")
@Cache
@NoArgsConstructor
public class EmailLookup
{
	/** Use this method to normalize email addresses for lookup */
	public static String normalize(String email) {
		return email.toLowerCase();
	}

	/**
	 * Normalized email
	 */
	@Id
	@Getter
	String normal;

	/** Pretty version */
	@Getter
	String email;

	/** */
	@Index
	@Load
	Ref<Customer> person;

	/** */
	public EmailLookup(String email, Customer person) {
		this.normal = normalize(email);
		this.email = email;
		this.person = Ref.create(person);
	}

	/** Hide the Ref from clients */
	public Customer getPerson() {
		return person.get();
	}
}