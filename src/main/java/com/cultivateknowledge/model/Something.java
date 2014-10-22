package com.cultivateknowledge.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Something {
    @JsonProperty
    public final int id;
    @JsonProperty
    public final String name;

    public Something(int id, String name) {
        this.id = id;
        this.name = name;
    }
}