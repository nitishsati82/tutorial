package com.swagger.demo.dto;

import java.io.Serializable;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class Customer implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5980386275838189880L;
	private String id;
	private String name;
}
