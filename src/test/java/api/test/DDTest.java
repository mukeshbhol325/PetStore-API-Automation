package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.userendpoints;
import api.payload.User;
import api.utilities.dataProviders;
import io.restassured.response.Response;

public class DDTest {

	@Test(priority=1,dataProvider="Data",dataProviderClass=dataProviders.class)
	public void testPostuser(String userID,String userName,String fname,String lname,String useremail,String pwd,String ph){
		
		User userPayload=new User();
		userPayload.setId(Integer.parseInt(userID));
		userPayload.setUsername(userName);
		userPayload.setFirstname(fname);
		userPayload.setLastname(lname);
		userPayload.setEmail(useremail);
		userPayload.setPassword(pwd);
		userPayload.setPhone(ph);
		
		Response response=userendpoints.createUser(userPayload);
		response.then().log().all();
			
			
		Assert.assertEquals(response.getStatusCode(), 200);
	
}
	@Test(priority=2,dataProvider="UserNames",dataProviderClass=dataProviders.class)
	public void testDeleteUserByName(String userName) {
		
		Response response=userendpoints.deleteUser(userName);
		Assert.assertEquals(response.getStatusCode(), 200);
	}
}