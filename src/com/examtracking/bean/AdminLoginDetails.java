package com.examtracking.bean;

import java.io.Serializable;

/**
 * 
 * @author BATCH-'C' This class used to set and get login details
 *
 */
public class AdminLoginDetails implements Serializable {

	/**
	 * This method is used to set the variables for AdminLoginDetails
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Initializing login variables
	private String userName;
	private String password;
	private String securityQues;

	// Setters and getters for login
	/**
	 * 
	 * 
	 * @param userName
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserName() {
		return userName;
	}

	/**
	 * 
	 * @return password
	 */
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 
	 * @return security Question
	 */
	public String getSecurityQues() {
		return securityQues;
	}

	public void setSecurityQues(String securityQues) {
		this.securityQues = securityQues;
	}

	@Override
	public String toString() {
		return "AdminLoginDetails [userName=" + userName + ", password=" + password + ", securityQues=" + securityQues
				+ "]";
	}

}
