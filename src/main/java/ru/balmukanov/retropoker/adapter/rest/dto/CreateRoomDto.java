package ru.balmukanov.retropoker.adapter.rest.dto;

import lombok.*;
import ru.balmukanov.retropoker.adapter.rest.validator.NotEmptyPasswordIfProtected;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@NotEmptyPasswordIfProtected
public class CreateRoomDto {
	@NotNull
	private String description;
	private boolean isProtected;
	private String password;
}
