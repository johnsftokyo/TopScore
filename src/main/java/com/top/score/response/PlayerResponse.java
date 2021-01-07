package com.top.score.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.top.score.domain.Score;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PlayerResponse extends BaseResponse {
	
	private List<Score> scoreList;
	private int page;
	private long totalElements;
	private Double avg;
	
	public PlayerResponse() {}
	
	public PlayerResponse(List<Score> scoreList) {
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

	public Double getAvg() {
		return avg;
	}

	public void setAvg(Double avg) {
		this.avg = avg;
	}
}
