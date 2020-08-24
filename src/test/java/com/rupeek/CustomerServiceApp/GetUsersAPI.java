package com.rupeek.CustomerServiceApp;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.rupeek.resources.Utils;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class GetUsersAPI extends Utils{

	@Test(priority=1)
	public static void getUsersList() throws IOException {

		RestAssured.baseURI = getGlobalValue("baseURI");
		String response = given()
				.auth().oauth2(AuthenticateAPI.getToken())
				.get(getGlobalValue("list_resources"))
				.then()
				.assertThat().statusCode(200)
				.extract().response()
				.asString();
		
		JsonPath js = new JsonPath(response);
		js.prettyPeek();
		
	}
	
	@Test(priority=2,dataProvider="invalidInput")
	public static void getUsersListWithInvalidInput(String uri, String token) throws IOException {

		RestAssured.baseURI = uri;

				given()
				.auth().oauth2(token)
				.get(getGlobalValue("list_resources"))
				.then()
				.assertThat().statusCode(401)
				.extract().response();

	}
	

	@DataProvider
	public Object[][] invalidInput() throws IOException{
		
		return new Object[][] {			
			{getGlobalValue("baseURI"), AuthenticateAPI.getToken()+"invalid"}
			
		};
	}
	
}
