package GetRequest;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.*;
import org.testng.annotations.Test;

public class GetUsers {

	@Test
	public void GetAllUsers()
	{
		System.out.println("GetAllUsers");
		int responseCode = RestAssured.get("https://reqres.in/api/users").getStatusCode();
		System.out.println("Response code " + responseCode);
		Assert.assertEquals(responseCode, 200, "Response code did not match");	
	}
	
	@Test
	public void GetSingleUser()
	{
		System.out.println("GetSingleUser");
		RestAssured.baseURI = "https://reqres.in/api/users"; 
		
		RequestSpecification httpRequest = RestAssured.given();
		Response respose = httpRequest.get("6");
		
		Assert.assertEquals(respose.getStatusCode(), 200, "Response code did not match");	
		System.out.println("Response as string " + respose.asString());
		System.out.println("Response body " + respose.getBody());
		
		JsonPath jsonPathData =  respose.jsonPath();
		System.out.println("CONTENT " + jsonPathData.get("data"));
		// Response as string {"data":{"id":6,"email":"tracey.ramos@reqres.in","first_name":"Tracey","last_name":"Ramos","avatar":"https://s3.amazonaws.com/uifaces/faces/twitter/bigmancho/128.jpg"}}
		//Assert.assertEquals(jsonPathData.get("id"), "6", "Id is incorrect");
		//Assert.assertEquals(jsonPathData.get("email"), "tracey.ramos@reqres.in", "email is incorrect");
		//Assert.assertEquals("Tracey", jsonPathData.getString("first_name"), "Firstname is incorrect");
	}	
}	
