package com.javapoint1.controller;


import org.springframework.web.bind.annotation.*;

@RestController
public class StudentController {
	
	@RequestMapping("/name")
	public String getName() {
		return "Deepak";
	}
	
	@RequestMapping("/deepak")
	public String getTemplate() {
		return "rakesh";
	}
}
