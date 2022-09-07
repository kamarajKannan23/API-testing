package com.omrbranch;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.base.RestAssuredBaseClass;
import com.democompany.StatusCodeVerifier;
import com.pojo.Login_Output_Pojo;

import io.restassured.response.Response;
/**
 * @author Kamaraj
 * @description Used to perform Login and get response in Login_Output_Pojo class
 * @date 04/09/2022
 */
public class LoginWithResponse extends RestAssuredBaseClass {

	@Test
	public void login() {
		addHeader("Content-Type", "application/json");
		
		basicAuth("k23raj@gmail.com", "Omr@123");
		
		Response response = requestMethodType("POST", "https://omrbranch.com/api/postmanBasicAuthLogin");
		
		int statusCode = getStatusCode(response);
		System.out.println(statusCode);
		Assert.assertEquals(statusCode, 200, "verifying status ");
		
		Login_Output_Pojo login_Output_Pojo = response.as(Login_Output_Pojo.class);
		
		String first_name = login_Output_Pojo.getData().getFirst_name();
		System.out.println(first_name);
		
		Assert.assertEquals(first_name, "Kamaraj", "verify first name");
		
		StatusCodeVerifier.verify(statusCode);
	}

}