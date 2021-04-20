package test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class API3 {

	
	public static void main(String[] args) {
		
		
		
		Response res= RestAssured.given().contentType(ContentType.JSON).cookie("JSESSIONID",SessionID.createSessionID()).get("http://localhost:8080/rest/api/2/search");
		
		System.out.println(res.getStatusCode());
		//System.out.println(res.getBody().jsonPath().prettify());
		System.out.println(res.getBody().asString());
	}
}
