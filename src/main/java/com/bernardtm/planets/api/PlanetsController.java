package com.bernardtm.planets.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bernardtm.planets.domain.models.Planet;
import com.bernardtm.planets.domain.services.PlanetsService;

@RestController
@RequestMapping("/planets")
public class PlanetsController {
	
	@Autowired
	private PlanetsService service;

	//Adicionar um planeta (com nome, clima e terreno)
	@PostMapping
	public Planet addPlanet (@RequestBody Planet planet) {
		return service.addPlanet(planet);
	}
	
	//Listar planetas
	@GetMapping
	@Cacheable(value="event", key="#planetId")
	public List<Planet> getAllPlanets () {
		return service.getAllPlanets();
	}
	
	//Buscar por nome
	@GetMapping(value="/{planetName}")
	public Planet getPlanetByName (@PathVariable String planetName) {
		return service.getPlanetByName(planetName);
	}
	//Buscar por ID
	@Cacheable(value="event", key="#planetId")
	@GetMapping(value="/{planetName}")
	public Planet getPlanetById (@PathVariable String planetId) {
		return service.getPlanetById(planetId);
	}
	
	//Remover planeta
	@DeleteMapping(value="/{planetId}")
	public void deletePlanet (@PathVariable String planetId) {
		service.deletePlanet(planetId);
	}
	
}
