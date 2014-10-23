package com.cultivateknowledge.service;

import com.google.common.base.Optional;
import dagger.Module;
import dagger.ObjectGraph;
import dagger.Provides;
import org.junit.Before;
import org.junit.Test;

import javax.inject.Named;

import static org.junit.Assert.assertEquals;

public class HelloWorldResourceTest {

    HelloWorldResource resource;

    @Before
    public void setup() {
        ObjectGraph objectGraph = ObjectGraph.create(new HelloWorldDaggerModuleTest());
        resource = objectGraph.get(HelloWorldResource.class);
    }

    @Test
    public void testSayHello() throws Exception {
        Optional<String> name = Optional.of("Bob");
        assertEquals("Hello, Bob!", resource.sayHello(name).getContent());
    }

    @Test
    public void testSayHelloDefault() throws Exception {
        Optional<String> name = Optional.absent();
        assertEquals("Hello, Stranger!", resource.sayHello(name).getContent());
    }

    @Module(injects = com.cultivateknowledge.service.HelloWorldResource.class)
    class HelloWorldDaggerModuleTest {

        @Provides
        @Named("template") String provideTemplate() {
            return "Hello, %s!";
        }

        @Provides @Named("defaultName") String provideDefaultName() {
            return "Stranger";
        }
    }
}