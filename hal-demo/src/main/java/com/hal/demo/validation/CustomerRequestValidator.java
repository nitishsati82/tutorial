package com.hal.demo.validation;

import java.util.Objects;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.server.ResponseStatusException;

import com.hal.demo.dto.Customer;

import lombok.SneakyThrows;

@Configuration
public class CustomerRequestValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {

		return Customer.class.equals(clazz);
	}

	@Override
	@SneakyThrows
	public void validate(Object target, Errors errors) {
		Customer obj = (Customer) target;
		if (Objects.isNull(obj))
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "request body cannot be null or Empty");
		if (!Objects.nonNull(obj.getName()) || !StringUtils.hasLength(obj.getName()))
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "name cannot be null or Empty");
		if (obj.getName().trim().length()<2)
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "name should contain at least 2 characters");
		if (!Objects.nonNull(obj.getEmpCode()) || !StringUtils.hasLength(obj.getEmpCode()))
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "empcode cannot be null or Empty");
	}

}
