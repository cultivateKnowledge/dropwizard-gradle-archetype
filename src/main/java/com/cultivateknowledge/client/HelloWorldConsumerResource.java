package com.cultivateknowledge.client;

import com.codahale.metrics.annotation.Timed;
import com.cultivateknowledge.model.Saying;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/consumer")
@Produces(MediaType.TEXT_PLAIN)
public class HelloWorldConsumerResource {
    private final HelloWorldAPI hellowWorld;

    public HelloWorldConsumerResource(HelloWorldAPI helloWorldAPI) {
        this.hellowWorld = helloWorldAPI;
    }

    @Timed
    @GET
    public String consume() {
        Saying saying = hellowWorld.hi("consumer");
        return String.format("The service is saying: %s (id: %d)",  saying.getContent(), saying.getId());
    }
}
