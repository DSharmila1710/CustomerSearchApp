package com.rupeek.CustomerServiceApp;

import static io.restassured.RestAssured.given;

import java.io.IOException;

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
				//.log().all()
				.get(getGlobalValue("list_resources"))
				.then()
				.assertThat().statusCode(200)
				.extract().response()
				.asString();
		
		JsonPath js = new JsonPath(response);
		js.prettyPeek();
		
		//ArrayList<String> userList =  new ArrayList<String>();

	}
	
	@Test(priority=2)
	public static void getUsersListWithIncorrectToken() throws IOException {

		RestAssured.baseURI = getGlobalValue("baseURI");

				given()
				.auth().oauth2(AuthenticateAPI.getToken()+"a")
				//.log().all()
				.get(getGlobalValue("list_resources"))
				.then()
				.assertThat().statusCode(401)
				.extract().response();

	}
	
	

	@Test(priority=3)
	public static void getUsersListWithInvalidURI() throws IOException {

		RestAssured.baseURI = getGlobalValue("baseURI")+"a";

				given()
				.auth().oauth2(AuthenticateAPI.getToken())
				//.log().all()
				.get(getGlobalValue("list_resources"))
				.then()
				.assertThat().statusCode(404)
				.extract().response();

	}
	
}
