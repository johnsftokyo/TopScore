package com.top.score;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.top.score.convert.ScoreConverter;
import com.top.score.domain.BizResult;
import com.top.score.domain.ScoreMgr;
import com.top.score.domain.ScoreResult;
import com.top.score.response.BaseResponse;

@RestController
public class ScoreController {

	@GetMapping("/score/get")
	public BaseResponse greeting(@RequestParam(value = "scoreId") long scoreId) {
		ScoreMgr mgr = new ScoreMgr();
		ScoreResult bizRes = mgr.getScore(scoreId);
		
		if(bizRes.getStatus() == BizResult.Status.SUCCESS) {
			ScoreConverter cv = new ScoreConverter();
			return cv.convertToResponse(bizRes);
		}
		
		BaseResponse rsp = new BaseResponse();
		rsp.setMessage("Failed to get score");
		return rsp;
	}
}
