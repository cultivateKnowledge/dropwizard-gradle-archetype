package com.cultivateknowledge;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class HelloWorldConfigurationTest {

    @Test
    public void testConfigs() throws Exception {
        HelloWorldConfiguration configs = new HelloWorldConfiguration();
        configs.template = "Hello, %s!";
        configs.defaultName = "Stranger";
        assertTrue(configs.getDefaultName().equals("Stranger"));
        assertTrue(configs.getTemplate().equals("Hello, %s!"));
    }
}