package ru.balmukanov.retropoker.application.api;

import reactor.core.publisher.Mono;
import ru.balmukanov.retropoker.domain.Room;

public interface RoomService {
	Mono<Room> get(String uuid);
	Mono<Room> create(Room room);
	Mono<Room> update(Room room);
}
