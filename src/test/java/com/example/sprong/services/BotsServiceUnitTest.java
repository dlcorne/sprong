package com.example.sprong.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import java.util.Optional;
import com.example.sprong.domain.Bots;
import com.example.sprong.repos.BotsRepo;
import com.example.sprong.service.BotsService;

@SpringBootTest
@ActiveProfiles("test")
public class BotsServiceUnitTest {

	@Autowired
	private BotsService service;

	@MockBean
	private BotsRepo repo;

	@Test
	void testUpdate() {
		final int id = 1;
		final String name = "Paris Legionnaire";
		final String make = "Gladiator";
		final Integer newAge = 52;
		final int newCost = 345000;

		Bots expected = new Bots(id, name, make, newAge, newCost);

		Mockito.when(this.repo.findById(id)).thenReturn(Optional.of(new Bots(id, name, make, 46, 1000000)));
		Mockito.when(this.repo.save(new Bots(id, name, make, newAge, newCost)))
				.thenReturn(new Bots(id, name, make, newAge, newCost));

		Bots actual = this.service.updateBot(id, null, null, newAge, newCost);

		assertEquals(expected, actual);

	}

}