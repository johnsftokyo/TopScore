package com.top.score.request;

public class ScoreRequest extends BaseRequest {
	private long playerId;
	private long scoreId;
	
	public long getPlayerId() {
		return playerId;
	}
	
	public void setPlayerId(long playerId) {
		this.playerId = playerId;
	}
	
	public long getScoreId() {
		return scoreId;
	}
	
	public void setScoreId(long scoreId) {
		this.scoreId = scoreId;
	}
}
