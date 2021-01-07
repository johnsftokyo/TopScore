package com.top.score.request;

import javax.validation.constraints.Digits;

import com.top.score.error.ScoreRequestException;

public class RemoveScoreRequest extends BaseRequest {
	
	@Digits(integer=19, fraction=0)
	private long id = Long.MIN_VALUE;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Override
	public void validateRequest() {
		if(id < 0) {
			throw new ScoreRequestException("Invalid request param",
					new String[]{"id"});
		}
	}
}
