package com.top.score.error;

import com.top.score.response.ErrorResponse;

public class ScoreRequestException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	private final String message;
    private final String[] params;
    
    public ScoreRequestException(String message, String[] params) {
        super(message);
        this.message = message;
        this.params = params;
    }

	public String getMessage() {
		return message;
	}

	public String[] getParams() {
		return params;
	}
	
    public ErrorResponse getErrorRsp() {
        return new ErrorResponse(message, params);
    }
}
