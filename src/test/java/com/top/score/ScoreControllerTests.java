package com.top.score;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.top.score.request.SaveScoreRequest;


@ActiveProfiles("integration") //loads application-integration.properties
@Testcontainers
@SpringBootTest
@ContextConfiguration(
	initializers = {ScoreControllerTests.Initializer.class}
)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@AutoConfigureMockMvc
@Sql({"/init_test.sql"})
public class ScoreControllerTests {
	
    @Container
    private static final MySQLContainer<?> MY_SQL_CONTAINER = new MySQLContainer<>("mysql:8.0.22");
    
    @Autowired  
    private MockMvc mockMvc;
    
    private String testPlayerName = "integration_player";
    private int testPlayerScore = 10;
    
    private String testPlayer2Name = "test_bill";
    private int testPlayer2Score = 11;
    private String testPlayer2StartDate = "2020-05-03T01:51:37Z";
    
    @BeforeAll
    @DisplayName("Check DB container status")
    static void checkTestcontainerStatus() {
        assertTrue(MY_SQL_CONTAINER.isRunning());
    }
	
    @Test
	public void saveScore() throws Exception {
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/score/save")
				.content(createJsonBody(testPlayerScore, testPlayerName))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andReturn();

		Map<?,?> map = new ObjectMapper().readValue(result.getResponse().getContentAsString(), Map.class);
		List<?> scoreList = (List<?>)map.get("scores");
		int actualScore = (int)scoreList.get(0);

		assertTrue(actualScore == testPlayerScore);
		
		getPlayer(testPlayerName);
		getScore(testPlayerName + ","+ testPlayer2Name);
    }
    
	public void getPlayer(String testPlayerName) throws Exception {
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/player/get")
				.param("playerName", testPlayerName))
				.andExpect(status().isOk())
				.andReturn();
		
		Map<?,?> map = new ObjectMapper().readValue(result.getResponse().getContentAsString(), Map.class);
		List<?> scoreList = (List<?>)map.get("scoreList");
		Map<?,?> scoreMap = (Map<?, ?>)scoreList.get(0);
		String actualName = (String)scoreMap.get("playerName");
		int actualScore = (int)scoreMap.get("score");

		assertTrue(actualName.equalsIgnoreCase(testPlayerName));
		assertTrue(actualScore == testPlayerScore);
    }
    
	public void getScore(String players) throws Exception {
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/score/get")
				.param("playerNames", players)
				.param("after", testPlayer2StartDate))
				.andExpect(status().isOk())
				.andReturn();
		
		Map<?,?> map = new ObjectMapper().readValue(result.getResponse().getContentAsString(), Map.class);
		List<?> scoreList = (List<?>)map.get("scores");
		
		int testPlayer2ScoreActual = (int)scoreList.get(0);
		assertTrue(testPlayer2Score == testPlayer2ScoreActual);
		
		int testPlayerScoreActual = (int)scoreList.get(scoreList.size() - 1);
		assertTrue(testPlayerScoreActual == testPlayerScore);
    }   
	
	private String createJsonBody(int score, String player) throws JsonProcessingException {
		SaveScoreRequest req = new SaveScoreRequest();
		req.setPlayerName(player);
		req.setScore(score);
		
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(req);
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
