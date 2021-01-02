package com.top.score.request;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.top.score.error.ScoreRequestException;

public class SaveScoreRequest extends BaseRequest {
	
	@Size(max = 20)
	private String playerName;
	
	@Digits(integer=19, fraction=0)
	@Positive
	private long score;

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public long getScore() {
		return score;
	}

	public void setScore(long score) {
		this.score = score;
	}

	@Override
	public void validateRequest() {
		List<String> paramList = new ArrayList<>();
		
		if(playerName == null)
			paramList.add("playerName");
		
		if(score <= 0)
			paramList.add("score");
			
		if(paramList.size() > 0)
			throw new ScoreRequestException("Invalid or Missing", paramList.toArray(new String[paramList.size()]));
	}
}
