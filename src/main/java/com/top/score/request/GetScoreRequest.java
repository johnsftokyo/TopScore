package com.top.score.request;

import java.time.ZonedDateTime;
import java.util.List;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.top.score.constants.Constants;
import com.top.score.error.ScoreRequestException;

public class GetScoreRequest extends BaseRequest {
		
	private FilterBy filterBy = FilterBy.UNKNOWN;
	
	@Digits(integer=19, fraction=0)
	private long id = Long.MIN_VALUE;
	
	@Size(max = 20)
	private List<String> playerNames;
	
	/* Max search range is limited to current date minus by MAX_DATE_RANGE_DAYS. The reason is in production we
	 * only can keep a certain amount of data in the Prod DB for fast access. Any data pass the search range is
	 * usually stored in data warehouse and eventually purged.
	 */
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private ZonedDateTime before = ZonedDateTime.now();
	
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private ZonedDateTime after = ZonedDateTime.now().minus(Constants.MAX_DATE_RANGE, Constants.TIME_UNIT_USED);
	
	@Digits(integer=9, fraction=0)
	private int page = Constants.DEFAULT_PAGE;
	
	@Digits(integer=9, fraction=0)
	private int perPage = Constants.PER_PAGE;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public List<String> getPlayerNames() {
		return playerNames;
	}

	public void setPlayerNames(List<String> playerNames) {
		this.playerNames = playerNames;
	}

	public ZonedDateTime getBefore() {
		return before;
	}

	public void setBefore(ZonedDateTime before) {
		this.before = before;
	}

	public ZonedDateTime getAfter() {
		return after;
	}

	public void setAfter(ZonedDateTime after) {
		this.after = after;
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

	public FilterBy getFilterBy() {
		return filterBy;
	}

	@Override
	public void validateRequest() {	
		//If score ID is in the request ignore other request params.
		if(id != Long.MIN_VALUE)
			filterBy = FilterBy.ID;
		//filter by player and date range.
		else if(playerNames != null && !playerNames.isEmpty())
			filterBy = FilterBy.PLAYER;
		//filter by default page, elements per page and date range.
		else
			filterBy = FilterBy.DATE_RANGE;
		
		if(filterBy == FilterBy.UNKNOWN) {
			throw new ScoreRequestException("Invalid param combination given",
					new String[]{"id", "playerNames", "before", "after"});
		}
	}
}
