package com.bernardtm.planets.health;

import static org.hamcrest.CoreMatchers.is;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HealthCheckTest {
	
	@LocalServerPort
    int port;

    @Before
    public void setUp() {
        RestAssured.port = port;
    }
	
	@Test
	public void healthCheckViaActuator() {

		given().
            basePath("/planets-api").get("/actuator/health").
        then().
            statusCode(200).
            body("status", is("UP"));
    }


}
