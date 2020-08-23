package com.rupeek.resources;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import io.restassured.path.json.JsonPath;

public class Utils {

	static Properties prop = new Properties();

	public static String getGlobalValue(String key) throws IOException {
		FileInputStream fis = new FileInputStream("./env.properties");
		prop.load(fis);
		return prop.getProperty(key);

	}
	
	public static String extractValueFromJson(String str,String key) {
		JsonPath js = new JsonPath(str);
		return js.get(key);
	}
}