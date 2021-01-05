package com.top.score.dao;

import java.time.ZonedDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.top.score.entity.ScoreEntity;

@Repository
public interface ScoreRepository extends PagingAndSortingRepository<ScoreEntity, Long> {
		
    @Query(
    	value =	"SELECT * FROM score WHERE created_date >= :afterDate AND created_date <= :beforeDate",
    	countQuery = "SELECT count(*) FROM score WHERE created_date >= :afterDate AND created_date <= :beforeDate",
    	nativeQuery = true
    )
	Page<ScoreEntity> findByDateRange(Pageable pageable, @Param("afterDate") ZonedDateTime afterDate,  
			@Param("beforeDate") ZonedDateTime beforeDate);
    
    @Query(
        	value =	"SELECT * FROM score WHERE player_name IN (:playerNames) AND created_date >= :afterDate AND created_date <= :beforeDate",
        	countQuery = "SELECT count(*) FROM score WHERE player_name IN (:playerNames) AND created_date >= :afterDate AND created_date <= :beforeDate",
        	nativeQuery = true
    )
    Page<ScoreEntity> findByPlayersDateRange(Pageable pageable, @Param("playerNames") List<String> playerNames, 
    		@Param("afterDate") ZonedDateTime afterDate,  @Param("beforeDate") ZonedDateTime beforeDate);
    
    @Query(
        	value =	"SELECT AVG(score) FROM score WHERE player_name = :playerName AND created_date >= :afterDate AND created_date <= :beforeDate",
        	nativeQuery = true
    )
    Double findByPlayerAvgScore(@Param("playerName") String playerName, 
    		@Param("afterDate") ZonedDateTime afterDate,  @Param("beforeDate") ZonedDateTime beforeDate);
    
    @Query(
        	value =	"SELECT * FROM score WHERE player_name = :playerName AND created_date >= :afterDate AND created_date <= :beforeDate AND score = (SELECT MAX(score) FROM score WHERE player_name = :playerName AND created_date >= :afterDate AND created_date <= :beforeDate)",
        	nativeQuery = true
    )
    Page<ScoreEntity> findByPlayerMaxScore(Pageable pageable, @Param("playerName") String playerName, 
    		@Param("afterDate") ZonedDateTime afterDate,  @Param("beforeDate") ZonedDateTime beforeDate);
    
    @Query(
    		value =	"SELECT * FROM score WHERE player_name = :playerName AND created_date >= :afterDate AND created_date <= :beforeDate AND score = (SELECT MIN(score) FROM score WHERE player_name = :playerName AND created_date >= :afterDate AND created_date <= :beforeDate)",
        	nativeQuery = true
    )
    Page<ScoreEntity> findByPlayerMinScore(Pageable pageable, @Param("playerName") String playerName, 
    		@Param("afterDate") ZonedDateTime afterDate,  @Param("beforeDate") ZonedDateTime beforeDate);
}
