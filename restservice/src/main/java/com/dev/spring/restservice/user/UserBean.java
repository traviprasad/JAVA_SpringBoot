package com.dev.spring.restservice.user;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

public class UserBean {

    private Integer id;

    //@Size(min = 2,max = 25,message = "name should have min 2 and max 25 charcters")
    @NotBlank(message = "name must not be blank") @NotNull(message = "name must not be null")
    private String name;

    @Size(min = 3,max = 50,message = "Location should have min 3 and max 50 charcters")
    private String location;

    @Past
    private Date dob;

    public UserBean(Integer id, String name, String location, Date dob) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.dob = dob;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public Date getDob() {
        return dob;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    @Override
    public String toString() {
        return "UserBean{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", dob=" + dob +
                '}';
    }
}
