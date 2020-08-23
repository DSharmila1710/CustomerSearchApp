package com.rupeek.CustomerServiceApp;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.testng.annotations.Test;

import com.rupeek.resources.Utils;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class GetUsersAPI extends Utils{

	@Test
	public static void getUsersList() throws IOException {

		RestAssured.baseURI = getGlobalValue("baseURI");
		String response = given()
				.auth().oauth2(AuthenticateAPI.getToken())
				//.log().all()
				.get(getGlobalValue("list_resources"))
				.then()
				//.assertThat().statusCode(200)
				.extract().response()
				.asString();
		
		JsonPath js = new JsonPath(response);
		js.prettyPeek();
		
		//ArrayList<String> userList =  new ArrayList<String>();

	}
	
	
}
