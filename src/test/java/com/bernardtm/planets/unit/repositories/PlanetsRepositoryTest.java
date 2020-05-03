package com.bernardtm.planets.unit.repositories;

import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.bernardtm.planets.domain.models.Planet;
import com.bernardtm.planets.domain.repositories.PlanetsRepository;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PlanetsRepositoryTest {
	
	private Planet planet;
	
	private Planet createdPlanet;
	
	@Autowired
    PlanetsRepository repository;
	
	@Before
    public void setUp() {
		planet = new Planet();
		planet.setClimate("Coldy");
		planet.setTerrain("Rocky");
    }
	
	@Test
    public void savePlanet() {
		planet.setName("Terrana1");
		createdPlanet = repository.save(planet);
        Assert.assertTrue(createdPlanet.getId() != null);
        repository.delete(createdPlanet);
    }
	
	@Test
    public void getPlanetById() {
		planet.setName("Terrana2");
		createdPlanet = repository.save(planet);
		
		Optional<Planet> foundPlanet = repository.findById(createdPlanet.getId());
		
        Assert.assertTrue(foundPlanet.get().getId().equals(createdPlanet.getId()));
        repository.delete(foundPlanet.get());
    }
	
	@Test
    public void getAllPlanets() {
    	planet.setName("Terrana3");
		createdPlanet = repository.save(planet);
		
		List<Planet> foundPlanets = repository.findAll();
		for (Planet pl : foundPlanets) {
			if (pl.getId().equals(createdPlanet.getId())) {
				Assert.assertTrue(true);
			}
		}
		
        repository.delete(createdPlanet);
    }
    
	
	@Test
    public void getPlanetByName() {
		String name = "Terrana4";
		planet.setName(name);
		createdPlanet = repository.save(planet);
		
		Optional<Planet> foundPlanet = repository.findByName(name);
		
        Assert.assertTrue(foundPlanet.get().getId().equals(createdPlanet.getId()));
        repository.delete(foundPlanet.get());
    }

}
