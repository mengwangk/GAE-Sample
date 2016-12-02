package com.crossover.action.message;

public enum ServiceCode {
	
	UNKNOWN_FAILURE(-1),
	SUCCESS(0),
	NOT_AUTHORIZED(1);

	private int code;

	ServiceCode(int code) {
		this.code = code;
	}

	public int getCode() {
		return this.code;
	}

	public static ServiceCode get(final int code) {
		if (SUCCESS.getCode() == code) {
			return SUCCESS;
		} else if (NOT_AUTHORIZED.getCode() == code) {
			return NOT_AUTHORIZED;
		} else {
			return UNKNOWN_FAILURE;
		}
	}
}