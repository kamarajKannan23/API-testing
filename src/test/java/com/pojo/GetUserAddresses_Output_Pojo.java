package com.pojo;

import java.util.ArrayList;
/**
 * @author Kamaraj
 * @description Used to maintain GetUserAddresses Output data and methods
 * @date 04/09/2022
 */
public class GetUserAddresses_Output_Pojo {
	    public int status;
	    public String message;
	    public ArrayList<Datum> data;
	    
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
		public ArrayList<Datum> getData() {
			return data;
		}
		public void setData(ArrayList<Datum> data) {
			this.data = data;
		}
	    

}
