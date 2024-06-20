package api.test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.module.jsv.JsonSchemaValidator;

public class UniversitiesSchemaValidation {
	
	@Test
	public void UKUniversitySchemaValidation()
	{
		given()
		
		
		.when()
		.get("http://universities.hipolabs.com/search?name=middle")
		
		.then()
		.assertThat().body((JsonSchemaValidator.matchesJsonSchemaInClasspath("UniversitiesSchema.json")));
			
	}
	
	
	


}
