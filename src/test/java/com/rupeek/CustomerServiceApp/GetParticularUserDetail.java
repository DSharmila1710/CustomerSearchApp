package com.rupeek.CustomerServiceApp;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.rupeek.resources.Utils;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class GetParticularUserDetail extends Utils{

	@Test(dataProvider="inputPhoneNum",priority=1)
	public static void getUserDetail(String phoneNumber) throws IOException {

		RestAssured.baseURI = getGlobalValue("baseURI");
		String response = given()
				.auth().oauth2(AuthenticateAPI.getToken())
				//.log().all()
				.get(getGlobalValue("list_resources")+phoneNumber)
				.then()
				.assertThat().statusCode(200)
				.extract().response()
				.asString();
		
		JsonPath js = new JsonPath(response);
		js.prettyPeek();	

	}
	
	@Test(dataProvider="invalidInput",priority=2)
	public static void getUserDetailWithIncorrectToken(String phoneNumber) throws IOException {

		RestAssured.baseURI = getGlobalValue("baseURI");
		
				given()
				.auth().oauth2(AuthenticateAPI.getToken()+"a")
				.get(getGlobalValue("list_resources")+phoneNumber)
				.then()
				.assertThat().statusCode(401)
				.extract().response();

	}
	

	
	@Test(dataProvider="invalidInput",priority=3)
	public static void getUserDetailWithInvalidURI(String phoneNumber) throws IOException {

		RestAssured.baseURI = getGlobalValue("baseURI")+"a";
		
				given()
				.auth().oauth2(AuthenticateAPI.getToken())
				.get(getGlobalValue("list_resources")+phoneNumber)
				.then()
				.assertThat().statusCode(404)
				.extract().response();

	}
	
	@Test(dataProvider="invalidInput",priority=4)
	public static void getUserDetailWithIncorrectPhoneNumber(String phoneNumber) throws IOException {

		RestAssured.baseURI = getGlobalValue("baseURI");

				given()
				.auth().oauth2(AuthenticateAPI.getToken())
				//.log().all()
				.get(getGlobalValue("list_resources")+phoneNumber)
				.then()
				.assertThat().statusCode(200)
				.extract().response()
				.asString();
		
		

	}
	

	@DataProvider
	public Object[][] inputPhoneNum() throws IOException{
		
		return new Object[][] {			
			{"8037602400"},
			{"9972939567"},
			{"9995879555"}
			
		};
	}
	
	@DataProvider
	public Object[][] invalidInput() throws IOException{
		
		return new Object[][] {			
			{"8037602410"}
			
		};
	}
	


}
