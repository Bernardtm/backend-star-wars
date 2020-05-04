package com.bernardtm.planets.functional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.bernardtm.planets.domain.httpclients.HttpClientSwapi;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HttpClientSwapiTest {
	
	@Autowired
    HttpClientSwapi swapi;
	
	@Test
    public void swapiTest() {
		String planetId = "1";
		String appearances = swapi.getMovieAppearances(planetId);
		
		System.out.println(appearances);
    }
	
	@Test
    public void swapiErrorTest() {
		String inexistentPlanetId = "19999";
		String appearances = swapi.getMovieAppearances(inexistentPlanetId);
		
		System.out.println(appearances);
    }

}
