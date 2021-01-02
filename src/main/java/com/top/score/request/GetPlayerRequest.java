package com.top.score.request;

import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Size;

import com.top.score.constants.Constants;
import com.top.score.error.ScoreRequestException;

public class GetPlayerRequest extends BaseRequest {
	
	private static Set<FilterBy> PLAYER_FILTERS;
	private FilterBy filterBy = FilterBy.ALL_PLAYER_SCORE;
	
	static {
		PLAYER_FILTERS = new HashSet<>(4);
		PLAYER_FILTERS.add(FilterBy.ALL_PLAYER_SCORE);
		PLAYER_FILTERS.add(FilterBy.AVG_SCORE);
		PLAYER_FILTERS.add(FilterBy.MAX_SCORE);
		PLAYER_FILTERS.add(FilterBy.MIN_SCORE);
	}
	
	@Size(max = 20)
	private String playerName;
	
	/*
	 * Default search date range that should not be changed.
	 */
	private ZonedDateTime before = ZonedDateTime.now();
	private ZonedDateTime after = ZonedDateTime.now().minus(Constants.MAX_DATE_RANGE, Constants.TIME_UNIT_USED);
	
	@Digits(integer=9, fraction=0)
	private int page = Constants.DEFAULT_PAGE;
	
	@Digits(integer=9, fraction=0)
	private int perPage = Constants.PER_PAGE;
	
	public FilterBy getFilterBy() {
		return filterBy;
	}

	public void setFilterBy(FilterBy filterBy) {
		this.filterBy = filterBy;
	}
	
	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPerPage() {
		return perPage;
	}

	public void setPerPage(int perPage) {
		this.perPage = perPage;
	}
	
	public ZonedDateTime getBefore() {
		return before;
	}

	public ZonedDateTime getAfter() {
		return after;
	}

	@Override
	public void validateRequest() {
		/* For player history, we can only filter by: 
		 * MAX_SCORE, MIN_SCORE, AVG_SCORE, ALL_PLAYER_SCORE
		 */
		if(!PLAYER_FILTERS.contains(filterBy)) {
			throw new ScoreRequestException("Invalid param given",
					new String[]{"filterBy"});
		}
		
		if(playerName == null || playerName.isEmpty()) {
			throw new ScoreRequestException("Following param is required.",
					new String[]{"playerName"});
		}
	}
}
