package com.cultivateknowledge.service.db;

import org.junit.Test;

import java.sql.ResultSet;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SomethingMapperTest {

    @Test
    public void testMap() throws Exception {
        ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.getString("name")).thenReturn("Bob");
        when(resultSet.getInt("id")).thenReturn(1);
        SomethingMapper mapper = new SomethingMapper();
        assertEquals("Bob", mapper.map(1, resultSet, null).name);
    }
}