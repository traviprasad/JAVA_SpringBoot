package com.dev.spring.restservice.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {

    @GetMapping(path = "/v1/person")
    public PersonV1Bean personV1(){
        return new PersonV1Bean("Baby Venkata");
    }

    @GetMapping(path = "/v2/person")
    public PersonV2Bean personV2(){
        return new PersonV2Bean(new Name("Baby","Venkata","Lakshmi"));
    }

    @GetMapping(path = "/person", params = "version=1")
    public PersonV1Bean personV1Params(){
        return new PersonV1Bean("Baby Venkata");
    }

    @GetMapping(path = "/person",params = "version=2")
    public PersonV2Bean personV2Params(){
        return new PersonV2Bean(new Name("Baby","Venkata","Lakshmi"));
    }

    @GetMapping(path = "/person", headers = "x-api-version=1")
    public PersonV1Bean personV1Header(){
        return new PersonV1Bean("Baby Venkata");
    }

    @GetMapping(path = "/person", headers = "x-api-version=2")
    public PersonV2Bean personV2Header(){
        return new PersonV2Bean(new Name("Baby","Venkata","Lakshmi"));
    }

    @GetMapping(path = "/person", produces = "application/person.v1+json")
    public PersonV1Bean personV1Produces(){
        return new PersonV1Bean("Baby Venkata");
    }

    @GetMapping(path = "/person", produces = "application/person.v2+json")
    public PersonV2Bean personV2Produces(){
        return new PersonV2Bean(new Name("Baby","Venkata","Lakshmi"));
    }
}
