package com.dev.spring.restservice.versioning;

public class PersonV1Bean {
    private String name;

    public PersonV1Bean(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
