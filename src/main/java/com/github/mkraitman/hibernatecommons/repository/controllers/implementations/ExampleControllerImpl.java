package com.github.mkraitman.hibernatecommons.repository.controllers.implementations;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Repository;

import com.github.mkraitman.hibernatecommons.entities.ExampleEntity;
import com.github.mkraitman.hibernatecommons.repository.controllers.ExampleController;

@Repository
@EnableAutoConfiguration
public class ExampleControllerImpl extends GenericController<ExampleEntity, Long> implements ExampleController {

	@Override
	public String getIdentity() {
		return "id";
	}

}
