package com.examtracking.bean;

import java.io.Serializable;

/***
 * 
 * @author Batch- 'C' This class use to get and set student login credentials for student.
 */

public class SetLoginDetails implements Serializable {
	/**
	 * This method is used to set the variables
	 */
	private static final long serialVersionUID = 1L;
	private long Stud_id;
	private String username;
	private String password;
	private String securityQues;

	// getters and setters for the variables
	public long getStud_id() {
		return Stud_id;
	}

	public void setStud_id(long stud_id) {
		Stud_id = stud_id;
	}

	/***
	 * @return user name
	 */
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	/***
	 * @return password
	 */
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/***
	 * @return security Question
	 */
	public String getSecurityQues() {
		return securityQues;
	}

	public void setSecurityQues(String securityQues) {
		this.securityQues = securityQues;
	}
}
