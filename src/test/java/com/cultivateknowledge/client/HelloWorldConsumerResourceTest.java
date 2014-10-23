package com.cultivateknowledge.client;

import com.cultivateknowledge.model.Saying;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class HelloWorldConsumerResourceTest {

    private HelloWorldConsumerResource resource;
    private HelloWorldAPI apiClient;

    @Before
    public void setUp() throws Exception {
        apiClient = mock(HelloWorldAPI.class);
        resource = new HelloWorldConsumerResource(apiClient);
    }

    @Test
    public void testConsume() throws Exception {
        Saying saying = new Saying(1l, "Pluto");
        when(apiClient.hi("consumer")).thenReturn(saying);
        assertEquals("The service is saying: Pluto (id: 1)", resource.consume());
    }
}