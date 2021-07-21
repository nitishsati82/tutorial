package com.swagger.demo.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Customer implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5980386275838189880L;
	private Integer id;
	
	private String name;
	private String empCode;
	private String email;
}
