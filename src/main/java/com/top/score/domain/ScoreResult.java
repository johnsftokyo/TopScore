package com.top.score.domain;

import java.util.List;

import org.springframework.data.domain.Page;

import com.top.score.entity.ScoreEntity;

public class ScoreResult extends BizResult {

	private List<Score> scoreList;
	private Page<ScoreEntity> page;
	private Double avg = null;
	
	public ScoreResult() {}	
	
	public ScoreResult(List<Score> scoreList) {
		this.scoreList = scoreList;
	}

	public List<Score> getScoreList() {
		return scoreList;
	}

	public void setScoreList(List<Score> scoreList) {
		this.scoreList = scoreList;
	}
	
	public void addScore(Score score) {
		scoreList.add(score);
	}

	public Page<ScoreEntity> getPage() {
		return page;
	}

	public void setPage(Page<ScoreEntity> page) {
		this.page = page;
	}

	public Double getAvg() {
		return avg;
	}

	public void setAvg(Double avg) {
		this.avg = avg;
	}
}
