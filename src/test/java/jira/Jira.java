package jira;

import java.util.ArrayList;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class Jira {
	
	
	
	public static String createSessionID() {
		

	      String body="{\r\n" + 
	      		"    \"username\":\"root\",\"password\":\"root\"\r\n" + 
	      		"}";
	        
	        Response resp= RestAssured.given().headers("Content-Type", "application/json").body(body).post("http://localhost:8080/rest/auth/1/session");
	        System.out.println(resp.getStatusCode());
	        System.out.println(resp.getBody().jsonPath().prettify());
	        return resp.getCookie("JSESSIONID");
		
	}
	

	@SuppressWarnings("rawtypes")
	public static void isBugLogged(String bugdescription) throws JsonMappingException, JsonProcessingException {

		Response res = RestAssured.given().contentType(ContentType.JSON).cookie("JSESSIONID", createSessionID()).get("http://localhost:8080/rest/api/2/search");

		ObjectMapper mapper = new ObjectMapper();
		int size = ((ArrayList) mapper.readValue(res.getBody().asPrettyString(), Map.class).get("issues")).size();
		
		System.out.println("@@@@@@@@@@@@@@@@@ Bug size" + size);
		
		boolean flag=false;
		
		for (int i = 0; i < size; i++) {
			try {
				if(bugdescription.contains((CharSequence) (((Map) ((Map) ((ArrayList) mapper.readValue(res.getBody().asPrettyString(), Map.class).get("issues")).get(i)).get("fields")).get("summary")))) {
					System.out.println("Bug available Hence Not Logging");
					flag=true;
				}
				
			} catch (Exception e) {

			}

		}
		
		if(!flag) {
			
				System.out.println("Bug not available Hence Logging");
				logBug(bugdescription);
			
		}

	}
	
	public static void logBug(String bugdescriptionOrSummary) {

		Response res= RestAssured.given().contentType(ContentType.JSON).cookie("JSESSIONID",createSessionID()).body("{\r\n" +
				"    \"fields\": {\r\n" + 
				"       \"project\":\r\n" + 
				"       {\r\n" + 
				"          \"key\": \"K1\"\r\n" + 
				"       },\r\n" + 
				"       \"summary\": \""
				+ bugdescriptionOrSummary
				+ "\",\r\n" + 
				"       \"description\": \""
				+ bugdescriptionOrSummary
				+ "\",\r\n" + 
				"       \"issuetype\": {\r\n" + 
				"          \"name\": \"Bug\"\r\n" + 
				"       }\r\n" + 
				"   }\r\n" + 
				"}").post("http://localhost:8080/rest/api/2/issue");
		
		System.out.println(res.getStatusCode());
		System.out.println(res.getBody().jsonPath().prettify());
	
	}
	
	public static void main(String[] args) throws JsonMappingException, JsonProcessingException {
		
		isBugLogged("This is Sample text bug");
		
		
		
	}

}
