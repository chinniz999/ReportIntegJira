package test;

import org.json.JSONObject;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.restassured.RestAssured;
import io.restassured.response.Response;

@JsonIgnoreProperties(ignoreUnknown = true)
public class API {
	
	public static void createRequest() {
	
      String body="{\r\n" + 
      		"    \"username\":\"root\",\"password\":\"root\"\r\n" + 
      		"}";
        
        Response resp= RestAssured.given().headers("Content-Type", "application/json").body(body).post("http://localhost:8080/rest/auth/1/session");
        System.out.println(resp.getStatusCode());
        System.out.println(resp.getBody().jsonPath().prettify());
        String sessionID=resp.getCookie("JSESSIONID");
	    
	}
	
	public static void main(String[] args) {
		createRequest();
	}

}
