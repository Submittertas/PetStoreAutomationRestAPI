package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;



import api.endpoints.UserEndPoints;
import api.payload.UserPojo;
import api.utilities.DataProviders;
import io.restassured.response.Response;
import jdk.internal.org.jline.utils.Log;

public class DDTests {
	
	
	@Test(priority=1, dataProvider = "GetAllData", dataProviderClass = DataProviders.class)
	public void testPostUser(String UserID, String UserName, String FirstName, String LastName, String Email, String Password, String Phone)
	{

		
		UserPojo userPayload = new UserPojo();
		
		userPayload.setId(Integer.parseInt(UserID));
		userPayload.setUsername(UserName);
		userPayload.setFirstName(FirstName);
		userPayload.setLastName(LastName);
		userPayload.setEmail(Email);
		userPayload.setPassword(Password);
		userPayload.setPhone(Phone);
		
		Response response = UserEndPoints.createUser(userPayload);
		Assert.assertEquals(response.getStatusCode(), 200);
		
	}
	
	
	@Test(priority=2, dataProvider = "UserNames", dataProviderClass = DataProviders.class)
	public void testDeleteUserbyUserName(String UserName)
	{
		Response response = UserEndPoints.deleteUser(UserName);
		Assert.assertEquals(response.getStatusCode(), 200);
	
	}

}
