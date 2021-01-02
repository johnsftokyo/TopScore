package com.top.score.domain;

import java.time.Instant;

public class Score {
	
	private long id;
	private Instant createTime;
	private long score;
	private String playerName;
	
	public Score(long id, Instant createTime, long score, String playerName) {
		this.id = id;
		this.createTime = createTime;
		this.score = score;
		this.playerName = playerName;
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public Instant getCreateTime() {
		return createTime;
	}
	
	public void setCreateTime(Instant createTime) {
		this.createTime = createTime;
	}
	
	public long getScore() {
		return score;
	}
	
	public void setScore(long score) {
		this.score = score;
	}
	
	public String getPlayerName() {
		return playerName;
	}
	
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
}
