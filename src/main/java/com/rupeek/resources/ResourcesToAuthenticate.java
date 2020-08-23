package com.rupeek.resources;

public class ResourcesToAuthenticate {

	public static AuthenticatePOJO setInputToAuthenticateAPI(String username, String password) {
		
		AuthenticatePOJO pojo = new AuthenticatePOJO();
		pojo.setUsername(username);
		pojo.setPassword(password);
		
		return pojo;
	}
	
}

