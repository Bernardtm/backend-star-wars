package com.bernardtm.planets.functional;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;

import java.util.Optional;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import com.bernardtm.planets.domain.models.Planet;
import com.bernardtm.planets.domain.repositories.PlanetsRepository;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class PlanetsControllerFunctionalTest {
	
	@LocalServerPort
    int port;

	private final String CONTEXT_PATH = "/planets-api";
	private final String NONEXISTENT_PLANET_ID = "9998";
	private final String NONEXISTENT_PLANET_NAME = "9998_NAME";
	private final String PLANET_NAME = "Martian";

	private Planet planet;
	private Planet createdPlanet;

	@Autowired
	PlanetsRepository repository;


	@Before
    public void setUp() {
        RestAssured.port = port;
        
        planet = new Planet();
        planet.setName(PLANET_NAME);
        planet.setClimate("Warm");
        planet.setTerrain("Rocky");
        createdPlanet = repository.save(planet);
    }
	
	@After
    public void tearDown() {
		repository.delete(createdPlanet);
    }
    
	@Test
	public void getPlanetById() {
		
		given().
        	basePath(CONTEXT_PATH).get("/planets/id/" + createdPlanet.getId()).
	    then().
	        statusCode(200).
	        body("id", equalTo(createdPlanet.getId()));
    }
	
	@Test
	public void getNonexistentPlanetById() {
		
		given().
        	basePath(CONTEXT_PATH).get("/planets/id/" + NONEXISTENT_PLANET_ID).
	    then().
	        statusCode(204);
    }
	
	@Test
	public void getPlanetByName() {
		
		given().
        	basePath(CONTEXT_PATH).get("/planets/name/" + createdPlanet.getName()).
	    then().
	        statusCode(200).
	        body("name", hasItem(createdPlanet.getName()));
    }
	
	@Test
	public void getNonexistentPlanetByName() {
		
		given().
        	basePath(CONTEXT_PATH).get("/planets/name/" + NONEXISTENT_PLANET_NAME).
	    then().
	        statusCode(204);
    }
	
	@Test
	public void getAllPlanets() {
		
		given().
        	basePath(CONTEXT_PATH).get("/planets/").
	    then().
	        statusCode(200).
	        body("name", hasItem(createdPlanet.getName()));
    }
	
	@Test
	public void addPlanet() throws JSONException {
		String name = "terrana6";
		String payload = new JSONObject()
				.put("name", name)
				.put("climate", "warm")
				.put("terrain", "rocky")
				.toString();
		
		given().
			contentType(ContentType.JSON).
			body(payload).
        	basePath(CONTEXT_PATH).post("/planets/").
	    then().
	        statusCode(201);
		
		Optional<Planet> planetFound = repository.findByName(name);
		repository.delete(planetFound.get());
    }
	
	@Test
	public void addExistentPlanet() throws JSONException {
		String payload = new JSONObject()
				.put("name", PLANET_NAME)
				.put("climate", "warm")
				.put("terrain", "rocky")
				.toString();
		
		given().
			contentType(ContentType.JSON).
			body(payload).
        	basePath(CONTEXT_PATH).post("/planets/").
	    then().
	        statusCode(400);
		
		Optional<Planet> planetFound = repository.findByName(PLANET_NAME);
		repository.delete(planetFound.get());
    }
	
	@Test
	public void addPlanetWithoutName() throws JSONException {
		String payload = new JSONObject()
				.put("climate", "warm")
				.put("terrain", "rocky")
				.toString();
		
		given().
			contentType(ContentType.JSON).
			body(payload).
        	basePath(CONTEXT_PATH).post("/planets/").
	    then().
	        statusCode(400);
    }
	
	@Test
	public void addPlanetWithoutClimate() throws JSONException {
		String payload = new JSONObject()
				.put("name", "terrana6")
				.put("terrain", "rocky")
				.toString();
		
		given().
			contentType(ContentType.JSON).
			body(payload).
	    	basePath(CONTEXT_PATH).post("/planets/").
	    then().
	        statusCode(400);
    }
	
	@Test
	public void addPlanetWithoutTerrain() throws JSONException {
		String payload = new JSONObject()
				.put("name", "terrana6")
				.put("climate", "warm")
				.toString();
		
		given().
			contentType(ContentType.JSON).
			body(payload).
	    	basePath(CONTEXT_PATH).post("/planets/").
	    then().
	        statusCode(400);
    }
	
	@Test
	public void addPlanetMissingTwoProperties() throws JSONException {
		String payload = new JSONObject()
				.put("name", "terrana6")
				.toString();
		
		given().
			contentType(ContentType.JSON).
			body(payload).
	    	basePath(CONTEXT_PATH).post("/planets/").
	    then().
	        statusCode(400);
    }
	
	@Test
	public void deletePlanet() {
		
		given().
        	basePath(CONTEXT_PATH).delete("/planets/" + createdPlanet.getId()).
	    then().
	        statusCode(204);
    }
	
	@Test
	public void deleteNonExistentPlanet() {
		
		given().
        	basePath(CONTEXT_PATH).delete("/planets/" + NONEXISTENT_PLANET_ID).
	    then().
	        statusCode(204);
    }

}
