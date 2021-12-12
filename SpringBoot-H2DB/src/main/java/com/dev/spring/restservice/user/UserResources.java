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
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class UserResources {

    @Autowired
    private UserRepositroy userRepositroy;

    @Autowired
    private PostRepositroy postRepositroy;
    //List of Users
    @GetMapping(path = "/jpa/users/all")
    public List<UserBean> retriveAllUsers(){
        return userRepositroy.findAll();
    }

    //retrive user details
    @GetMapping(path = "/jpa/users/{id}",  produces = APPLICATION_JSON_VALUE)
    public EntityModel<UserBean> retriveUserId(@PathVariable int id){
        Optional<UserBean> user = userRepositroy.findById(id);
        if(!user.isPresent()){
            throw new UsertNotFoundException("id "+id);
        }

        EntityModel<UserBean> model = EntityModel.of(user.get());
        WebMvcLinkBuilder linkToUser = linkTo(methodOn(this.getClass()).retriveAllUsers());
        model.add(linkToUser.withRel("all-users"));
        return model;
    }

    //add new users
    @PostMapping(path = "/jpa/users")
    public ResponseEntity createUser(@Valid @RequestBody UserBean userBean){
        UserBean uBean = userRepositroy.save(userBean);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(uBean.getId()).toUri();
       return ResponseEntity.created(location).body(uBean);
    }

    //delete user details
    @DeleteMapping(path = "/jpa/users/{id}")
    public void deleteUserId(@PathVariable int id){
       userRepositroy.deleteById(id);
        /*if(user == null){
            throw new UsertNotFoundException("id "+id);
        }*/

        //return user;
    }

    //retrive user post details
    @GetMapping(path = "/jpa/users/{id}/posts",  produces = APPLICATION_JSON_VALUE)
    public List<Post> retriveUserPosts(@PathVariable int id){
        Optional<UserBean> user = userRepositroy.findById(id);
        if(!user.isPresent()){
            throw new UsertNotFoundException("id "+id);
        }

        return user.get().getPosts();
    }

    @PostMapping(path = "/jpa/users/{id}/posts",  produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> postUserPosts(@PathVariable int id, @Valid @RequestBody Post post){
        Optional<UserBean> userOptional = userRepositroy.findById(id);
        if(!userOptional.isPresent()){
            throw new UsertNotFoundException("id "+id);
        }
        UserBean userBean = userOptional.get();

        post.setUserBean(userBean);
        postRepositroy.save(post);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(post.getId()).toUri();
        return ResponseEntity.created(location).body(post);

    }
}
