package com.hal.demo.service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.hal.demo.dto.Customer;

@Component
public class CustomerService {
	private static List<Customer> customerList = new LinkedList<>();
	static {
		customerList.add(Customer.builder().id(1).name("Jax").empCode("J-1").build());
		customerList.add(Customer.builder().id(2).name("Mike").empCode("J-2").build());
		customerList.add(Customer.builder().id(3).name("Rojy").empCode("J-3").build());
	}

	public List<Customer> findAll() {
		return customerList;
	}

	public Customer findById(int id) {
		List<Customer> customer = customerList.stream().filter(obj -> obj.getId().equals(id))
				.collect(Collectors.toList());
		if (customer.isEmpty())
			return null;
		else
			return customer.get(0);

	}

	public Customer saveCustomer(Customer customer) {
		customer.setId(customerList.size() + 1);
		customerList.add(customer);
		return customer;
	}

	public boolean delete(int id) {

		return customerList.removeIf(obj -> obj.getId().equals(id));
	}
}
