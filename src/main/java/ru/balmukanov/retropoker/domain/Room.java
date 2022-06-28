package ru.balmukanov.retropoker.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Data
@Document(collection = "rooms")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Room {
	@Id
	private String uuid;
	private String description;
	private boolean isProtected;
	private String password;
	private Set<User> users;
}
