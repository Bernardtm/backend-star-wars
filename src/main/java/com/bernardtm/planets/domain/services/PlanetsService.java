package com.bernardtm.planets.domain.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.bernardtm.planets.domain.httpclients.HttpClientSwapi;
import com.bernardtm.planets.domain.models.Planet;
import com.bernardtm.planets.domain.repositories.PlanetsRepository;

@Service
public class PlanetsService {
	
	private PlanetsRepository repository;
	private HttpClientSwapi swapi;

	public PlanetsService (PlanetsRepository repository, HttpClientSwapi swapi) {
		this.repository = repository;
		this.swapi = swapi;
	}
	
	
	public List<String> validaAddPlanet(Planet planet) {
		List<String> messages = new ArrayList<String>();
		
		if (planet.getName() == null) {
			messages.add("Name should not be null.");
		} else {
			Optional<Planet> planetFound = repository.findByName(planet.getName());
			if (planetFound.isPresent()) {
				messages.add("Planet with this name already exist.");
			}
		}
		if (planet.getClimate() == null) {
			messages.add("Climate should not be null.");
		}
		if (planet.getTerrain() == null) {
			messages.add("Terrain should not be null.");
		}
		
		return messages;
	}

	public Planet addPlanet(Planet planet) {
		planet.setAppearance(swapi.getMovieAppearances(planet.getId()));
		Planet createdPlanet = repository.save(planet);
		return createdPlanet;
	}
}
