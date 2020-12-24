package com.top.score.convert;

import com.top.score.domain.BizResult;
import com.top.score.domain.ScoreResult;
import com.top.score.response.BaseResponse;
import com.top.score.response.ScoreResponse;

public class ScoreConverter extends BaseConverter {

	@Override
	public BaseResponse convertToResponse(BizResult bizRes) {
		ScoreResult scoreRes = (ScoreResult) bizRes;
		ScoreResponse scoreRsp = new ScoreResponse();
		
		scoreRsp.setScore(scoreRes.getScore());
		
		return scoreRsp;
	}

}
