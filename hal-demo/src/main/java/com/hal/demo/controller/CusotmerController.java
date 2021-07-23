package com.hal.demo.controller;

import java.net.URI;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.hal.demo.dto.Customer;
import com.hal.demo.generic.response.ResponseEntityBuilder;
import com.hal.demo.generic.response.RestApiResponse;
import com.hal.demo.service.CustomerService;
import com.hal.demo.validation.CustomerRequestValidator;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v0/")
@Slf4j
public class CusotmerController {

	@Autowired
	CustomerService service;

	@Autowired
	CustomerRequestValidator requestValidator;

	@GetMapping(path = "/customers")
	public ResponseEntity<RestApiResponse> getCustomers() {
		List<Customer> list = service.findAll();
		if (Objects.nonNull(list))
			return ResponseEntityBuilder.getBuilder(HttpStatus.OK).successResponse(HttpStatus.OK.getReasonPhrase(),
					list);
		else
			return ResponseEntityBuilder.getBuilder(HttpStatus.NOT_FOUND)
					.errorResponse(HttpStatus.NOT_FOUND.getReasonPhrase());
	}

	@GetMapping(path = "/customer/{id}")
	public ResponseEntity<RestApiResponse> getCustomers(@PathVariable int id) {
		Customer customer = service.findById(id);
		if (Objects.nonNull(customer)) {
			return ResponseEntityBuilder.getBuilder(HttpStatus.OK).successResponse(HttpStatus.OK.getReasonPhrase(),
					customer);
		} else {
			return ResponseEntityBuilder.getBuilder(HttpStatus.NOT_FOUND)
					.errorResponse(HttpStatus.NOT_FOUND.getReasonPhrase());
		}
	}

	@PostMapping(path = "/create")
	public ResponseEntity<RestApiResponse> create(@RequestBody Customer cus, BindingResult result) {
		try {
			requestValidator.validate(cus, result);
			Customer savedCustomer = service.saveCustomer(cus);
			if (Objects.nonNull(savedCustomer)) {
				URI location = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
						.buildAndExpand(savedCustomer.getId()).toUri();
				return ResponseEntity.created(location).build();
			}
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		} catch (Exception ex) {
			log.error("Exception ::"+ex);
			ResponseStatusException res = (ResponseStatusException) ex;
			return ResponseEntityBuilder.getBuilder(res.getStatus()).errorResponse(res.getStatus().value(),
					res.getReason());
		}
	}

	@DeleteMapping(path = "/customer/{id}")
	public ResponseEntity<RestApiResponse> deleteCusotmer(@PathVariable int id) {
		boolean deleted = service.delete(id);
		if (deleted)
			return ResponseEntityBuilder.getBuilder(HttpStatus.NO_CONTENT)
					.successResponse(HttpStatus.NO_CONTENT.getReasonPhrase());
		else
			return ResponseEntityBuilder.getBuilder(HttpStatus.NOT_FOUND)
					.errorResponse(HttpStatus.NOT_FOUND.getReasonPhrase());
	}
}
