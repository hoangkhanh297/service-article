package com.khanh.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

public class City {
    @JsonProperty("ID")
    private int id;
    @JsonProperty("Name")
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
