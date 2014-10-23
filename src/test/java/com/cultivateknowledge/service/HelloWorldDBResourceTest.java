package com.cultivateknowledge.service;

import com.cultivateknowledge.model.Something;
import com.cultivateknowledge.service.db.HelloWorldDAO;
import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Test;
import org.skife.jdbi.v2.Handle;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class HelloWorldDBResourceTest {

    private HelloWorldDBResource resource;
    private HelloWorldDAO dao;
    private Handle h;

    @Before
    public void setUp() throws Exception {
        dao = mock(HelloWorldDAO.class);
        resource = new HelloWorldDBResource(dao);
    }

    @Test
    public void testAdd() throws Exception {
        Something belizeSomething = new Something(1, "Belize");
        when(dao.insert("Belize")).thenReturn(1);
        when(dao.findById(1)).thenReturn(belizeSomething);
        Something created = resource.add("Belize");
        assertEquals("Belize", created.name);
    }

    @Test
    public void testFind() throws Exception {
        Something belizeSomething = new Something(1, "Belize");
        when(dao.findById(1)).thenReturn(belizeSomething);
        Something created = resource.find(1);
        assertEquals("Belize", created.name);
    }

    @Test
    public void testAll() throws Exception {
        Something belizeSomething = new Something(1, "Belize");
        Something canadaSomething = new Something(2, "Canada");
        when(dao.all()).thenReturn(Lists.newArrayList(belizeSomething, canadaSomething));
        List<Something> created = resource.findAll();
        assertEquals(2, created.size());
    }
}