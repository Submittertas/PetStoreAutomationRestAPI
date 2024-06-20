package api.test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.endpoints.UserEndPoints2;
import api.payload.UserPojo;
import io.restassured.response.Response;

public class UserTest2 {
	
	Faker faker;
	
	UserPojo userPayload;
	
	@BeforeClass
	public void setupData()
	{
		faker = new Faker();
		userPayload = new UserPojo();
		
		userPayload.setId(faker.idNumber().hashCode());  //randomly generate Id number
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().emailAddress());
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		userPayload.setPassword(faker.internet().password(5, 10));
		
	}
	
	// This is reading from the routes.properties file
	
	@Test(priority=1)
	public void testPostUser()
	{
		Response response = UserEndPoints2.createUser(userPayload);
		response.then().log().all();
		
		response.then().log().body().statusCode(200);
		Assert.assertEquals(response.getStatusCode(), 200);
		//Assert.assertEquals(DEFAULT_URL_ENCODING_ENABLED, null);
		
	}
	
	
	@Test(priority=2)
	public void testGetUserByName()
	{
		Response response = UserEndPoints2.readUser(this.userPayload.getUsername());
		
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
	}
	
	
	@Test(priority=3)
	public void testUpdateUser()
	{
		// Update data using Pojo Payload
		
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().emailAddress()); 
		
		Response response = UserEndPoints2.updateUser(this.userPayload.getUsername(),userPayload);
		
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(),200);
		
		//Checking data after Update
		
		Response responseAfterUpdate = UserEndPoints2.readUser(this.userPayload.getUsername()); 
		Assert.assertEquals(responseAfterUpdate.getStatusCode(), 200);
		
	}
	
	
	@Test(priority=4)
	public void testDeleteUser()
	{
		Response response = UserEndPoints.deleteUser(this.userPayload.getUsername());
		
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
	}

}
