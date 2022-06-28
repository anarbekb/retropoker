package ru.balmukanov.retropoker.framework;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@Configuration
@EnableReactiveMongoRepositories(basePackages = "ru.balmukanov.retropoker")
public class MongoConfiguration {
}
