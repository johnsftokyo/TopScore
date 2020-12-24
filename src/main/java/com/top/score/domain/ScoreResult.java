package com.top.score.domain;

public class ScoreResult extends BizResult {
	private long score;
	
	public ScoreResult(long score) {
		this.score = score;
	}

	public long getScore() {
		return score;
	}

	public void setScore(long score) {
		this.score = score;
	}
}
