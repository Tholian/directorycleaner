package com.github.tholian.directorycleaner;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.sentry.Sentry;
import io.sentry.SentryClient;

@Ignore(value="Only for testing sentry connection (once).")
public class SentryJavaConnectionTest {
	private static final Logger logger = LoggerFactory.getLogger(SentryJavaConnectionTest.class);
	
	private SentryJavaConnection sentryJavaConnection;
	
	@BeforeClass
	public static final void setUpBeforeClass(){
		SentryClient init = Sentry.init();
		logger.debug("Sentry init: {}", init);
	}
	
	@Before
	public void setUp() {
		sentryJavaConnection = new SentryJavaConnection();
	}


	@Test(expected=UnsupportedOperationException.class)
	public void testUnsafeMethod() {
		sentryJavaConnection.unsafeMethod();
	}

	@Test
	public void testLogSimpleMessage() {
		sentryJavaConnection.logSimpleMessage();
	}

	@Test
	public void testLogException() {
		sentryJavaConnection.logException();
	}

}
