package com.top.score.response;

import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.top.score.domain.Score;

public class ScoreResponse extends BaseResponse {
	
	@JsonIgnore
	private List<Score> scoreList;
	private List<Long> scores;
	private int page;
	private long totalElements;
	
	public ScoreResponse() {}
	
	public ScoreResponse(List<Score> scoreList) {
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

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public long getTotalElements() {
		return totalElements;
	}

	public void setTotalElements(long totalElements) {
		this.totalElements = totalElements;
	}
	
	public List<Long> getScores() {
		return scores;
	}

	public void prepareResponse() {
		scores = scoreList.stream().map(s -> s.getScore()).collect(Collectors.toList());
	}
}
