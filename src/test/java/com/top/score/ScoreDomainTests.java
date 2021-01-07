package com.top.score;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.top.score.dao.ScoreRepository;
import com.top.score.domain.BizResult;
import com.top.score.domain.Score;
import com.top.score.domain.ScoreMgr;
import com.top.score.domain.ScoreResult;
import com.top.score.entity.ScoreEntity;
import com.top.score.request.GetScoreRequest;
import com.top.score.request.SaveScoreRequest;

@ExtendWith(MockitoExtension.class)
public class ScoreDomainTests {
	
    @Mock
    private ScoreRepository scoreRepository;
    
    @Mock 
    private Page<ScoreEntity> repoResult;
	
    @InjectMocks //inject mocked ScoreRepository into ScoreMgr
    private ScoreMgr scoreMgr = new ScoreMgr();
    
    @DisplayName("Testing ScoreMgr GetScore method")
    @Test
    void testGetScore() {
    	String testPlayer = "test_player";
    	long testScore = 10l;
    	
    	//setup client request
    	GetScoreRequest request = new GetScoreRequest();
    	request.setPage(0);
    	request.setPerPage(10);
    	List<String> names = new ArrayList<>(1);
    	names.add(testPlayer);
    	request.setPlayerNames(names);
        ZonedDateTime startTime = ZonedDateTime.parse("2019-12-23T01:51:37+00:00[UTC]");
        ZonedDateTime endTime = ZonedDateTime.parse("2019-12-26T01:51:37+00:00[UTC]");
    	request.setAfter(startTime);
    	request.setBefore(endTime);
    	request.validateRequest();
    	
    	Pageable pageable = PageRequest.of(request.getPage(), request.getPerPage());
    	 	
    	Mockito.when(scoreRepository.findByPlayersDateRange(pageable, names, request.getAfter(), request.getBefore())).thenReturn(repoResult);
    	
    	//setup result from repository
    	List<ScoreEntity> scoreList = new ArrayList<>(1);
    	ScoreEntity score = new ScoreEntity();
    	score.setId(1);
    	ZonedDateTime createdDate = ZonedDateTime.parse("2019-12-25T01:51:37+00:00[UTC]");
    	score.setCreatedDate(createdDate.toInstant());
    	score.setPlayerName(testPlayer);
    	score.setScore(testScore);
    	scoreList.add(score);

    	Mockito.when(repoResult.getContent()).thenReturn(scoreList);
    	
    	ScoreResult res = scoreMgr.getScore(request);
    	assertEquals(BizResult.Status.SUCCESS, res.getStatus());
    	Score actualScore = res.getScoreList().get(0);
    	assertTrue(actualScore.getPlayerName() == testPlayer);
    	assertTrue(actualScore.getScore() == testScore);
    }
    
    @DisplayName("Testing ScoreMgr SaveScore method")
    @Test
    void saveScore() {
    	String testPlayer = "test_player";
    	long testScore = 10l;
    	
    	//setup client request
    	SaveScoreRequest request = new SaveScoreRequest();
    	request.setPlayerName(testPlayer);
    	request.setScore(testScore);

        ZonedDateTime createdDate = ZonedDateTime.parse("2019-12-23T01:51:37+00:00[UTC]");

    	request.validateRequest();

		ScoreEntity scoreDo = new ScoreEntity();
		scoreDo.setPlayerName(request.getPlayerName());
		scoreDo.setScore(request.getScore());
    	
		//setup result from repository
		ScoreEntity actualScoreDo = new ScoreEntity();
		actualScoreDo.setId(1);
		actualScoreDo.setPlayerName(request.getPlayerName());
		actualScoreDo.setScore(request.getScore());
		actualScoreDo.setCreatedDate(createdDate.toInstant());
		
    	Mockito.when(scoreRepository.save(ArgumentMatchers.any(ScoreEntity.class))).thenReturn(actualScoreDo);
    	
    	ScoreResult res = scoreMgr.saveScore(request);
    	assertEquals(BizResult.Status.SUCCESS, res.getStatus());
    	Score actualScore = res.getScoreList().get(0);
    	assertTrue(actualScore.getPlayerName() == testPlayer);
    	assertTrue(actualScore.getScore() == testScore);
    }
}
