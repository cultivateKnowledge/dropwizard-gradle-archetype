package com.cultivateknowledge.service;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class IndexResourceTest {

    @Test
    public void testHandle() throws Exception {
        IndexResource resource = new IndexResource();
        IndexResource.IndexView view = resource.handle();
        assertEquals(view.getHelloWorldMsg(), "Hello World!");
        assertTrue(view.getWorlds().contains("Earth"));
    }
}