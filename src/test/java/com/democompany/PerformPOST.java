package com.democompany;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PerformPOST {

	public static void main(String[] args) {

		RequestSpecification reqSpec;

		// 1.Initialize the rest assured
		reqSpec = RestAssured.given();
 
		// 2.Payload / reqbody 
		reqSpec = reqSpec.body("{\r\n" + 
				"    \"name\": \"morpheus\",\r\n" + 
				"    \"job\": \"leader\"\r\n" + 
				"}");
		
		// 3.method type and endpoint
		Response response = reqSpec.post("https://reqres.in/api/users/");

		// 4.Status Code
		int statusCode = response.getStatusCode();
		System.out.println(statusCode);

		// 5.Pretty String
		String asPrettyString = response.asPrettyString();
		System.out.println(asPrettyString);
	}

}
