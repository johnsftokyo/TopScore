package com.top.score.request;

public abstract class BaseRequest {

	public enum FilterBy {
		ID,
		PLAYER,
		DATE_RANGE,
		MAX_SCORE,
		MIN_SCORE,
		AVG_SCORE,
		ALL_PLAYER_SCORE,
		UNKNOWN;
	}
	
	public abstract void validateRequest();
}
