package com.bernardtm.planets.health;

//import static org.hamcrest.CoreMatchers.is;

//import static io.restassured.module.jsv.JsonSchemaValidator.*;

import org.testng.annotations.Test;

//import io.restassured.RestAssured;


public class HealthCheckTest {
	
	@Test(groups="health")
	public void healthCheckViaActuator() {
//		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
//		RestAssured.basePath = "/actuator";
//
//		RestAssured.when().
//            get("/health").
//        then().
//            statusCode(200).
//            body("status", is("UP"));
//
//		RestAssured.baseURI = "/octo-events";
    }


}
