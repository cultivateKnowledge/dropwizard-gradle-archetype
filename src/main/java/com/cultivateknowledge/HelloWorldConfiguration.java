package com.cultivateknowledge;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * User: Ryan Chute
 * Date: 10/17/14
 */
public class HelloWorldConfiguration extends Configuration  {

    public String getTemplate()    { return template; }
    public String getDefaultName() { return defaultName; }
    public DataSourceFactory getDataSourceFactory() { return database; }

    @JsonProperty
    @NotEmpty
    private String template;

    @JsonProperty
    @NotEmpty
    private
    String defaultName;

    @Valid
    @NotNull
    @JsonProperty
    private DataSourceFactory database = new DataSourceFactory();

}
