package com.cultivateknowledge.service.db;

import com.cultivateknowledge.model.Something;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import java.util.List;

@RegisterMapper(SomethingMapper.class)
public interface HelloWorldDAO {
    @SqlUpdate("insert into something (name) values (:name)")
    @GetGeneratedKeys
    int insert(@Bind("name") String name);

    @SqlQuery("select * from something where id = :id")
    Something findById(@Bind("id") int id);

    @SqlQuery("select * from something")
    List<Something> all();
}