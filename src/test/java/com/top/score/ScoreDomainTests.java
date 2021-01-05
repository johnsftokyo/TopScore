package com.top.score;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.top.score.dao.ScoreRepository;
import com.top.score.domain.BizResult;
import com.top.score.domain.ScoreMgr;
import com.top.score.domain.ScoreResult;
import com.top.score.request.BaseRequest.FilterBy;
import com.top.score.request.GetScoreRequest;

@SpringBootTest
public class ScoreDomainTests {
	
    @Mock
    private ScoreRepository scoreRepository;
	
    //inject mocked ScoreRepository into ScoreMgr
    @InjectMocks 
    private ScoreMgr scoreMgr = new ScoreMgr();
    
//    @DisplayName("Testing ScoreMgr GetScore method")
//    @Test
//    void testGetScore(@Mock GetScoreRequest request) {
//    	Mockito.when(request.getFilterBy()).thenReturn(FilterBy.ID);
//    	ScoreResult res = scoreMgr.getScore(request);
//    	assertEquals(BizResult.Status.SUCCESS, res.getStatus());
//    }
}
