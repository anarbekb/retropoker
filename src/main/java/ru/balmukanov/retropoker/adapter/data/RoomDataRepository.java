package ru.balmukanov.retropoker.adapter.data;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import ru.balmukanov.retropoker.domain.Room;

public interface RoomDataRepository extends ReactiveCrudRepository<Room, String> {
}
