package com.cultivateknowledge.service;

import com.codahale.metrics.annotation.Timed;
import com.cultivateknowledge.model.Something;
import com.cultivateknowledge.service.db.HelloWorldDAO;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/db")
@Produces(MediaType.APPLICATION_JSON)
public class HelloWorldDBResource {
    private final HelloWorldDAO dao;

    public HelloWorldDBResource(HelloWorldDAO dao) {
        this.dao = dao;
    }

    @Timed
    @POST @Path("/add")
    public Something add(String name) {
        return find(dao.insert(name));
    }

    @Timed
    @GET @Path("/item/{id}")
    public Something find(@PathParam("id") Integer id) {
        return dao.findById(id);
    }

    @Timed
    @GET @Path("/all")
    public List<Something> findAll() {
        return dao.all();
    }
}