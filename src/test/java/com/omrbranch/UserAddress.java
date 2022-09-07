package com.omrbranch;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.base.RestAssuredBaseClass;
import com.endpoints.EndPoints;
import com.pojo.AddUserAddress_Input_Pojo;
import com.pojo.AddUserAddress_Output_Pojo;
import com.pojo.DeleteUserAddress_Input_Pojo;
import com.pojo.DeleteUserAddress_Output_Pojo;
import com.pojo.GetUserAddresses_Output_Pojo;
import com.pojo.Login_Output_Pojo;
import com.pojo.UpdateProfilePic_Output_Pojo;
import com.pojo.UpdateUserAddress_Input_Pojo;
import com.pojo.UpdateUserAddress_Output_Pojo;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
/**
 * @author Kamaraj
 * @description Used to perform Login and actions on address and profic pic
 * @date 04/09/2022
 */
public class UserAddress extends RestAssuredBaseClass {
	String logtoken;
	String address_id;

	@AfterMethod
	public void after_method() {
		System.out.println("\n**************************************************************");
	}

	/**
	 * @description Used to change profile picture and verify success message
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	@Test (priority = 6)
	public void changeProfilePic() throws FileNotFoundException, IOException {
		List<Header> h = new ArrayList<>();

		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Authorization", "Bearer " + logtoken);
		Header h3 = new Header("Content-Type", "multipart/form-data");

		h.add(h1);
		h.add(h2);
		h.add(h3);
		
		Headers headers = new Headers(h);

		addHeaders(headers);
		System.out.println("IMAGE URL"+System.getProperty("user.dir")+getPropertyFileValue("profilePic"));
		formData("profile_picture",new File(System.getProperty("user.dir")+getPropertyFileValue("profilePic")));
		
		Response response = requestMethodType("POST",EndPoints.UPDATEPROFILEPIC);
		
		int statusCode = getStatusCode(response);
		Assert.assertEquals(statusCode, 200, "verify statusCode");
		
		
		UpdateProfilePic_Output_Pojo updateProfilePic=response.as(UpdateProfilePic_Output_Pojo.class);
		
		String resBodyAsPreetyString = getResBodyAsPreetyString(response);
		System.out.println(resBodyAsPreetyString);
		String message = updateProfilePic.getMessage();
		Assert.assertEquals(message, "Profile updated Successfully", "verify statusCode");

	}
	
	
	/**
	 * @description Used to get user addresses and verify success message
	 */
	
	@Test(priority = 4)
	public void getUserAddresses() {

		List<Header> h = new ArrayList<>();

		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Authorization", "Bearer " + logtoken);
		Header h3 = new Header("Content-Type", "application/json");

		h.add(h1);
		h.add(h2);
		h.add(h3);

		Headers headers = new Headers(h);

		addHeaders(headers);

		Response response = requestMethodType("GET", "https://omrbranch.com/api/getUserAddress");

		int statusCode = getStatusCode(response);
		System.out.println(statusCode);

		Assert.assertEquals(statusCode, 200, "verify statusCode");

		String resBody = getResBodyAsPreetyString(response);
		System.out.println(resBody);

		GetUserAddresses_Output_Pojo getUserAddress = response.as(GetUserAddresses_Output_Pojo.class);

		String message = getUserAddress.getMessage();

		Assert.assertEquals(message, "OK", "verify OK");

	}
	
	/**
	 * @description Used to delete user addresses and verify success message
	 */
	@Test(priority = 5)
	public void deleteUserAddress() {

		List<Header> h = new ArrayList<>();

		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Authorization", "Bearer " + logtoken);
		Header h3 = new Header("Content-Type", "application/json");

		h.add(h1);
		h.add(h2);
		h.add(h3);

		Headers headers = new Headers(h);

		addHeaders(headers);

		DeleteUserAddress_Input_Pojo deleteAddress = new DeleteUserAddress_Input_Pojo(address_id);
		addBody(deleteAddress);

		Response response = requestMethodType("DELETE", "https://omrbranch.com/api/deleteAddress");

		int statusCode = getStatusCode(response);
		System.out.println(statusCode);

		Assert.assertEquals(statusCode, 200, "verify statusCode");

		String resBody = getResBodyAsPreetyString(response);
		System.out.println(resBody);

		DeleteUserAddress_Output_Pojo deleteUserAddress = response.as(DeleteUserAddress_Output_Pojo.class);
		String message = deleteUserAddress.getMessage();
		Assert.assertEquals(message, "Address deleted successfully", "verify Address deleted successfully");

	}

	/**
	 * @description Used to update user addresses and verify success message
	 */
	@Test(priority = 3)
	public void updateUserAddress() {

		List<Header> h = new ArrayList<>();

		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Authorization", "Bearer " + logtoken);
		Header h3 = new Header("Content-Type", "application/json");

		h.add(h1);
		h.add(h2);
		h.add(h3);
		Headers headers = new Headers(h);

		addHeaders(headers);

		UpdateUserAddress_Input_Pojo updateAddress = new UpdateUserAddress_Input_Pojo(address_id, "Gandhi", "Kannan",
				"6381763806", "CasaGrand Homes", 35, 3659, 101, "600097", "Thooraipakkam", "Work");
		addBody(updateAddress);

		Response response = requestMethodType("PUT", "https://omrbranch.com/api/updateUserAddress");

		int statusCode = getStatusCode(response);
		System.out.println(statusCode);

		Assert.assertEquals(statusCode, 200, "verify statusCode");

		String resBody = getResBodyAsPreetyString(response);
		System.out.println(resBody);

		UpdateUserAddress_Output_Pojo updateUserAddress = response.as(UpdateUserAddress_Output_Pojo.class);
		String message = updateUserAddress.getMessage();
		Assert.assertEquals(message, "Address updated successfully", "verify Address updated successfully");

	}
	/**
	 * @description Used to add user addresses and verify success message
	 */
	@Test(priority = 2)
	public void addUserAddress() {

		List<Header> h = new ArrayList<>();

		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Authorization", "Bearer " + logtoken);
		Header h3 = new Header("Content-Type", "application/json");

		h.add(h1);
		h.add(h2);
		h.add(h3);
		Headers headers = new Headers(h);

		addHeaders(headers);

		AddUserAddress_Input_Pojo addAddress = new AddUserAddress_Input_Pojo("Kamal", "Kannan", "6381763806",
				"Royal Empire Flats", 35, 3659, 101, "600097", "Thooraipakkam", "Home");
		addBody(addAddress);

		Response response = requestMethodType("POST", "https://omrbranch.com/api/addUserAddress");

		int statusCode = getStatusCode(response);
		System.out.println(statusCode);

		String resBody = getResBodyAsPreetyString(response);
		System.out.println(resBody);

		AddUserAddress_Output_Pojo addUserAddress = response.as(AddUserAddress_Output_Pojo.class);

		int id = addUserAddress.getAddress_id();
		address_id = String.valueOf(id);
		System.out.println(address_id);

		String message = addUserAddress.getMessage();
		Assert.assertEquals(message, "Address added successfully", "verify Address added successfully");

	}
	
	/**
	 * @description Used to login with basic auth and verify success message
	 */

	@Test(priority = 1)
	public void login() {
		// 1.Add Header
		addHeader("Content-Type", "application/json");

		// 2.Basic Auth
		basicAuth("k23raj@gmail.com", "Omr@123");

		// 3.Pass Method and Endpoint
		Response response = requestMethodType("POST", "https://omrbranch.com/api/postmanBasicAuthLogin");

		// 4.Get Status Code
		int statusCode = getStatusCode(response);
		System.out.println(statusCode);

		// 5.Get resBody as PrettyString
		String resBody = getResBodyAsPreetyString(response);
		System.out.println("Login Response Body :" + resBody);

		Login_Output_Pojo login_Output_Pojo = response.as(Login_Output_Pojo.class);

		logtoken = login_Output_Pojo.getData().getLogtoken();

	}
}
