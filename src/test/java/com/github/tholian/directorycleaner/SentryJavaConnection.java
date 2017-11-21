package com.github.tholian.directorycleaner;

import io.sentry.Sentry;
import io.sentry.event.Event;
import io.sentry.event.EventBuilder;
import io.sentry.event.interfaces.ExceptionInterface;

public final class SentryJavaConnection {

    void unsafeMethod() {
        throw new UnsupportedOperationException("You shouldn't call this!");
    }

    void logSimpleMessage() {
        // This sends an event to Sentry.
        EventBuilder eventBuilder = new EventBuilder()
                        .withMessage("This is a test")
                        .withLevel(Event.Level.INFO)
                        .withLogger(SentryJavaConnection.class.getName());

        // Note that the *unbuilt* EventBuilder instance is passed in so that
        // EventBuilderHelpers are run to add extra information to your event.
        Sentry.capture(eventBuilder);
    }

    void logException() {
        try {
            unsafeMethod();
        } catch (Exception e) {
            // This sends an exception event to Sentry.
            EventBuilder eventBuilder = new EventBuilder()
                            .withMessage("Exception caught")
                            .withLevel(Event.Level.ERROR)
                            .withLogger(SentryJavaConnection.class.getName())
                            .withSentryInterface(new ExceptionInterface(e));

            // Note that the *unbuilt* EventBuilder instance is passed in so that
            // EventBuilderHelpers are run to add extra information to your event.
            Sentry.capture(eventBuilder);
        }
    }}
