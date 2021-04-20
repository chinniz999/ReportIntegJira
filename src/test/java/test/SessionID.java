package test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class SessionID {
	
	public static String  createSessionID() {
		

	      String body="{\r\n" + 
	      		"    \"username\":\"root\",\"password\":\"root\"\r\n" + 
	      		"}";
	        
	        Response resp= RestAssured.given().headers("Content-Type", "application/json").body(body).post("http://localhost:8080/rest/auth/1/session");
	        System.out.println(resp.getStatusCode());
	        System.out.println(resp.getBody().jsonPath().prettify());
	        return resp.getCookie("JSESSIONID");
		
	}

}
