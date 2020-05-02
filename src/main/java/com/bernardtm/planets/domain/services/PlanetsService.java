package com.bernardtm.planets.domain.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bernardtm.planets.domain.models.Planet;
import com.bernardtm.planets.domain.repositories.PlanetsRepository;

@Service
public class PlanetsService {
	
	@Autowired
	private PlanetsRepository repository;
	
	public Planet addPlanet(Planet planet) {
		return repository.save(planet);
	}

	public List<Planet> getAllPlanets() {
		return repository.findAll();
	}

	public Planet getPlanetByName(String planetName) {
		return null;
	}

	public Planet getPlanetById(String planetId) {
		Optional<Planet> planet = repository.findById(planetId);
		return planet.get();
	}

	public void deletePlanet(String planetId) {
		repository.deleteById(planetId);		
	}
}
