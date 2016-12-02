package com.crossover.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Cache;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
@Cache
@NoArgsConstructor
@EqualsAndHashCode(of="id")
public class Customer implements Serializable 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8113011437930457253L;

	/** */
	public static Key<Customer> key(String id) {
		return Key.create(Customer.class, id);
	}
	
	/**
	 * Synthetic id
	 */
	@Id
	@Getter
	String id;
	
	/** Pretty, non-normalized version */
	@Getter
	String email;

	/** Date user first logged in */
	@Index
	@Getter
	Date created;

	/** Date user last logged in */
	@Index
	@Getter
	Date lastLogin;
	
	@Index
	boolean isAdminRole;
	
	/**
	 */
	public Customer(String id, String email) {
		this.id = id;
		this.email = email;
		this.created = new Date();
	}

	/** */
	public void loggedIn() {
		this.lastLogin = new Date();
	}
	
	public boolean isAdmin(){
		return this.isAdminRole;
	}
	
	public void isAdmin(boolean isAdmin) {
		this.isAdminRole = isAdmin;
	}

}