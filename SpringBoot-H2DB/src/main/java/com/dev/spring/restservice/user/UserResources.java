package com.dev.spring.restservice.user;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class UserResources {

    @Autowired
    private UserDaoService userDaoService;

    //List of Users
    @GetMapping(path = "/users/all")
    public List<UserBean> retriveAllUsers(){
        return userDaoService.findAllUsers();
    }

    //retrive user details
    @GetMapping(path = "/users/{id}",  produces = APPLICATION_JSON_VALUE)
    public EntityModel<UserBean> retriveUserId(@PathVariable int id){
        UserBean user = userDaoService.findUser(id);
        if(user == null){
            throw new UsertNotFoundException("id "+id);
        }

        EntityModel<UserBean> model = EntityModel.of(user);
        WebMvcLinkBuilder linkToUser = linkTo(methodOn(this.getClass()).retriveAllUsers());
        model.add(linkToUser.withRel("all-users"));
        return model;
    }

    //add new users
    @PostMapping(path = "/users")
    public ResponseEntity createUser(@Valid @RequestBody UserBean userBean){
        UserBean uBean = userDaoService.saveUser(userBean);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(uBean.getId()).toUri();
       return ResponseEntity.created(location).body(uBean);
    }

    //delete user details
    @DeleteMapping(path = "/users/{id}")
    public void deleteUserId(@PathVariable int id){
        UserBean user = userDaoService.deleteUser(id);
        if(user == null){
            throw new UsertNotFoundException("id "+id);
        }

        //return user;
    }
}
