package com.pojo;
/**
 * @author Kamaraj
 * @description Used to maintain AddUserAddress Output data and methods
 * @date 04/09/2022
 */
public class AddUserAddress_Output_Pojo {
	private int status;
	private String message;
	private int address_id;
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getAddress_id() {
		return address_id;
	}
	public void setAddress_id(int address_id) {
		this.address_id = address_id;
	}

}
