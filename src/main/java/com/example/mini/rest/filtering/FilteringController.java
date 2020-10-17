package com.example.mini.rest.filtering;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class FilteringController {

	// DYNAMIC FILTERING
	@GetMapping(value = "/filtering")
	public MappingJacksonValue retrieveSomeBean() {
		SomeBean someBean = new SomeBean("value4", "value5", "value6");
		MappingJacksonValue mapping = name(Arrays.asList( someBean ));
		return mapping;
	}
	
	@GetMapping(value = "/filtering-List")
	public MappingJacksonValue retrieveEAllSomeBean() {
	List<SomeBean> list = Arrays.asList(new SomeBean("value1", "value2", "value3"), new SomeBean("value4", "value5", "value6"));
		MappingJacksonValue  mapping = name(list);
	return  mapping;
	
	}
	
	// DYNAMIC FILTERING CODE
	public MappingJacksonValue name(List<SomeBean> list) {
		
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1", "field2");
		SimpleFilterProvider addFilter = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
		MappingJacksonValue mapping = new MappingJacksonValue(list);
		mapping.setFilters(addFilter);
		
		return mapping;
		
	}
}
