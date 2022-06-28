package ru.balmukanov.retropoker;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Flux;
import ru.balmukanov.retropoker.adapter.data.RoomDataRepository;
import ru.balmukanov.retropoker.adapter.rest.dto.CreateRoomDto;
import ru.balmukanov.retropoker.domain.Room;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;

import static org.hamcrest.CoreMatchers.is;

@SpringBootTest
@SpringJUnitConfig
@ActiveProfiles("test")
class RoomTest {
	@Autowired
	ApplicationContext applicationContext;

	@Autowired
	RoomDataRepository roomRepository;

	ObjectMapper objectMapper = new ObjectMapper();

	WebTestClient rest;

	@BeforeEach
	void initClient() {
		rest = WebTestClient.bindToApplicationContext(applicationContext).build();
	}

	@AfterEach
	void initTestDate() {
		roomRepository.deleteAll().block();
	}

	@Test
	void getRoom_happyPath() throws IOException {
		String json = new String(Objects.requireNonNull(this.getClass().getClassLoader().getResourceAsStream("rooms.json")).readAllBytes(),
			StandardCharsets.UTF_8);
		Flux.fromIterable(objectMapper.readValue(json, new TypeReference<List<Room>>() {}))
			.concatMap(roomRepository::save)
			.blockLast();

		rest.get()
			.uri("/room/b4772ab3-9fdb-4d7b-942f-aaf279a4d544")
			.exchange()
			.expectStatus().isOk()
			.expectBody().jsonPath("$.isProtected", is(false));
	}

	@Test
	void getRoom_notFound() {
		rest.get()
			.uri("/room/qwerty")
			.exchange()
			.expectStatus().isNotFound();
	}

	@Test
	void creteRoom_happyPath() {
		var request = new CreateRoomDto();
		request.setDescription("Test");
		request.setProtected(false);

		rest.post()
			.uri("/room")
			.body(BodyInserters.fromValue(request))
			.exchange()
			.expectStatus().isOk();
	}

	@Test
	void createProtectedRoomWithoutPassword_validationError() {
		var request = new CreateRoomDto();
		request.setDescription("Test");
		request.setProtected(true);

		rest.post()
			.uri("/room")
			.body(BodyInserters.fromValue(request))
			.exchange()
			.expectStatus().isBadRequest()
			.expectBody(String.class).isEqualTo("You need to fill in a password if you want to create a secure room");
	}

	@Test
	void createProtectedRoomWithPassword_happyPath() {
		var request = new CreateRoomDto();
		request.setDescription("Test");
		request.setProtected(true);
		request.setPassword("1234");

		rest.post()
			.uri("/room")
			.body(BodyInserters.fromValue(request))
			.exchange()
			.expectStatus().isOk();
	}
}
