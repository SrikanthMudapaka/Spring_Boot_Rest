package com.example.mini.rest.helloworld;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.example.mini.rest.helloworld.Student;

@RestController
public class HelloController {

	@Autowired
	private MessageSource messageSource;

	@GetMapping("/hello")
	public String hello() {
		return "hello";
	}

	@GetMapping(value = "/hello/{name}")
	public Student helloname(@PathVariable String name) {
		return new Student(String.format("hello, Welcome %s", name));

	}

	// internalization
	@GetMapping("/hello-internalized")
	public String helloLocaleUser(/* @RequestHeader(name = "Accept-Language", required = false) Locale locale */) {
		return messageSource.getMessage("good.morning.message", null, LocaleContextHolder.getLocale()); 
		//LocaleContextHolder.getLocale() used to pick Accept-Header Locale at runtime
	}

}
