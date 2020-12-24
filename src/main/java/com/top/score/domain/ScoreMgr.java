package com.top.score.domain;

public class ScoreMgr {
	
	public ScoreResult getScore(long id) {
		ScoreResult res = new ScoreResult(100);
		res.setStatus(BizResult.Status.SUCCESS);
		return res;
	}
}
