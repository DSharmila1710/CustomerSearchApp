package com.rupeek.CustomerServiceApp;


import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.testng.annotations.Test;

import com.rupeek.resources.ResourcesToAuthenticate;
import com.rupeek.resources.Utils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;


public class AuthenticateAPI extends Utils{
	
	public static String getToken() throws IOException {
		RestAssured.baseURI = getGlobalValue("baseURI");
		
		String response = 
				given()
				.contentType(ContentType.JSON)
				.body(ResourcesToAuthenticate.setInputToAuthenticateAPI(getGlobalValue("username"),getGlobalValue("password")))
				.log().body()
				.when()
				.post(getGlobalValue("authenticate_resources"))
				.then().assertThat()
				.statusCode(200)
				.extract().response()
				.asString();
		
		return extractValueFromJson(response,"token");
		
	}
	
	@Test
	public void printToken() throws IOException  {
		System.out.println(getToken());
	}
	

}
