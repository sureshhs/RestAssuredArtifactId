package PostRequest;

import static org.testng.Assert.assertEquals;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class AddUsers {

	@Test
	public void AddSingleUser() 
	{
		System.out.println("AddSingleUser");
		RestAssured.baseURI = "https://reqres.in/api/users";
		RequestSpecification request = RestAssured.given();

		// Create JSON request parameters
		JSONObject requestParams = new JSONObject();
		requestParams.put("name", "Neil");
		requestParams.put("job", "Software Engineer");
		// Indicate format (JOSN) of request parameters
		request.header("Content-Type", "application/json");
		// Send parameters as body of the request
		request.body(requestParams.toJSONString());

		Response response = request.post();
		Assert.assertEquals(response.getStatusCode(), 201, "Response code did not match");
		// Assert.assertEquals(response.jsonPath().get("SuccessCode"),
		// "OPERATION_SUCCESS", "Success code did not match");
		System.out.println("Posted successfully");
	}

	@Test(dataProvider = "empDataProvider")
	public void AddUsers(String name, String job) 
	{
		System.out.println("AddUsers");
		RestAssured.baseURI = "https://reqres.in/api/users";
		RequestSpecification request = RestAssured.given();

		JSONObject params = new JSONObject();
		params.put("name", name);
		params.put("job", job);

		request.header("Content-Type", "application/json");
		request.body(params.toJSONString());

		Response response = request.post();
		Assert.assertEquals(response.getStatusCode(), 201, "Response does not match");
		System.out.println("Response body - " + response.getBody().asString());
		Assert.assertEquals(response.getBody().asString().contains(name), true);
	}

	@DataProvider(name = "empDataProvider")
	public String[][] GetUserInfo() {
		String data[][] = { { "Neil", "SDET" }, { "Nick", "Developer" } };
		return data;
	}

}
