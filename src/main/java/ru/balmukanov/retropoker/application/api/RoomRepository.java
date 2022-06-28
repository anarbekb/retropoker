package ru.balmukanov.retropoker.application.api;

import reactor.core.publisher.Mono;
import ru.balmukanov.retropoker.domain.Room;

public interface RoomRepository {
	Mono<Room> save(Room room);
	Mono<Room> findByUuid(String uuid);
}
