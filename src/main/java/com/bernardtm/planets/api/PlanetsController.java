package com.bernardtm.planets.api;

import java.net.URI;
import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.bernardtm.planets.domain.models.Planet;
import com.bernardtm.planets.domain.repositories.PlanetsRepository;
import com.bernardtm.planets.domain.services.PlanetsService;

@RestController
@RequestMapping("/planets")
public class PlanetsController {
	
	private PlanetsService service;
	
	private PlanetsRepository repository;
	
	public PlanetsController (PlanetsService service, PlanetsRepository repository) {
		this.service = service;
		this.repository = repository;
	}

	// TODO evict cache
	/** Add new planet to database
	 *  properties name, climate and terrain are obligatory
	 * @param planet
	 * @return
	 */
	@PostMapping
	public ResponseEntity<List<String>> addPlanet (@RequestBody Planet planet) {
		
		List<String> messages = service.validaAddPlanet(planet);
		
		if (!messages.isEmpty()) {
			return ResponseEntity.badRequest().body(messages);
		}
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/id/{id}")
				        .buildAndExpand(repository.save(planet).getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	/** Get All Planets
	 * 
	 * @return
	 */
	@GetMapping
	@Cacheable(value="event", key="#planetId")
	public ResponseEntity<List<Planet>> getAllPlanets () {
		return ResponseEntity.ok().body(repository.findAll());
	}
	
	/** Search for a planet containing the string in the name
	 * 
	 * @param planetName
	 * @return
	 */
	@GetMapping(value="/name/{planetName}")
	public ResponseEntity<List<Planet>> getPlanetByName (@PathVariable String planetName) {
		
		List<Planet> planets = repository.findByNameContaining(planetName);
		
		if (!planets.isEmpty()) {
			return ResponseEntity.ok().body(planets);
		} else {
			return ResponseEntity.noContent().build();
		}
		
	}
	
	/** Get the planet by Id
	 * 
	 * @param planetId
	 * @return
	 */
	@Cacheable(value="event", key="#planetId")
	@GetMapping(value="/id/{planetId}")
	public ResponseEntity<Planet> getPlanetById (@PathVariable String planetId) {
		return repository.findById(planetId)
				.map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.noContent().build());
	}
	
	/** Delete the planet by Id
	 * 
	 * @param planetId
	 * @return
	 */
	@DeleteMapping(value="/{planetId}")
	public ResponseEntity<Void> deletePlanet (@PathVariable String planetId) {
		repository.deleteById(planetId);
		return ResponseEntity.noContent().build();
	}
	
}
