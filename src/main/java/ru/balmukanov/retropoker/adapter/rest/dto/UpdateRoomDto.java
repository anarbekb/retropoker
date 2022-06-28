package ru.balmukanov.retropoker.adapter.rest.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.balmukanov.retropoker.adapter.rest.validator.NotEmptyPasswordIfProtected;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@NotEmptyPasswordIfProtected
public class UpdateRoomDto {
	@NotNull
	private String uuid;
	private String description;
	private boolean isProtected;
	private String password;
}
