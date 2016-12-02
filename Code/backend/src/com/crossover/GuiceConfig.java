package com.crossover;

import java.util.Map;

import javax.inject.Singleton;
import javax.servlet.ServletContextEvent;

import lombok.extern.slf4j.Slf4j;

import com.crossover.action.AccountService;
import com.crossover.action.AdminService;
import com.crossover.action.ServiceTest;
import com.crossover.auth.AuthFilter;
import com.crossover.util.ObjectMapperProvider;
import com.crossover.util.txn.Transact;
import com.crossover.util.txn.TransactInterceptor;
import com.google.appengine.tools.appstats.AppstatsFilter;
import com.google.appengine.tools.appstats.AppstatsServlet;
import com.google.common.collect.Maps;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.matcher.Matchers;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;
import com.googlecode.objectify.ObjectifyFilter;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;


/**
 * Creates our Guice module
 *
 */
@Slf4j
public class GuiceConfig extends GuiceServletContextListener
{
	/** */
	static class CrossOverServletModule extends ServletModule
	{
		/* (non-Javadoc)
		 * @see com.google.inject.servlet.ServletModule#configureServlets()
		 */
		@Override
		protected void configureServlets() {
			Map<String, String> appstatsParams = Maps.newHashMap();
			appstatsParams.put("logMessage", "Appstats: /admin/appstats/details?time={ID}");
			appstatsParams.put("calculateRpcCosts", "true");
			filter("/*").through(AppstatsFilter.class, appstatsParams);
			serve("/appstats/*").with(AppstatsServlet.class);

			filter("/*").through(ObjectifyFilter.class);
			filter("/*").through(AuthFilter.class);

			Map<String, String> params = Maps.newHashMap();
			params.put("com.sun.jersey.config.property.packages", "com.crossover.action");
			serve("/services/*").with(GuiceContainer.class, params);
		}
	}

	/** Public so it can be used by unit tests */
	public static class CrossOverModule extends AbstractModule
	{
		/* (non-Javadoc)
		 * @see com.google.inject.AbstractModule#configure()
		 */
		@Override
		protected void configure() {
			requestStaticInjection(OfyService.class);

			// Lets us use @Transact
			bindInterceptor(Matchers.any(), Matchers.annotatedWith(Transact.class), new TransactInterceptor());

			// Use jackson for jaxrs
			bind(ObjectMapperProvider.class);

			// External things that don't have Guice annotations
			bind(AppstatsFilter.class).in(Singleton.class);
			bind(AppstatsServlet.class).in(Singleton.class);
			bind(ObjectifyFilter.class).in(Singleton.class);

			bind(ServiceTest.class);
			bind(AccountService.class);
			bind(AdminService.class);
		}
	}

	/**
	 * Logs the time required to initialize Guice
	 */
	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		long time = System.currentTimeMillis();

		super.contextInitialized(servletContextEvent);

		long millis = System.currentTimeMillis() - time;
		log.info("Guice initialization took " + millis + " millis");
	}

	@Override
	protected Injector getInjector() {
		return Guice.createInjector(new CrossOverServletModule(), new CrossOverModule());
	}

}