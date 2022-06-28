package ru.balmukanov.retropoker.adapter.rest;

import org.mapstruct.Mapper;
import ru.balmukanov.retropoker.adapter.rest.dto.CreateRoomDto;
import ru.balmukanov.retropoker.adapter.rest.dto.UpdateRoomDto;
import ru.balmukanov.retropoker.application.api.RoomDto;
import ru.balmukanov.retropoker.domain.Room;

@Mapper(componentModel = "spring")
public interface RoomMapper {
	Room toEntity(CreateRoomDto roomDto);
	Room toEntity(UpdateRoomDto roomDto);
	RoomDto toDto(Room room);
}
