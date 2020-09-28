package com;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageService {
	
	@GetMapping("/capitalise/{str1}")
	public String capitalise(@PathVariable("str1") String str1) {
		return str1.toUpperCase();
	}
	
	@GetMapping("/small/{str1}")
	public String small(@PathVariable("str1") String str1) {
		return str1.toLowerCase();
	}
	
	@GetMapping("/concat/{str1}/{str2}")
	public String concat(@PathVariable("str1") String str1, @PathVariable("str2") String str2) {
		return str1.concat(str2);
	}
	
	@GetMapping("/lastChar/{str1}/{char1}")
	public Boolean lastChar(@PathVariable("str1") String str1, @PathVariable("char1") String char1) {
		return str1.endsWith(char1);
	}
	
	@GetMapping("/trim/{str1}")
	public String trim(@PathVariable("str1") String str1) {
		return str1.trim();
	}
}