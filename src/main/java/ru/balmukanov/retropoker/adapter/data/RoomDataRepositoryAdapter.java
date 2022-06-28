package ru.balmukanov.retropoker.adapter.data;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import ru.balmukanov.retropoker.application.api.RoomRepository;
import ru.balmukanov.retropoker.domain.Room;

@Component
@RequiredArgsConstructor
public class RoomDataRepositoryAdapter implements RoomRepository {
	private final RoomDataRepository roomDataRepository;

	public Mono<Room> save(Room room) {
		return roomDataRepository.save(room);
	}

	@Override
	public Mono<Room> findByUuid(String uuid) {
		return roomDataRepository.findById(uuid);
	}
}
