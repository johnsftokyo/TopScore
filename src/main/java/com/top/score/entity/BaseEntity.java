package com.top.score.entity;

import java.time.Instant;

public class BaseEntity {
	private long id;
	private Instant createTime;
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public Instant getCreateTime() {
		return createTime;
	}
	
	public void setCreateTime(Instant createTime) {
		this.createTime = createTime;
	}
}
