package com.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.pojo.AddUserAddress_Input_Pojo;
import com.pojo.UpdateUserAddress_Input_Pojo;

import com.pojo.AddUserAddress_Input_Pojo;
import com.pojo.UpdateUserAddress_Input_Pojo;

import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/**
 * @author Kamaraj
 * @description Used to maintain all reusable methods
 * @date 02/09/2022
 */

public class RestAssuredBaseClass {

	static RequestSpecification reqSpec;
	public static Response response;

	/**
	 * @description Used to add single header for api request
	 * @param key
	 * @param value
	 */
	public void addHeader(String key, String value) {
		reqSpec = RestAssured.given().header(key, value);
	}

	/**
	 * @description Used to add (multiple header) list of header for api request
	 * @param headers
	 */
	public void addHeaders(Headers headers) {
		reqSpec = RestAssured.given().headers(headers);
	}

	/**
	 * @description Used to set formdata 
	 * @param key
	 * @param file
	 */
	public void formData(String key, File file) {
		reqSpec = reqSpec.multiPart(key, file);
	}

	/**
	 * @description Used to login with username and password 
	 * @param userName
	 * @param password
	 */
	public void basicAuth(String userName, String password) {
		reqSpec = reqSpec.auth().preemptive().basic(userName, password);
	}

	/**
	 * @description Used to add queary parameter to request endpoint
	 * @param key
	 * @param value
	 */
	public void addQuearyParameter(String key, String value) {
		reqSpec = reqSpec.queryParam(key, value);
	}

	/**
	 * @description Used to add queary parameter to request endpoint
	 * @param key
	 * @param value
	 */
	public void addPathParameter(String key, String value) {
		reqSpec = reqSpec.pathParam(key, value);
	}

	/**
	 * @description Used to add body (input data) to request endpoint
	 * @param body
	 */
	public void addBody(Object body) {
		reqSpec = reqSpec.body(body);
	}

	/**
	 * @description Used to add body (input data) to request endpoint
	 * @param body
	 */
	public void addBody(String body) {
		reqSpec = reqSpec.body(body);

	}

	/**
	 * @description Used to select one of method in switch case by given type
	 * @param type
	 * @param endpoint
	 * @return methodType
	 */
	public Response requestMethodType(String type, String endpoint) {
		switch (type) {
		case "GET":
			response = reqSpec.log().all().get(endpoint);
			break;

		case "POST":
			response = reqSpec.log().all().post(endpoint);
			break;

		case "PUT":
			response = reqSpec.log().all().put(endpoint);
			break;

		case "DELETE":
			response = reqSpec.log().all().delete(endpoint);
			break;

		default:
			break;
		}

		return response;
	}

	/**
	 * @description Used to get status code given response
	 * @param response
	 * @return statusCode
	 */
	public int getStatusCode(Response response) {
		int statusCode = response.getStatusCode();
		return statusCode;
	}

	/**
	 * @description Used to convert resonsebody as string
	 * @param responce
	 * @return String
	 */
	public String getResBodyAsString(Response responce) {
		String asString = response.asString();
		return asString;
	}
	/**
	 * @description Used to convert resonsebody as pretty string
	 * @param responce
	 * @return String
	 */
	public String getResBodyAsPreetyString(Response responce) {
		String asPrettyString = responce.asPrettyString();
		return asPrettyString;
	}

	/**
	 * description Used to get property value from config file
	 * @param key
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static String getPropertyFileValue(String key) throws FileNotFoundException, IOException {
		Properties properties = new Properties();
		properties.load(new FileInputStream(System.getProperty("user.dir") + "\\Config\\Config.properties"));

		String value = (String) properties.get(key);
		return value;
	}

	
}