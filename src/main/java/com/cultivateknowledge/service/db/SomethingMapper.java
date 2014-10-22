package com.cultivateknowledge.service.db;

import com.cultivateknowledge.model.Something;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SomethingMapper implements ResultSetMapper<Something> {
    public Something map(int index, ResultSet r, StatementContext ctx) throws SQLException {
        return new Something(r.getInt("id"), r.getString("name"));
    }
}
