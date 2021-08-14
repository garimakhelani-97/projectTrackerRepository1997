package com.smrc.gpor.model;

public class PhasesResponseModel<T> {
	private String status;
	private T response;
	public String getStatus() {
		return status;
	}
	public void setStatus(final String status) {
		this.status = status;
	}
	public T getResponse() {
		return response;
	}
	public void setResponse(final T response) {
		this.response = response;
	}

}
