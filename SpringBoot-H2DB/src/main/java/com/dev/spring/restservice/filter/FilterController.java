package com.dev.spring.restservice.filter;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class FilterController {

    @GetMapping(path = "/user/filter")
    public StaticFilterBean staticFilter(){

        return new StaticFilterBean("value1","value2","value3","value4","value5");
    }

    @GetMapping(path = "/user/filter-list")
    public List<StaticFilterBean> staticListFilter(){

        return Arrays.asList(new StaticFilterBean("value1","value2","value3","value4","value5"),
                new StaticFilterBean("value11","value12","value13","value14","value15"),
        new StaticFilterBean("value15","value25","value35","value45","value55"));
    }
    @GetMapping(path = "/user/propfilter")
    public StaticPropFilterBean staticPropertyFilter(){

        return new StaticPropFilterBean("value1","value2","value3","value4","value5");
    }

    @GetMapping(path = "/user/dynamicfilter")
    public MappingJacksonValue dynamicFilter(){
        DynamicFilterBean dynamicFilterBean =  new DynamicFilterBean("value1","value2","value3","value4","value5");

        SimpleBeanPropertyFilter beanFilter = SimpleBeanPropertyFilter.filterOutAllExcept("field1", "field3", "field5");

        FilterProvider filterProvider = new SimpleFilterProvider().addFilter("DynamicFilter",beanFilter);
        MappingJacksonValue mappingVal = new MappingJacksonValue(dynamicFilterBean);
        mappingVal.setFilters(filterProvider);
        return mappingVal;
    }

    @GetMapping(path = "/user/dynamicfilter-list")
    public MappingJacksonValue dynamicListFilter(){
        List<DynamicFilterBean> dynamicListFilterBean =  Arrays.asList(new DynamicFilterBean("value1","value2","value3","value4","value5"),
                new DynamicFilterBean("value13","value23","value33","value43","value53"),
                new DynamicFilterBean("value16","value26","value36","value46","value56"),
                new DynamicFilterBean("value18","value28","value38","value48","value58"));

        SimpleBeanPropertyFilter beanFilter = SimpleBeanPropertyFilter.filterOutAllExcept("field2", "field4", "field5");

        FilterProvider filterProvider = new SimpleFilterProvider().addFilter("DynamicFilter",beanFilter);
        MappingJacksonValue mappingVal = new MappingJacksonValue(dynamicListFilterBean);
        mappingVal.setFilters(filterProvider);
        return mappingVal;
    }
}
