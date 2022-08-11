package com.example.sprong.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.List;
import java.util.Optional;
import org.springframework.http.MediaType;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.sprong.domain.Bots;
import com.example.sprong.repos.BotsRepo;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Sql(scripts = {"classpath:bot-schema.sql", "classpath:bot-data.sql"}, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
public class BotControllerIntegrationTest {
	
	private BotsRepo repo;
	
	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private ObjectMapper objmap;
	
	@SuppressWarnings("deprecation")
	@Test
	void testCreate() throws Exception {
		Bots testBot = new Bots("Chicago Shield", "Defender", 42, 600000);
		RequestBuilder req = post("/createBot").content(this.objmap.writeValueAsString(testBot))
				.contentType(MediaType.APPLICATION_STREAM_JSON_VALUE);
		
		ResultMatcher checkStatus = status().isCreated();
		Bots testSavedBot = new Bots(1, "Chicago Shield", "Defender", 42, 600000);
		ResultMatcher checkBody = content().json(this.objmap.writeValueAsString(testSavedBot));
		
		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}
	
	@Test
	void testGetById() throws Exception {
		ResultMatcher checkBody = content()
				.json(this.objmap.writeValueAsString(new Bots("Chicago Shield", "Defender", 42, 600000)));

		this.mvc.perform(get("/get/1")).andExpect(status().isOk()).andExpect(checkBody);
	}
	
	@Test
	void testGet() throws Exception {
		List<Bots> bots = List.of(new Bots("Chicago Shield", "Defender", 42, 600000),
				new Bots("Chicago Shield", "Defender", 42, 600000));

		ResultMatcher checkBody = content().json(this.objmap.writeValueAsString(bots));
		this.mvc.perform(get("/getAll")).andExpect(status().isOk()).andExpect(checkBody);
	}
	
	@Test
	void testUpdate() {
		Bots testBot = new Bots("Chicago Shield", "Defender", 42, 600000);
		testBot.setName("Updated");
		Bots botUpdated = repo.save(testBot);
		Assertions.assertThat(botUpdated.getName()).isEqualTo("Updated");
	}
	
	@Test
	void testDelete() {
		Bots bot = repo.findById(1).get();
		repo.delete(bot);
		Bots bot1 = null;
		@SuppressWarnings("deprecation")
		Optional<Bots> optionalBot = Optional.ofNullable(repo.getById(1));
		if(optionalBot.isPresent()) {
			bot1 = optionalBot.get();
		}
		Assertions.assertThat(bot1).isNull();
	}
}
