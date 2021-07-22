package com.swagger.demo.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/localized/")
public class LocalizedController {

	@Autowired
	MessageSource source;

	@GetMapping(path = "/messages")
	public String getLocalizedMessage(@RequestHeader(name = "Accept-Language", required = false) Locale locale) {

		return source.getMessage("message.hello", null, "Default message", locale);
	}
}
