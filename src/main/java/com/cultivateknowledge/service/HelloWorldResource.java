package com.cultivateknowledge.service;

import com.codahale.metrics.annotation.Timed;
import com.google.common.base.Optional;
import com.cultivateknowledge.model.Saying;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicLong;

// The actual service
@Path("/hello-world")
@Produces(MediaType.APPLICATION_JSON)
public class HelloWorldResource {
    private final AtomicLong counter = new AtomicLong();
    @Inject @Named("template") String template;
    @Inject @Named("defaultName") String defaultName;

    @Timed // monitor timing of this service with Metrics
    @GET
    public Saying sayHello(@QueryParam("name") Optional<String> name) throws InterruptedException {
        final String value = String.format(template, name.or(defaultName));
        Thread.sleep(ThreadLocalRandom.current().nextInt(10, 500));
        return new Saying(counter.incrementAndGet(), value);
    }
}