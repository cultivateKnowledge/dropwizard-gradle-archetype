package com.cultivateknowledge.service;

import com.codahale.metrics.annotation.Timed;
import com.cultivateknowledge.model.Something;
import com.cultivateknowledge.service.db.HelloWorldDAO;
import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Arrays;
import java.util.List;

@Path("/db")
@Produces(MediaType.APPLICATION_JSON)
public class HelloWorldDBResource {
    private final HelloWorldDAO dao;

    public HelloWorldDBResource(DBI dbi) {
        this.dao = dbi.onDemand(HelloWorldDAO.class);

        try (Handle h = dbi.open()) {
            h.execute("create table something (id int primary key auto_increment, name varchar(100))");
            String[] names = { "Gigantic", "Bone Machine", "Hey", "Cactus" };
            Arrays.stream(names).forEach(name -> h.insert("insert into something (name) values (?)", name));
        }
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
    public List<Something> all(@PathParam("id") Integer id) {
        return dao.all();
    }
}