package com.bernardtm.planets.domain.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bernardtm.planets.domain.models.Planet;

public interface PlanetsRepository extends MongoRepository<Planet, String> {
	
}
