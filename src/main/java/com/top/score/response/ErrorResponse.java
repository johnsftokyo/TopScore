package com.top.score.response;

import java.io.Serializable;

public class ErrorResponse implements Serializable {
	
    private static final long serialVersionUID = 1L;
    private final String message;
    private final String[] params;
    
    public ErrorResponse(String message, String[] params) {
        this.message = message;
        this.params = params;
    }    
    
	public String getMessage() {
		return message;
	}
	
	public String[] getParams() {
		return params;
	}
}
