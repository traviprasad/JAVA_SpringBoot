package com.dev.spring.restservice.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepositroy extends JpaRepository<Post, Integer> {



}
