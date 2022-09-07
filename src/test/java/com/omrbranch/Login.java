package com.omrbranch;

import org.testng.annotations.Test;

import com.base.RestAssuredBaseClass;

import io.restassured.response.Response;
/**
 * @author Kamaraj
 * @description Used to perform login
 * @date 04/09/2022
 */
public class Login extends RestAssuredBaseClass {
	@Test
	public void login() {
		//1.Add Header
		addHeader("Content-Type", "application/json");

		//2.Add body
		addBody("{\r\n" + 
				"  \"email\": \"k23raj@gmail.com\",\r\n" + 
				"  \"pass\": \"Omr@123\"\r\n" + 
				"}");
		
		//3.Pass Method and Endpoint
		Response response = requestMethodType("POST","https://omrbranch.com/api/login");
		
		//4.Get Status Code 		
		int statusCode = getStatusCode(response);
		System.out.println(statusCode);
		
		//5.Get resBody as PrettyString
		String resBody = getResBodyAsPreetyString(response);
		System.out.println(resBody);
	}
}
