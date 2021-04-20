package test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ReadJson {
	
	
	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
		
		Response res= RestAssured.given().contentType(ContentType.JSON).cookie("JSESSIONID",SessionID.createSessionID()).get("http://localhost:8080/rest/api/2/search");
		
		ObjectMapper mapper=new ObjectMapper();
		int size=((ArrayList) mapper.readValue(res.getBody().asPrettyString(), Map.class).get("issues")).size();
		System.out.println("@@@@@@@@@@@@@@@@@ size"+size);
		for(int i=0;i<size;i++) {
			try {
				System.out.println((((Map) ((Map) ((ArrayList) mapper.readValue(new File("C:\\Users\\chinn\\Desktop\\Results.json"), Map.class).get("issues")).get(i)).get("fields")).get("summary")));
			}catch(Exception e) {
				
			}
			
		}
		
	}
	
	
}


//ObjectMapper mapper=new ObjectMapper();
//int size=((ArrayList) mapper.readValue(new File("C:\\Users\\chinn\\Desktop\\Results.json"), Map.class).get("issues")).size();
//
//for(int i=0;i<size;i++) {
//	System.out.println((((Map) ((Map) ((ArrayList) mapper.readValue(new File("C:\\Users\\chinn\\Desktop\\Results.json"), Map.class).get("issues")).get(i)).get("fields")).get("summary")));
//}





