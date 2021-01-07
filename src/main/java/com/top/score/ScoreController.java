package com.top.score;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.top.score.domain.BizResult;
import com.top.score.domain.ScoreMgr;
import com.top.score.domain.ScoreResult;
import com.top.score.error.ScoreRequestException;
import com.top.score.request.BaseRequest;
import com.top.score.request.GetPlayerRequest;
import com.top.score.request.GetScoreRequest;
import com.top.score.request.RemoveScoreRequest;
import com.top.score.request.SaveScoreRequest;
import com.top.score.response.ErrorResponse;
import com.top.score.response.PlayerResponse;
import com.top.score.response.ScoreResponse;

@RestController
public class ScoreController {

    @Autowired
    ScoreMgr scoreMgr;	
	
	@GetMapping(path="/score/get", produces="application/json")
	public ScoreResponse getScore(@Valid GetScoreRequest request) {
		request.validateRequest();
		ScoreResult bizRes = scoreMgr.getScore(request);
		
		ScoreResponse rsp = new ScoreResponse();
		if(bizRes.getStatus() == BizResult.Status.SUCCESS) {
			rsp.setScoreList(bizRes.getScoreList());
			rsp.prepareResponse();
			if(bizRes.getPage() != null) {
				rsp.setPage(bizRes.getPage().getNumber());
				rsp.setTotalElements(bizRes.getPage().getTotalElements());
			}
		}
		else {
			rsp.setMessage("Failed to get score");
		}
		
		return rsp;
	}
	
	@GetMapping(path="/player/get", produces="application/json")
	public PlayerResponse getPlayerHistory(@Valid GetPlayerRequest request) {
		request.validateRequest();
		ScoreResult bizRes = scoreMgr.getPlayer(request);
		
		PlayerResponse rsp = new PlayerResponse();
		if(bizRes.getStatus() == BizResult.Status.SUCCESS) {
			if(request.getFilterBy() == BaseRequest.FilterBy.AVG_SCORE) {
				rsp.setAvg(bizRes.getAvg());
			}
			else {
				rsp.setScoreList(bizRes.getScoreList());
				if(bizRes.getPage() != null) {
					rsp.setPage(bizRes.getPage().getNumber());
					rsp.setTotalElements(bizRes.getPage().getTotalElements());
				}
			}
		}
		else {
			rsp.setMessage("Failed to get score");
		}
		
		return rsp;
	}	
	
	@PostMapping(path="/score/save", consumes="application/json", produces="application/json")
	@ResponseBody
	public ScoreResponse saveScore(@Valid @RequestBody SaveScoreRequest request) {
		request.validateRequest();
		ScoreResult bizRes = scoreMgr.saveScore(request);
		
		ScoreResponse rsp = new ScoreResponse();
		if(bizRes.getStatus() == BizResult.Status.SUCCESS) {
			rsp.setScoreList(bizRes.getScoreList());
			rsp.prepareResponse();
		}
		else {
			rsp.setMessage("Failed to save score");
		}
		
		return rsp;
	}
	
	@PostMapping(path="/score/remove", consumes="application/json", produces="application/json")
	@ResponseBody
	public ScoreResponse removeScore(@Valid @RequestBody RemoveScoreRequest request) {
		request.validateRequest();
		ScoreResult bizRes = scoreMgr.removeScore(request);
		
		ScoreResponse rsp = new ScoreResponse();
		if(bizRes.getStatus() == BizResult.Status.FAIL) {
			rsp.setMessage("Failed to save score");
		}
		
		return rsp;
	}	
	
    @ExceptionHandler
    public Map<String, String> handleValidationError(MethodArgumentNotValidException e) {
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return errors;
    }
    
    @ExceptionHandler(BindException.class)
    public Map<String, String> handleRequestBindingError(BindException e) {
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return errors;
    }
    
    @ExceptionHandler(ScoreRequestException.class)
    @ResponseBody
    public ErrorResponse handleRequestValidationError(ScoreRequestException ex) {
        return ex.getErrorRsp();
    }    
}
