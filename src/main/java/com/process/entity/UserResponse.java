 package com.process.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

	private String token;
	private String Messsage;
	
	
	
public String getMesssage() {
		return Messsage;
	}
	public void setMesssage(String messsage) {
		Messsage = messsage;
	}
	
	public UserResponse(String token, String Messsage) {
		
        this.token=token;
		
		this.Messsage=Messsage;
 	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	
	

}
