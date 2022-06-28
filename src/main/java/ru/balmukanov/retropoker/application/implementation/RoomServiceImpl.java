package ru.balmukanov.retropoker.application.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import ru.balmukanov.retropoker.application.api.RoomRepository;
import ru.balmukanov.retropoker.application.api.RoomService;
import ru.balmukanov.retropoker.application.api.exception.RoomNotFoundException;
import ru.balmukanov.retropoker.domain.Room;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {
	private final RoomRepository roomRepository;

	@Override
	public Mono<Room> get(String uuid) {
		return roomRepository.findByUuid(uuid).switchIfEmpty(Mono.error(RoomNotFoundException::new));
	}

	@Override
	public Mono<Room> create(Room room) {
		return roomRepository.save(room);
	}

	@Override
	public Mono<Room> update(Room room) {
		return Mono.empty();
	}
}
