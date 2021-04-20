package test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class API2 {

	
	public static void main(String[] args) {
		
		//Response res= RestAssured.given().contentType(ContentType.JSON).cookie("JSESSIONID","ED81F4F3A2CA2D090A7C66D0276073C5").body("{\r\n" + 
		Response res= RestAssured.given().contentType(ContentType.JSON).cookie("JSESSIONID","ED81F4F3A2CA2D090A7C66D0276073C5").body("{\r\n" +
				"    \"fields\": {\r\n" + 
				"       \"project\":\r\n" + 
				"       {\r\n" + 
				"          \"key\": \"K1\"\r\n" + 
				"       },\r\n" + 
				"       \"summary\": \"New Bug From API3\",\r\n" + 
				"       \"description\": \"New Bug From API3\",\r\n" + 
				"       \"issuetype\": {\r\n" + 
				"          \"name\": \"Bug\"\r\n" + 
				"       }\r\n" + 
				"   }\r\n" + 
				"}").post("http://localhost:8080/rest/api/2/issue");
		
		System.out.println(res.getStatusCode());
		System.out.println(res.getBody().jsonPath().prettify());
	}
}
