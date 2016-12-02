package com.crossover.action.message;

import lombok.Getter;
import lombok.Setter;

import com.google.gson.annotations.Expose;

public abstract class Response {

	
	@Getter
	@Setter
	@Expose
	private int serviceCode = ServiceCode.NOT_AUTHORIZED.getCode();
	
}
