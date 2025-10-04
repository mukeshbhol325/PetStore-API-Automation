package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.userendpoints;
import api.endpoints.userendpoints2;
import api.payload.User;
import io.restassured.response.Response;

public class UserTests2 {

	Faker faker;
	User userPayload;
	public Logger logger;
	
	@BeforeClass
	public void setupData() {
		
		faker = new Faker();
		userPayload = new User();

		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstname(faker.name().firstName());
		userPayload.setLastname(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5, 10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		
		//logs
		logger=LogManager.getLogger(this.getClass());
		
		
	}
	@Test(priority=1)
	public void testPostUser() {
		
		logger.info("**** Creating User ****");
		Response response=userendpoints2.createUser(userPayload);
		response.then().log().all();
		
		
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("**** User Created ****");
	}
	
	@Test(priority=2)
	public void testGetUserByName() {
		
		logger.info("**** Getting User Details ****");
		
		Response response=userendpoints2.readUser(this.userPayload.getUsername());
		response.then().log().all();
		//Assert.assertEquals(response.getStatusCode(), 200);
;	}
	
	@Test(priority=3)
	public void testUpdateUserByName() {
		
		logger.info("**** Updating User ****");
		
		//Update data using Payload
		userPayload.setFirstname(faker.name().firstName());
		userPayload.setLastname(faker.name().lastName());
		
		Response response=userendpoints2.updateUser(this.userPayload.getUsername(),userPayload);
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
		logger.info("**** User Updated ****");
		
		//Checking data after update
		
		/*Response responseafterupdate=userendpoints.readUser(this.userPayload.getUsername());
		Assert.assertEquals(responseafterupdate.getStatusCode(), 200);*/
	}
	
	@Test(priority=4)
	public void testDeleteUserByName() {
		
		logger.info("**** Deleting User ****");
		
		Response response=userendpoints2.deleteUser(this.userPayload.getUsername());
		Assert.assertEquals(response.getStatusCode(), 200);
	}
}
