package com.cultivateknowledge;

import com.codahale.metrics.JmxReporter;
import com.cultivateknowledge.client.HelloWorldAPI;
import com.cultivateknowledge.client.HelloWorldConsumerResource;
import com.cultivateknowledge.service.HelloWorldDBResource;
import com.cultivateknowledge.service.HelloWorldResource;
import com.cultivateknowledge.service.IndexResource;
import com.cultivateknowledge.service.db.HelloWorldDAO;
import dagger.Module;
import dagger.ObjectGraph;
import dagger.Provides;
import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.jaxrs.JAXRSModule;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;
import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;

import javax.inject.Named;
import java.util.Arrays;

public class HelloWorldApplication extends Application<HelloWorldConfiguration> {

    public static void main(String[] args) throws Exception {
        new HelloWorldApplication().run(new String[]{"server", System.getProperty("dropwizard.config")});
    }

    @Override
    public void initialize(Bootstrap<HelloWorldConfiguration> bootstrap) {
        bootstrap.addBundle(new ViewBundle());
        bootstrap.addBundle(new AssetsBundle());
    }

    @Override
    public void run(HelloWorldConfiguration cfg, Environment env) throws ClassNotFoundException {
        JmxReporter.forRegistry(env.metrics()).build().start(); // Manually add JMX reporting (Dropwizard regression)

        // Consumer Resource - Calls Hello World as client
        Feign.Builder feignBuilder = Feign.builder()
                .contract(new JAXRSModule.JAXRSContract()) // we want JAX-RS annotations
                .encoder(new JacksonEncoder()) // we want Jackson because that's what Dropwizard uses already
                .decoder(new JacksonDecoder());
        HelloWorldAPI helloWorldAPI = feignBuilder.target(HelloWorldAPI.class, "http://localhost:8080");
        env.jersey().register(new HelloWorldConsumerResource(helloWorldAPI));

        // Database CRUD Resource
        DBI dbi = new DBIFactory().build(env, cfg.getDataSourceFactory(), "db");
        env.jersey().register(new HelloWorldDBResource(dbi.onDemand(HelloWorldDAO.class)));

        // Set-up test db with sample data for the sake of this demo
        try (Handle h = dbi.open()) {
            h.execute("create table something (id int primary key auto_increment, name varchar(100))");
            String[] names = { "Gigantic", "Bone Machine", "Hey", "Cactus" };
            Arrays.stream(names).forEach(name -> h.insert("insert into something (name) values (?)", name));
        }

        // Hello World Resource
        ObjectGraph objectGraph = ObjectGraph.create(new HelloWorldDaggerModule(cfg));
        HelloWorldResource helloWorldResource = objectGraph.get(HelloWorldResource.class);
        env.jersey().register(helloWorldResource);

        // Add index page
        env.jersey().register(new IndexResource());
    }

    @Module(injects = com.cultivateknowledge.service.HelloWorldResource.class)
    class HelloWorldDaggerModule {
        private final HelloWorldConfiguration cfg;

        public HelloWorldDaggerModule(HelloWorldConfiguration cfg) {
            this.cfg = cfg;
        }

        @Provides @Named("template") String provideTemplate() {
            return cfg.getTemplate();
        }

        @Provides @Named("defaultName") String provideDefaultName() {
            return cfg.getDefaultName();
        }
    }

}