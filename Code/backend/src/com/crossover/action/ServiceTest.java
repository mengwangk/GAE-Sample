package com.crossover.action;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.crossover.Constants;
import com.crossover.entity.Customer;

import lombok.extern.slf4j.Slf4j;


/**
 * Methods related to signing in and registering.
 */
@Path("/test")
@Slf4j
public class ServiceTest
{
	@Inject HttpServletRequest request;

	/**
	 * Tries to log a user in via a browserid assertion.
	 * Sets cookies and logs them into the Bracelet.
	 */
	@GET
	@Path("/id")
	@Produces(MediaType.APPLICATION_JSON)
	public String abc()  {
		Customer customer = (Customer)request.getSession().getAttribute(Constants.ATTRIBUTE_CUSTOMER);
		return customer.getId();
		
	}
}