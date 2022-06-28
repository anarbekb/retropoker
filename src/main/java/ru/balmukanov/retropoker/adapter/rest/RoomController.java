package ru.balmukanov.retropoker.adapter.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import ru.balmukanov.retropoker.adapter.rest.dto.CreateRoomDto;
import ru.balmukanov.retropoker.adapter.rest.dto.UpdateRoomDto;
import ru.balmukanov.retropoker.application.api.RoomDto;
import ru.balmukanov.retropoker.application.api.RoomService;

@RestController
@RequestMapping("/room")
@RequiredArgsConstructor
public class RoomController {
	private final RoomService roomService;
	private final RoomMapper roomMapper;

	@GetMapping("/{uuid}")
	public Mono<RoomDto> get(@PathVariable String uuid) {
		return roomService.get(uuid).map(roomMapper::toDto);
	}

	@PostMapping
	public Mono<RoomDto> create(@RequestBody @Validated CreateRoomDto room) {
		return roomService.create(roomMapper.toEntity(room)).map(roomMapper::toDto);
	}

	@PatchMapping
	public Mono<RoomDto> update(@RequestBody @Validated UpdateRoomDto room) {
		return roomService.update(roomMapper.toEntity(room)).map(roomMapper::toDto);
	}
}
