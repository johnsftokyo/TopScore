package com.top.score.entity;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name="Score")
public class ScoreEntity {
	
	@Id
 	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "created_date", insertable=false)
	private Instant createdDate;
	
	private long score;
	private String playerName;
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public long getScore() {
		return score;
	}
	
	public void setScore(long score) {
		this.score = score;
	}
	
	public String getPlayerName() {
		return playerName;
	}
	
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public Instant getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Instant createdDate) {
		this.createdDate = createdDate;
	}
}
