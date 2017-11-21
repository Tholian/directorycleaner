package com.github.tholian.directorycleaner;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

@Ignore(value="Only for testing sentry connection (once).")
public class SentryLoggerConnectionTest {
	
	private SentryLoggerConnection sentryConnectionTest;
	
	@Before
	public void setUp() {
		sentryConnectionTest = new SentryLoggerConnection();
	}

	@Test
	public void testLogSimpleMessage() {
		
		sentryConnectionTest.logSimpleMessage();
	}

	@Test
	public void testLogWithBreadcrumbs() {
		sentryConnectionTest.logWithBreadcrumbs();
	}

	@Test
	public void testLogWithTag() {
		sentryConnectionTest.logWithTag();
	}

	@Test
	public void testLogWithExtras() {
		sentryConnectionTest.logWithExtras();
	}

	@Test
	public void testLogException() {
		sentryConnectionTest.logException();
	}

	@Test(expected=UnsupportedOperationException.class)
	public void testUnsafeMethod() {
		sentryConnectionTest.unsafeMethod();
	}

}
