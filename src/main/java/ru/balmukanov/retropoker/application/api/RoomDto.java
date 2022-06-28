package ru.balmukanov.retropoker.application.api;

import lombok.*;

@Getter
@Setter
@ToString
public class RoomDto {
	private String uuid;
	private String description;
	private boolean isProtected;
}
