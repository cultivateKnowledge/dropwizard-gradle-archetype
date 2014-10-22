package com.cultivateknowledge.client;

import com.cultivateknowledge.model.Saying;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

public interface HelloWorldAPI {
    @GET
    @Path("/hello-world")
    Saying hi(@QueryParam("name") String name);

    @GET
    @Path("/hello-world")
    Saying hi();
}