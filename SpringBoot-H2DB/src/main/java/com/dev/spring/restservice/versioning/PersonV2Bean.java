package com.dev.spring.restservice.versioning;

public class PersonV2Bean {
    private Name name;

    public PersonV2Bean(Name name) {
        this.name = name;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }
}
