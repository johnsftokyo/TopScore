package com.top.score.domain;

public class BizResult {
	
	public enum Status {
		SUCCESS,
		FAIL,
		UNKNOWN;
	}
	
	private Status status = Status.UNKNOWN;

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
}
