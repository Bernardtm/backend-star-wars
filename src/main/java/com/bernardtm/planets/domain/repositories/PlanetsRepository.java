package com.bernardtm.planets.domain.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bernardtm.planets.domain.models.Planet;

public interface PlanetsRepository extends MongoRepository<Planet, String> {

	List<Planet> findByNameContaining(String planetName);

	Optional<Planet> findByName(String name);
	
}
