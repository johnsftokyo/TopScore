package com.top.score;


import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.top.score.dao.ScoreRepository;
import com.top.score.entity.ScoreEntity;


@ActiveProfiles("integration") //loads application-integration.properties
@Testcontainers
@SpringBootTest
@ContextConfiguration(initializers = {ScoreRepositoryTests.Initializer.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql({"/init_test.sql"})
public class ScoreRepositoryTests {
	
    @Container
    private static final MySQLContainer<?> MY_SQL_CONTAINER = new MySQLContainer<>("mysql:8.0.22");
    
    @Autowired
    private ScoreRepository scoreRepository;
    
    private String playerName = "integration_player";
    private int createEntityCount = 20;
    private int perPage = 5;
      
    @BeforeAll
    @DisplayName("Check DB container status")
    static void checkTestcontainerStatus() {
        assertTrue(MY_SQL_CONTAINER.isRunning());
    }
    
//    @Disabled
    @Test
    public void saveNFetch() throws InterruptedException {
    	
    	ZonedDateTime startTime = ZonedDateTime.ofInstant(Instant.now(), ZoneId.of("UTC"));
    	startTime = startTime.truncatedTo(ChronoUnit.SECONDS);
    	
        for(int i=0; i<=createEntityCount; i++) {
        	ScoreEntity scoreEntity = new ScoreEntity();
            scoreEntity.setPlayerName(playerName);
            scoreEntity.setScore(i);
            scoreRepository.save(scoreEntity);
            Thread.sleep(1000);
        }
        
        ZonedDateTime endTime = ZonedDateTime.ofInstant(Instant.now(), ZoneId.of("UTC"));
        
        List<String> names = new ArrayList<>();
        names.add(playerName);
       
        Pageable firstPage = PageRequest.of(0, perPage);
        Page<ScoreEntity> firstPageResult = scoreRepository.findByPlayersDateRange(firstPage, names, startTime, endTime);
        assertTrue(firstPageResult.getContent().get(0).getScore() == 0);
        
        Pageable lastPage = PageRequest.of((createEntityCount/perPage), perPage);
        Page<ScoreEntity> lastPageResult = scoreRepository.findByPlayersDateRange(lastPage, names, startTime, endTime);
        assertTrue(lastPageResult.getContent().get(lastPageResult.getContent().size() - 1).getScore() == createEntityCount);
    }
    
    @Test
    public void getAverageScore() {
    	
        ZonedDateTime startTime = ZonedDateTime.parse("2020-06-01T01:51:37+00:00[UTC]");
        ZonedDateTime endTime = ZonedDateTime.parse("2020-09-01T01:51:37+00:00[UTC]");
       
        Double avgScore = scoreRepository.findByPlayerAvgScore("test_jane", startTime, endTime);
        assertTrue(avgScore.doubleValue() == 18);               
    }
    
    @Test
    public void getMinScore() {
    	
        ZonedDateTime startTime = ZonedDateTime.parse("2019-06-01T01:51:37+00:00[UTC]");
        ZonedDateTime endTime = ZonedDateTime.parse("2019-09-01T01:51:37+00:00[UTC]");
       
        Pageable firstPage = PageRequest.of(0, perPage);
        
        Page<ScoreEntity> page = scoreRepository.findByPlayerMinScore(firstPage, "test_jim", startTime, endTime);
        ScoreEntity score = page.getContent().get(0);
        assertTrue(score.getScore() == 16);       
    }
    
    @Test
    public void getMaxScore() {
    	
        ZonedDateTime startTime = ZonedDateTime.parse("2020-10-01T01:51:37+00:00[UTC]");
        ZonedDateTime endTime = ZonedDateTime.parse("2021-01-01T01:51:37+00:00[UTC]");
       
        Pageable firstPage = PageRequest.of(0, perPage);
        
        Page<ScoreEntity> page = scoreRepository.findByPlayerMaxScore(firstPage, "test_tom", startTime, endTime);
        ScoreEntity score = page.getContent().get(0);
        assertTrue(score.getScore() == 30);       
    }    
    
    static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            TestPropertyValues.of(
            		"spring.datasource.url = " + MY_SQL_CONTAINER.getJdbcUrl(),
            		"spring.datasource.username = root",
//            		"spring.datasource.username = " + MY_SQL_CONTAINER.getUsername(),
            		"spring.datasource.password = " + MY_SQL_CONTAINER.getPassword()
            ).applyTo(configurableApplicationContext.getEnvironment());
        }
    }
}
