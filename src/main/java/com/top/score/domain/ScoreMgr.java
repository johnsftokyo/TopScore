package com.top.score.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.top.score.dao.ScoreRepository;
import com.top.score.entity.ScoreEntity;
import com.top.score.request.GetPlayerRequest;
import com.top.score.request.GetScoreRequest;
import com.top.score.request.RemoveScoreRequest;
import com.top.score.request.SaveScoreRequest;


@Service
public class ScoreMgr {

    @Autowired
    private ScoreRepository dao;
    
	public ScoreResult getScore(GetScoreRequest request) {
		//assume failed state first
		ScoreResult res = new ScoreResult();
		res.setStatus(BizResult.Status.FAIL);
		
		//start biz logic
		List<Score> scoreList = null;
		Page<ScoreEntity> page = null;
		Pageable pageable = PageRequest.of(request.getPage(), request.getPerPage());
		
		switch(request.getFilterBy()) {
	        case ID:
				Optional<ScoreEntity> scoreDo = dao.findById(request.getId());
				scoreList = scoreDo.stream().map(e -> convertEntityToBiz(e)).collect(Collectors.toList());
	        break;
	        
	        case PLAYER:
				page = dao.findByPlayersDateRange(pageable, request.getPlayerNames(), request.getAfter(), request.getBefore());
	        break;
	                
	        case DATE_RANGE:
				page = dao.findByDateRange(pageable, request.getAfter(), request.getBefore());
	        break;
	        
	        default:
				page = null;
	        break;
		}
		
		if(page != null) {
			scoreList = page.getContent().stream().map(e -> convertEntityToBiz(e)).collect(Collectors.toList());
		}
		
		if(scoreList != null) {
			res = new ScoreResult(scoreList);
			res.setStatus(BizResult.Status.SUCCESS);
			res.setPage(page);
		}

		return res;
	}
	
	public ScoreResult getPlayer(GetPlayerRequest request) {
		//assume failed state first
		ScoreResult res = new ScoreResult();
		res.setStatus(BizResult.Status.FAIL);
		
		//start biz logic
		List<Score> scoreList = null;
		Page<ScoreEntity> page = null;
		Pageable pageable = PageRequest.of(request.getPage(), request.getPerPage());
		Double avg = null;
		
		switch(request.getFilterBy()) {        
	        case ALL_PLAYER_SCORE:
	        	page = dao.findByPlayersDateRange(pageable, 
	        			new ArrayList<String>(Arrays.asList(request.getPlayerName())), request.getAfter(), request.getBefore());
	        break;
	                
	        case AVG_SCORE:
				avg = dao.findByPlayerAvgScore(request.getPlayerName(), request.getAfter(), request.getBefore());
	        break;
	        
	        case MAX_SCORE:
	        	page = dao.findByPlayerMaxScore(pageable, request.getPlayerName(), request.getAfter(), request.getBefore());
	        break;
	        
	        case MIN_SCORE:
	        	page = dao.findByPlayerMinScore(pageable, request.getPlayerName(), request.getAfter(), request.getBefore());
	        break;	        
	        
	        default:
				page = null;
	        break;
		}
		
		if(page != null) {
			scoreList = page.getContent().stream().map(e -> convertEntityToBiz(e)).collect(Collectors.toList());
		}
		
		if(scoreList != null) {
			res.setScoreList(scoreList);
			res.setStatus(BizResult.Status.SUCCESS);
			res.setPage(page);
		}
		else if(avg != null) {
			res.setAvg(avg);
			res.setStatus(BizResult.Status.SUCCESS);
		}
		
		return res;
	}
	
	public ScoreResult saveScore(SaveScoreRequest request) {
		ScoreEntity scoreDo = new ScoreEntity();
		scoreDo.setPlayerName(request.getPlayerName());
		scoreDo.setScore(request.getScore());
		ScoreEntity savedScore = dao.save(scoreDo);
		
		List<ScoreEntity> entityList = new ArrayList<>(1);
		
		entityList.add(savedScore);
		List<Score> scoreList = entityList.stream().map(e -> convertEntityToBiz(e)).collect(Collectors.toList());
		
		ScoreResult res = new ScoreResult(scoreList);
		res.setStatus(BizResult.Status.SUCCESS);

		return res;
	}
	
	public ScoreResult removeScore(RemoveScoreRequest request) {
		ScoreEntity scoreDo = new ScoreEntity();
		scoreDo.setId(request.getId());
		dao.delete(scoreDo);
			
		ScoreResult res = new ScoreResult();
		res.setStatus(BizResult.Status.SUCCESS);

		return res;
	}	
	
	public Score convertEntityToBiz(ScoreEntity e) {
		Score s = new Score(e.getId(), e.getCreatedDate(), e.getScore(), e.getPlayerName());
		return s;
	}
}
