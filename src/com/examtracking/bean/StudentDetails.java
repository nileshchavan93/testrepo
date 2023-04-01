package com.examtracking.bean;

import java.io.Serializable;

/**
 * This class used to store  student data in a database
 * 
 * @author BATCH-'C'
 *
 */
public class StudentDetails implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// variables to declare the student details
	private long student_id;
	private String firstname;
	private String lastname;
	private String email_id;
	private String mobile_no;
	private String parent_no;
	private String address;
	private String branch;
	private String date_of_birth;
	private String date_of_joining;

	// getters setters method
	/**
	 * @return student_id
	 */
	public long getStudent_id() {
		return student_id;
	}

	public void setStudent_id(long student_id) {
		this.student_id = student_id;
	}

	/**
	 * @return firstname
	 */
	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	/**
	 * @return lastname
	 */
	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	/**
	 * @return Email_id
	 */
	public String getEmail_id() {
		return email_id;
	}

	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}

	/**
	 * @return Mobileno
	 */
	public String getMobile_no() {
		return mobile_no;
	}

	public void setMobile_no(String mobile_no) {
		this.mobile_no = mobile_no;
	}

	/**
	 * @return parent_no
	 */
	public String getParent_no() {
		return parent_no;
	}

	public void setParent_no(String parent_no) {
		this.parent_no = parent_no;
	}

	/**
	 * @return Address
	 */
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return Branch
	 */
	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	/**
	 * @return date_of_birth
	 */
	public String getDate_of_birth() {
		return date_of_birth;
	}

	public void setDate_of_birth(String date_of_birth) {
		this.date_of_birth = date_of_birth;
	}

	/**
	 * @return date_of_joining
	 */
	public String getDate_of_joining() {
		return date_of_joining;
	}

	public void setDate_of_joining(String date_of_joining) {
		this.date_of_joining = date_of_joining;
	}
}
