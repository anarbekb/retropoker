package ru.balmukanov.retropoker.adapter.rest.validator;

import org.springframework.util.ObjectUtils;
import ru.balmukanov.retropoker.adapter.rest.dto.CreateRoomDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NotEmptyPasswordIfProtectedValidator implements ConstraintValidator<NotEmptyPasswordIfProtected, CreateRoomDto> {
	@Override
	public boolean isValid(CreateRoomDto value, ConstraintValidatorContext context) {
		if (value.isProtected() && ObjectUtils.isEmpty(value.getPassword()) || value.isProtected() && value.getPassword().isBlank()) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate("You need to fill in a password if you want to create a secure room")
				.addConstraintViolation();
			return false;
		}
		return true;
	}
}
