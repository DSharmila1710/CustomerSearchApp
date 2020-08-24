package com.rupeek.CustomerServiceApp;


import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.testng.annotations.DataProvider;
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
	
	@Test(priority=1)
	public void printToken() throws IOException  {
		System.out.println(getToken());
	}
	
	@Test(priority=2,dataProvider="invalidInput")
	public static void validateAPIWithInvalidInput(String uri, String username, String password) throws IOException {
		RestAssured.baseURI = uri;
		
		
				given()
				.contentType(ContentType.JSON)
				.body(ResourcesToAuthenticate.setInputToAuthenticateAPI(username , password))
				.log().all()
				.when()
				.post(getGlobalValue("authenticate_resources"))
				.then().assertThat()
				.statusCode(401)
				.extract().response();		
		
	}
	
	@DataProvider
	public Object[][] invalidInput() throws IOException{
		
		return new Object[][] {
			{getGlobalValue("baseURI")+"invalid_url",getGlobalValue("username"),getGlobalValue("password")},
			{getGlobalValue("baseURI"),"Invalid_username",getGlobalValue("password")},
			{getGlobalValue("baseURI"),getGlobalValue("username"),"Invalid_password"},
			{getGlobalValue("baseURI"),"",getGlobalValue("password")},
			{getGlobalValue("baseURI"),getGlobalValue("username"),""}
			
		};
	}
	
	
	

}
